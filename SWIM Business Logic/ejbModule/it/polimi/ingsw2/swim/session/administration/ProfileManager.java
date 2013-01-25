package it.polimi.ingsw2.swim.session.administration;

import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.exceptions.DuplicateAdministratorException;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.Mailer;
import it.polimi.ingsw2.swim.session.remote.AdministrationProfileManagerRemote;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

/**
 * Session Bean implementation class ProfileManager
 */
@Stateless(name = "AdministrationProfileManager")
@Remote
public class ProfileManager implements AdministrationProfileManagerRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;

	private static final ClassValidator<Administrator> adminValidator = new ClassValidator<Administrator>(
			Administrator.class);

	/**
	 * Default constructor.
	 */
	public ProfileManager() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Administrator> retriveList() {
		return em.createQuery("SELECT b FROM Administrator b").getResultList();
	}

	@Override
	public Administrator retrive(String adminId) throws NoSuchUserException {
		Administrator admin = em.find(Administrator.class,
				Long.parseLong(adminId));
		if (admin == null) {
			throw new NoSuchUserException();
		}
		return admin;
	}
	
	@Override
	public void add(String username, String email, String password)
			throws InvalidDataException, DuplicateAdministratorException {
		try {
			em.createNamedQuery("getAdministratorByUsername")
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			Administrator admin = new Administrator(username, email, password);
			InvalidValue[] validatorMessages = adminValidator
					.getInvalidValues(admin);

			if (validatorMessages.length > 0) {
				throw new InvalidDataException(validatorMessages);
			}
			
			em.persist(admin);
			return;
		}

		throw new DuplicateAdministratorException();
	}

	@Override
	public void delete(String adminId) throws NoSuchUserException {
		Administrator admin = retrive(adminId);
		em.remove(admin);
	}

	@Override
	public void editProfile(String userId, String username, String email)
			throws NoSuchUserException, InvalidDataException {
		Administrator admin = retrive(userId);
		boolean correctMail = false;
		if (email != null && !admin.getEmail().equals(email.toLowerCase())) {
			try {
				em.createNativeQuery("getAdministratorByEmail")
						.setParameter("email", email).getSingleResult();
			} catch (NoResultException e) {
				correctMail = true;
			}
		} else {
			correctMail = true;
		}
		if (correctMail && email != null && !email.isEmpty()) {
			admin.setEmail(email);
		}
		boolean correctUsername = false;
		if (email != null && !admin.getUsername().equals(username)) {
			try {
				em.createNativeQuery("getAdministratorByUsername")
						.setParameter("username", email).getSingleResult();
			} catch (NoResultException e) {
				correctUsername = true;
			}
		} else {
			correctUsername = true;
		}
		if (correctUsername && username != null && !username.isEmpty()) {
			admin.setUsername(username);
		}

		InvalidValue[] validationMessages = adminValidator
				.getInvalidValues(admin);

		if (validationMessages.length != 0) {
			throw new InvalidDataException(validationMessages);
		}

		em.merge(admin);
	}
	
	@Override
	public void sendMessage(String senderId, String addresseeId, String text) throws NoSuchUserException{
		if(text == null || text.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		Administrator sender = em.find(Administrator.class, Long.parseLong(senderId));
		if(sender == null){
			throw new NoSuchUserException("fromUser");
		}
		
		Administrator addressee = em.find(Administrator.class, Long.parseLong(addresseeId));
		if(addressee == null){
			throw new NoSuchUserException("toUser");
		}
		
		new Mailer().sendEmail(addressee.getEmail(), "[SWIM] L'amminsitratore "+sender.getUsername()+" ti ha scritto.", text);
	}
	
	@Override
	public void insertNewPassword(String adminId, String oldPassword, String newPassword) throws InvalidPasswordException, NoSuchUserException{
		Administrator admin = retrive(adminId);
		admin.setPassword(oldPassword, newPassword);
	}
}
