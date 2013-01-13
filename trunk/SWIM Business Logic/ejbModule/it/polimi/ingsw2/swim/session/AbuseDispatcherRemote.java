package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.exceptions.InvalidDataException;

import javax.ejb.Remote;

@Remote
public interface AbuseDispatcherRemote {

	void registerAbuse(String email, String description)
			throws InvalidDataException;

}
