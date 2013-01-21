package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.exceptions.InvalidActivationCode;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

public interface ActivationRemote {

	void activateUser(String userId, String activationToken)
			throws UserDoesNotExixtException, InvalidActivationCode;

}
