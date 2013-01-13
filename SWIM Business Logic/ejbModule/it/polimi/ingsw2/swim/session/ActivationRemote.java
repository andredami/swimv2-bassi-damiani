package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.exceptions.InvalidActivationCode;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

import javax.ejb.Remote;

@Remote
public interface ActivationRemote {

	void activateUser(String userId, String activationToken)
			throws UserDoesNotExixtException, InvalidActivationCode;

}
