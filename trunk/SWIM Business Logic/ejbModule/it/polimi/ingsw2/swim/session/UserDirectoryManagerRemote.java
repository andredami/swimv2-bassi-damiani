package it.polimi.ingsw2.swim.session;

import java.util.List;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.LocationMissingException;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

import javax.ejb.Remote;

@Remote
public interface UserDirectoryManagerRemote {

	List<User> findUserByNameAndEmail(String firstname, String surname,
			String email, int page) throws InvalidDataException;

	List<User> findUserByAbility(String userId, String abilityName,
			String location, int minFeedback, int page)
			throws LocationMissingException, InvalidDataException,
			UserDoesNotExixtException;

}
