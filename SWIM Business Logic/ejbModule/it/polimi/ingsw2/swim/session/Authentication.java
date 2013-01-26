package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.Mailer.MessageType;
import it.polimi.ingsw2.swim.session.local.MailerLocal;
import it.polimi.ingsw2.swim.session.remote.AuthenticationRemote;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Authentication
 */
@Stateless
@Remote
public class Authentication implements AuthenticationRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public Authentication() {
		super();
	}

	public User authenticate(String email, String password) throws NoSuchUserException {
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

		if (!user.checkPassword(password)) {
			em.merge(user);
			throw new NoSuchUserException();
		}

		return user;
	}

	@Override
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
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			MailerLocal mailer = (MailerLocal) ctx.lookup("Mailer/local");
			Map<String, String> params = new HashMap<String, String>();
			params.put("temp", temporaryPassword);
			mailer.sendApplicationEmail(user.getEmail(),
					MessageType.PASSWORD_RECOVERY, params);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
