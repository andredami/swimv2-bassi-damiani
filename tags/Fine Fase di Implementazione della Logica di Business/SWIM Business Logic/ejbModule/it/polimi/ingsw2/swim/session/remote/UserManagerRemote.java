package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface UserManagerRemote {

	List<User> retriveUserList();

	User retriveUserProfile(String userId) throws NoSuchUserException;

	void sendMessage(String userId, String text) throws NoSuchUserException;

	void banUser(String userId);

}
