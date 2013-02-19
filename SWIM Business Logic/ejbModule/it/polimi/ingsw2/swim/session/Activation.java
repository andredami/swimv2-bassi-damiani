package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.entities.User.Status;
import it.polimi.ingsw2.swim.exceptions.InvalidActivationCode;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.Mailer.MessageType;
import it.polimi.ingsw2.swim.session.local.MailerLocal;
import it.polimi.ingsw2.swim.session.remote.ActivationRemote;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Activation
 */
@Stateless
@Remote
public class Activation implements ActivationRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public Activation() {
		super();
	}

	@Override
    public void activateUser(String userId, String activationToken) throws NoSuchUserException, InvalidActivationCode {
		try{
			User user = (User) em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.abilities WHERE u.id =:id").setParameter("id", Long.parseLong(userId)).getSingleResult();
			user.confirmRegistration(activationToken);
			em.merge(user);
		} catch(NoResultException e){
        	throw new NoSuchUserException();
        } catch(IllegalStateException e){
        }
    }

	public void sendActivationEmail(User user) {
		if (user.getStatus() == Status.REGISTERED) {
			return;
		}

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			MailerLocal mailer = (MailerLocal) ctx.lookup("Mailer/local");
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("id", user.getId().toString());
			parameters.put("userFirstname", user.getName().toString());
			parameters.put("activationToken", user.getActivationCode());
			mailer.sendApplicationEmail(user.getEmail(),
					MessageType.REGISTRATION_CONFIRMATION, parameters);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
