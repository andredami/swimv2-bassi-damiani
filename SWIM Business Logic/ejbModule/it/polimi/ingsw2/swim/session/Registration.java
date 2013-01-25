package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.FullName;
import it.polimi.ingsw2.swim.entities.TempUser;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;
import it.polimi.ingsw2.swim.session.remote.RegistrationRemote;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.jboss.ejb3.annotation.CacheConfig;

/**
 * Session Bean implementation class Registration
 */
@Stateful
@Remote
@CacheConfig(idleTimeoutSeconds = 60, removalTimeoutSeconds = 6000)
public class Registration implements RegistrationRemote {

	enum State {
		INIT, USER_CREATED, USER_FINALIZED;
	}

	private static ClassValidator<TempUser> tempUserValidator = new ClassValidator<TempUser>(
			TempUser.class);
	private static ClassValidator<User> userValidator = new ClassValidator<User>(
			User.class);

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;

	private TempUser temp;
	private User user;
	private State state = State.INIT;

	/**
	 * Default constructor.
	 */
	public Registration() {
		super();
	}

	@Override
	public void createUser(String password, String email, String firstname,
			String surname, Calendar birthdate, Gender gender)
			throws InvalidDataException, UserAlreadyExistsException {
		email = email.toLowerCase();
		if (state != State.INIT) {
			throw new IllegalStateException();
		}

		temp = new TempUser(password, email, new FullName(firstname, surname),
				birthdate, gender);

		InvalidValue[] validationMessages = tempUserValidator
				.getInvalidValues(temp);

		if (validationMessages.length != 0) {
			temp = null;
			throw new InvalidDataException(validationMessages);
		}

		Query users = em.createQuery("SELECT u" + "FROM User u"
				+ "WHERE u.email =:mail");
		users.setParameter("mail", email);

		if (users.getResultList().isEmpty()) {
			state = State.USER_CREATED;
		} else {
			temp = null;
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public void registerUser(String[] entries) throws InvalidDataException,
			UserAlreadyExistsException {
		if (state != State.USER_CREATED) {
			throw new IllegalStateException();
		}

		if (entries.length == 0) {
			throw new InvalidDataException();
		}

		boolean hasOneAbility = false;
		Set<Ability> abilities = new HashSet<Ability>();

		for (String s : entries) {
			Ability ability = em.find(Ability.class, s);
			if (ability != null && !abilities.contains(ability)) {
				abilities.add(ability);
				hasOneAbility = true;
			}
		}

		if (hasOneAbility) {
			user = new User(temp, abilities);

			InvalidValue[] validationMessages = userValidator
					.getInvalidValues(user);

			if (validationMessages.length != 0) {
				temp = null;
				throw new InvalidDataException(validationMessages);
			}

			try {
				em.persist(user);
			} catch (EntityExistsException e) {
				throw new UserAlreadyExistsException();
			}

			state = State.USER_FINALIZED;

		} else {
			throw new InvalidDataException();
		}
	}

	@Override
	public void sendActivationEmail() {
		Activation activationBean = new Activation();
		activationBean.sendActivationEmail(user);
	}

	@Override
	@Remove
	public void abort() {
		this.temp = null;
		this.user = null;
		state = State.INIT;
	}
}
