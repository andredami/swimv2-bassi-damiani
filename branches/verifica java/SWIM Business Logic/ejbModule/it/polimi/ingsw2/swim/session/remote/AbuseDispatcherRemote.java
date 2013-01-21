package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.exceptions.InvalidDataException;

public interface AbuseDispatcherRemote {

	void registerAbuse(String email, String description)
			throws InvalidDataException;

}
