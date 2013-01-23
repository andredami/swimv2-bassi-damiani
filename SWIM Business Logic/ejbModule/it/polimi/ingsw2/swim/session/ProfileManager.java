package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.LastAbilityDeletionException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.ProfileManagerRemote;

import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

/**
 * Session Bean implementation class ProfileManager
 */
@Stateless
@Remote
public class ProfileManager implements ProfileManagerRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;
	
	private static final ClassValidator<User> userValidator = new ClassValidator<User>(
			User.class);

	/**
	 * Default constructor.
	 */
	public ProfileManager() {
		super();
	}

	@Override
	public User getUserWithFriends(String userId) throws NoSuchUserException {
		try {
			return (User) em.createNamedQuery("getUserWithFriends")
					.setParameter("id", userId).getSingleResult();
		} catch (NoResultException e) {
			throw new NoSuchUserException();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoSuchUserException();
		}
	}

	@Override
	public User getUserWithAbilities(String userId) throws NoSuchUserException {
		try {
			return (User) em.createNamedQuery("getUserWithAbilities")
					.setParameter("id", userId).getSingleResult();
		} catch (NoResultException e) {
			throw new NoSuchUserException();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoSuchUserException();
		}
	}

	@Override
	public User getUserWithNotifications(String userId)
			throws NoSuchUserException {
		try {
			return (User) em.createNamedQuery("getUserWithNotifications")
					.setParameter("id", userId).getSingleResult();
		} catch (NoResultException e) {
			throw new NoSuchUserException();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoSuchUserException();
		}
	}

	@Override
	public User retriveProfile(String userId) throws NoSuchUserException {
		try {
			return (User) em.createNamedQuery("getCompleteUser")
					.setParameter("id", userId).getSingleResult();
		} catch (NoResultException e) {
			throw new NoSuchUserException();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoSuchUserException();
		}
	}

	@Override
	public void editProfile(String userId, String email, String picture,
			String street, String streetNumber, String zip, String city,
			String province, String telephone, String mobile, String fax,
			String skype) throws NoSuchUserException, InvalidDataException {
		User user = retriveProfile(userId);
		boolean correctMail = false;
		if (email != null && !user.getEmail().equals(email.toLowerCase())) {
			try {
				em.createNativeQuery("getUserByEmail")
						.setParameter("email", email).getSingleResult();
			} catch (NoResultException e) {
				correctMail = true;
			}
		} else {
			correctMail = true;
		}

		if (correctMail && email != null && !email.isEmpty()) {
			user.setEmail(email);
		}

		user.setPicture(picture);
		user.setAddress(street, streetNumber, zip, city, province);
		user.setTelephone(telephone);
		user.setMobile(mobile);
		user.setFax(fax);
		user.setSkype(skype);

		InvalidValue[] validationMessages = userValidator
				.getInvalidValues(user);

		if (validationMessages.length != 0) {
			throw new InvalidDataException(validationMessages);
		}

		em.merge(user);
	}

	@Override
	public void insertNewPassword(String userId, String oldPassword,
			String newPassword) throws NoSuchUserException,
			InvalidPasswordException {
		User user = retriveProfile(userId);
		user.setPassword(oldPassword, newPassword);
	}

	@Override
	public void deleteUser(String userId) throws NoSuchUserException {
		User user = retriveProfile(userId);
		em.remove(user);
	}

	@Override
	public void removeAbilities(String userId, Set<String> abilities)
			throws NoSuchUserException, LastAbilityDeletionException {
		User user = getUserWithAbilities(userId);
		for (String abilityName : abilities) {
			Ability ability = em.find(Ability.class, abilityName);
			if (ability != null) {
				user.removeAbility(ability);
			}
		}

		em.merge(user);
	}

	@Override
	public void addAbilities(String userId, Set<String> abilities)
			throws NoSuchUserException {
		User user = getUserWithAbilities(userId);
		for (String abilityName : abilities) {
			Ability ability = em.find(Ability.class, abilityName);
			if (ability != null) {
				user.addAbility(ability);
			}
		}

		em.merge(user);
	}

}
