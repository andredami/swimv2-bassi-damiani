package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

import javax.ejb.Remote;

@Remote
public interface AbilityRequestRemote {

	void registerSubscription(String id, String abilityName)
			throws UserDoesNotExixtException, InvalidDataException;

	void registerNewRequest(String id, String abilityName, String description)
			throws UserDoesNotExixtException;

}
