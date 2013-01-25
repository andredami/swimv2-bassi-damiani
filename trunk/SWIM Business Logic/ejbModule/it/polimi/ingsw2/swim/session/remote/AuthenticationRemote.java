package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

public interface AuthenticationRemote {

	public User authenticate(String email, String password) throws NoSuchUserException;

	void generateTemporaryPassword(String email) throws NoSuchUserException;
	
}
