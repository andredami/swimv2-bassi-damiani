package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Help;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.LastAbilityDeletionException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.exceptions.NotAuthorizedException;
import it.polimi.ingsw2.swim.session.remote.ProfileManagerRemote;

import java.util.List;
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

	private void gainStrictAuthorization(String userId, String askingUserId) throws NotAuthorizedException{
		if(!userId.equals(askingUserId)){
			throw new NotAuthorizedException();
		}
	}

	private void gainAuthorization(String userId, String askingUserId)
			throws NoSuchUserException, NotAuthorizedException {
		if(userId.equals(askingUserId)){
			return;
		}
		
		User returned = (User) em.find(User.class, Long.parseLong(userId));
		if(returned == null){
			throw new NoSuchUserException();
		}

		User asker;
		try{
		asker = (User) em.createNamedQuery("getUserWithFriends")
				.setParameter("id", askingUserId).getSingleResult();
		} catch (NoResultException e) {
			throw new NoSuchUserException();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new NoSuchUserException();
		}

		boolean auth = false;

		for (User u : asker.getFriendships()) {
			if (u.equals(returned)) {
				auth = true;
				break;
			}
		}

		if (auth == false) {
			@SuppressWarnings("unchecked")
			List<Help> helps = em.createNamedQuery("getActiveHelpByUsers")
					.setParameter("usera", returned.getId())
					.setParameter("userb", asker.getId()).getResultList();
			if(helps.size()>0){
				auth = true;
			}
		}
		
		
		if(!auth){
			throw new NotAuthorizedException();
		}
	}
	
	@Override
	public User getUserWithFriends(String userId, String askingUserId)
			throws NoSuchUserException, NotAuthorizedException {
		
		gainAuthorization(userId, askingUserId);

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
	public User getUserWithAbilities(String userId, String askingUserId)
			throws NoSuchUserException, NotAuthorizedException {
		gainAuthorization(userId, askingUserId);
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
	public User getUserWithNotifications(String userId, String askingUserId)
			throws NoSuchUserException, NotAuthorizedException {
		gainAuthorization(userId, askingUserId);
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
	public User retriveProfile(String userId, String askingUserId)
			throws NoSuchUserException, NotAuthorizedException {
		gainAuthorization(userId, askingUserId);
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
	
	
	User getUserWithFriends(String userId)
			throws NoSuchUserException {

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

	User getUserWithAbilities(String userId)
			throws NoSuchUserException {
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

	User getUserWithNotifications(String userId)
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

	User retriveProfile(String userId)
			throws NoSuchUserException {
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
			String skype, String askingUserId) throws NoSuchUserException,
			InvalidDataException, NotAuthorizedException {
		gainStrictAuthorization(userId, askingUserId);
		User user = retriveProfile(userId, askingUserId);
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
			InvalidPasswordException, NotAuthorizedException {
		User user = retriveProfile(userId, userId);
		user.setPassword(oldPassword, newPassword);
	}

	@Override
	public void deleteUser(String userId, String askingUserId)
			throws NoSuchUserException, NotAuthorizedException {
		gainStrictAuthorization(userId, askingUserId);
		User user = retriveProfile(userId, askingUserId);
		em.remove(user);
	}

	@Override
	public void removeAbilities(String userId, Set<String> abilities,
			String askingUserId) throws NoSuchUserException,
			LastAbilityDeletionException, NotAuthorizedException {
		gainStrictAuthorization(userId, askingUserId);
		User user = getUserWithAbilities(userId, askingUserId);
		for (String abilityName : abilities) {
			Ability ability = em.find(Ability.class, abilityName);
			if (ability != null) {
				user.removeAbility(ability);
			}
		}

		em.merge(user);
	}

	@Override
	public void addAbilities(String userId, Set<String> abilities,
			String askingUserId) throws NoSuchUserException, NotAuthorizedException {
		gainStrictAuthorization(userId, askingUserId);
		User user = getUserWithAbilities(userId, askingUserId);
		for (String abilityName : abilities) {
			Ability ability = em.find(Ability.class, abilityName);
			if (ability != null) {
				user.addAbility(ability);
			}
		}

		em.merge(user);
	}

}
