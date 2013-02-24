package it.polimi.ingsw2.swim.session.local;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

public interface ProfileManagerLocal {

	User getUserWithFriends(String userId) throws NoSuchUserException;

	User getUserWithAbilities(String userId) throws NoSuchUserException;

	User getUserWithNotifications(String userId) throws NoSuchUserException;

	User retriveProfile(String userId) throws NoSuchUserException;
	
	
}
