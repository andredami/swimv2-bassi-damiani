package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Address;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.entities.User.Status;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.LocationMissingException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.UserDirectoryManagerRemote;
import it.polimi.ingsw2.swim.validation.NameValidator;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.EmailValidator;
import org.hibernate.validator.InvalidValue;

/**
 * Session Bean implementation class UserDirectoryManager
 */
@Stateless
@Remote
public class UserDirectoryManager implements UserDirectoryManagerRemote {

	private static final int ENTRIES_PER_PAGE = 25;

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;

	private static final ClassValidator<Address> addressValidator = new ClassValidator<Address>(
			Address.class);

	private static final EmailValidator emailValidator = new EmailValidator();

	private static final NameValidator nameValidator = new NameValidator();

	/**
	 * Default constructor.
	 */
	public UserDirectoryManager() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByNameAndEmail(String firstname, String surname,
			String email, int page) throws InvalidDataException {
		if (email == null || email.isEmpty() || !emailValidator.isValid(email)) {
			if ((firstname == null || firstname.isEmpty() || !nameValidator
					.isValid(firstname))
					|| (surname == null || surname.isEmpty() || !nameValidator
							.isValid(surname))) {
				throw new InvalidDataException();
			}
		}

		String selectQuery = "SELECT u" + "FROM User u" + "WHERE ";
		String emailClause = "u.email =:email";
		String usernameClause = "u.name.getFirstname() =:firstname AND"
				+ "u.name.getSurname() =:surname"
				+ "ORDER BY u.name.getSurname(), u.name.getFirstname()";

		Query query = null;
		if (emailValidator.isValid(email)) {
			query = em.createQuery(selectQuery + emailClause);
		} else {
			query = em.createQuery(selectQuery + usernameClause);
		}

		query.setFirstResult((page * ENTRIES_PER_PAGE) - 1);
		query.setMaxResults(ENTRIES_PER_PAGE);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByAbility(String userId, String abilityName,
			String location, int minFeedback, int page)
			throws LocationMissingException, InvalidDataException,
			NoSuchUserException {
		if (abilityName == null) {
			throw new IllegalArgumentException();
		}

		Ability ability = em.find(Ability.class, abilityName);
		if (ability == null) {
			throw new InvalidDataException();
		}

		User user = null;
		if (userId != null) {
			user = em.find(User.class, Long.parseLong(userId));
			if (user == null || user.getStatus() != Status.REGISTERED) {
				throw new NoSuchUserException();
			}
		}

		Address address;
		if (location != null) {
			address = new Address(location);
			InvalidValue[] validationMessages = addressValidator
					.getInvalidValues(address);
			if (validationMessages.length != 0) {
				throw new InvalidDataException(validationMessages);
			}
		} else {
			if (user == null) {
				throw new LocationMissingException();
			}
			address = user.getAddress();
		}

		if (minFeedback < -5 || minFeedback > 5) {
			minFeedback = -5;
		}

		if (page <= 0) {
			page = 1;
		}

		List<User> returnList = new ArrayList<User>();

		String selectQuery = "SELECT u" + "FROM User u" + "WHERE ";
		String userClause = "u.status = it.polimi.ingsw2.swim.entities.User.Status.REGISTERED AND ";
		String friendClause = ":user IN i.friendships AND ";

		// TODO: Full georeferenced filtering cannot be implemented at the
		// moment due to main geolocation services free license restrictions.
		String locationClause = "u.address.getCity() = :address AND ";

		String feedbackClause = "u.evaluation >= :minFeedback AND ";
		String abilityClause = ":ability IN u.abilities ";
		String orderByClause = "ORDER BY u.evaluation DESC ";

		int results = 0;
		if (user != null) {
			Query friendsQuery = em.createQuery(selectQuery + userClause
					+ friendClause + locationClause + feedbackClause
					+ abilityClause + orderByClause);
			friendsQuery.setParameter("user", user);

			// TODO: Full georeferenced filtering cannot be implemented at the
			// moment due to main geolocation services free license
			// restrictions.
			friendsQuery.setParameter("address", address.getCity());
			friendsQuery.setParameter("minFeedback", minFeedback);
			friendsQuery.setParameter("ability", ability);

			if (friendsQuery.getResultList().size() - (page * ENTRIES_PER_PAGE) > 0) {
				friendsQuery.setFirstResult((page * ENTRIES_PER_PAGE) - 1);
				friendsQuery.setMaxResults(ENTRIES_PER_PAGE);

				List<User> friends = friendsQuery.getResultList();
				results = friends.size();

				returnList.addAll(friends);
			}
		}

		Query query = em.createQuery(selectQuery + userClause + locationClause
				+ feedbackClause + abilityClause + orderByClause);

		// TODO: Full georeferenced filtering cannot be implemented at the
		// moment due to main geolocation services free license
		// restrictions.
		query.setParameter("address", address.getCity());
		query.setParameter("minFeedback", minFeedback);
		query.setParameter("ability", ability);
		query.setFirstResult((page * ENTRIES_PER_PAGE) - results - 1);
		query.setMaxResults(ENTRIES_PER_PAGE - results);

		returnList.addAll(query.getResultList());

		return returnList;
	}

}
