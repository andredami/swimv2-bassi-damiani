package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.session.Mailer.MessageType;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.entities.User.Status;
import it.polimi.ingsw2.swim.exceptions.InvalidActivationCode;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Activation
 */
@Stateless
public class Activation implements ActivationRemote {

	@PersistenceContext(unitName = "persistentData")
	private static EntityManager em;
	
    /**
     * Default constructor. 
     */
	public Activation(){
		super();
	}
	
	@Override
    public void activateUser(String userId, String activationToken) throws UserDoesNotExixtException, InvalidActivationCode {
        User user = em.find(User.class, Long.parseLong(userId));
        if(user == null){
        	throw new UserDoesNotExixtException();
        }
        user.confirmRegistration(activationToken);
        em.merge(user);
    }

    
    public void sendActivationEmail(User user) {
		if (user.getStatus() == Status.REGISTERED) {
			return;
		}

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", user.getId().toString());
		parameters.put("userFirstname", user.getName().toString());
		parameters.put("activationToken", user.getActivationCode());

		Mailer mailer = new Mailer();
		mailer.sendApplicationEmail(user.getEmail(),
				MessageType.REGISTRATION_CONFIRMATION, parameters);
	}
}
