package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

public interface AbilityRequestRemote {

	void registerNewRequest(String id, String abilityName, String description)
			throws UserDoesNotExixtException;

	void registerSubscription(String id, String abilityName)
			throws UserDoesNotExixtException, InvalidDataException;

}
