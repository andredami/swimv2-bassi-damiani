package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.LastAbilityDeletionException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.exceptions.NotAuthorizedException;

import java.util.Set;

public interface ProfileManagerRemote {

	void addAbilities(String userId, Set<String> abilities, String askingUserId)
			throws NoSuchUserException, NotAuthorizedException;

	void deleteUser(String userId, String askingUserId) throws NoSuchUserException, NotAuthorizedException;

	void editProfile(String userId, String email, String picture,
			String street, String streetNumber, String zip, String city,
			String province, String telephone, String mobile, String fax,
			String skype, String askingUserId) throws NoSuchUserException,
			InvalidDataException, NotAuthorizedException;

	User getUserWithAbilities(String userId, String askingUserId) throws NoSuchUserException, NotAuthorizedException;

	User getUserWithFriends(String userId, String askingUserId) throws NoSuchUserException, NotAuthorizedException;

	User getUserWithNotifications(String userId, String askingUserId)
			throws NoSuchUserException, NotAuthorizedException;

	void insertNewPassword(String userId, String oldPassword, String newPassword)
			throws NoSuchUserException, InvalidPasswordException, NotAuthorizedException;

	void removeAbilities(String userId, Set<String> abilities, String askingUserId)
			throws NoSuchUserException, LastAbilityDeletionException, NotAuthorizedException;

	User retriveProfile(String userId, String askingUserId) throws NoSuchUserException, NotAuthorizedException;

	User getUser(String userId)
			throws NoSuchUserException, NotAuthorizedException;

}
