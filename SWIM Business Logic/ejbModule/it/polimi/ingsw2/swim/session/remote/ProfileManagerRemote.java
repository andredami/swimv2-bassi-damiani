package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.LastAbilityDeletionException;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

import java.util.Set;

public interface ProfileManagerRemote {

	void addAbilities(String userId, Set<String> abilities)
			throws UserDoesNotExixtException;

	void deleteUser(String userId) throws UserDoesNotExixtException;

	void editProfile(String userId, String email, String picture,
			String street, String streetNumber, String zip, String city,
			String province, String telephone, String mobile, String fax,
			String skype) throws UserDoesNotExixtException,
			InvalidDataException;

	User getUserWithAbilities(String userId) throws UserDoesNotExixtException;

	User getUserWithFriends(String userId) throws UserDoesNotExixtException;

	User getUserWithNotifications(String userId)
			throws UserDoesNotExixtException;

	void insertNewPassword(String userId, String oldPassword, String newPassword)
			throws UserDoesNotExixtException, InvalidPasswordException;

	void removeAbilities(String userId, Set<String> abilities)
			throws UserDoesNotExixtException, LastAbilityDeletionException;

	User retriveProfile(String userId) throws UserDoesNotExixtException;

}
