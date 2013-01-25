package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

public interface FriendshipManagerRemote {

	void confirm(String userId, String friendshipId)
			throws InvalidDataException;

	void deny(String userId, String friendshipId) throws InvalidDataException;

	void remove(String userId, String friendToBeRemovedId)
			throws NoSuchUserException;

	void ask(String addresseeId, String senderId) throws NoSuchUserException;

}
