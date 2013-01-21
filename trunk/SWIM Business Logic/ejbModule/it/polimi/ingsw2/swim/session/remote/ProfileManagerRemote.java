package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.LastAbilityDeletionException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

import java.util.Set;

public interface ProfileManagerRemote {

	void addAbilities(String userId, Set<String> abilities)
			throws NoSuchUserException;

	void deleteUser(String userId) throws NoSuchUserException;

	void editProfile(String userId, String email, String picture,
			String street, String streetNumber, String zip, String city,
			String province, String telephone, String mobile, String fax,
			String skype) throws NoSuchUserException,
			InvalidDataException;

	User getUserWithAbilities(String userId) throws NoSuchUserException;

	User getUserWithFriends(String userId) throws NoSuchUserException;

	User getUserWithNotifications(String userId)
			throws NoSuchUserException;

	void insertNewPassword(String userId, String oldPassword, String newPassword)
			throws NoSuchUserException, InvalidPasswordException;

	void removeAbilities(String userId, Set<String> abilities)
			throws NoSuchUserException, LastAbilityDeletionException;

	User retriveProfile(String userId) throws NoSuchUserException;

}
