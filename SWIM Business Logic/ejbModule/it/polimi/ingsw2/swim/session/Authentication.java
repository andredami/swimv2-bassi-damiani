package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.Mailer.MessageType;
import it.polimi.ingsw2.swim.session.remote.AuthenticationRemote;
import it.polimi.ingsw2.swim.util.DAO;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * Session Bean implementation class Authentication
 */
@Stateless
public class Authentication implements AuthenticationRemote {

	private static final EntityManager em = DAO.getInstance()
			.getEntityManager();

	/**
	 * Default constructor.
	 */
	public Authentication() {
		super();
	}

	public boolean authenticate(String email, String password) {
		User user;
		try {
			user = (User) em.createNamedQuery("getUserByEmail")
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return false;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException();
		}

		if (user.getStatus() != User.Status.REGISTERED) {
			return false;
		}

		if (!user.checkPassword(password)) {
			em.merge(user);
			return false;
		}

		return true;
	}

	public void generateTemporaryPassword(String email)
			throws NoSuchUserException {
		User user;
		try {
			user = (User) em.createNamedQuery("getUserByEmail")
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			throw new NoSuchUserException();
		} catch (NonUniqueResultException e) {
			throw new RuntimeException();
		}

		if (user.getStatus() != User.Status.REGISTERED) {
			throw new NoSuchUserException();
		}

		String temporaryPassword = user.setTemporaryPassword();
		em.merge(user);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("temp", temporaryPassword);
		new Mailer().sendApplicationEmail(user.getEmail(),
				MessageType.PASSWORD_RECOVERY, params);
	}
}
