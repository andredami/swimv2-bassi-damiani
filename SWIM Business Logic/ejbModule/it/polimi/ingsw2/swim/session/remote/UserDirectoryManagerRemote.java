package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

import java.util.List;

public interface UserDirectoryManagerRemote {

	List<User> findUserByAbility(String userId, String abilityName,
			String location, int minFeedback, int page)
			throws InvalidDataException,
			NoSuchUserException;

	List<User> findUserByNameAndEmail(String firstname, String surname,
			String email, int page) throws InvalidDataException;

}
