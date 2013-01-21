package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.LocationMissingException;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

import java.util.List;

public interface UserDirectoryManagerRemote {

	List<User> findUserByAbility(String userId, String abilityName,
			String location, int minFeedback, int page)
			throws LocationMissingException, InvalidDataException,
			UserDoesNotExixtException;

	List<User> findUserByNameAndEmail(String firstname, String surname,
			String email, int page) throws InvalidDataException;

}
