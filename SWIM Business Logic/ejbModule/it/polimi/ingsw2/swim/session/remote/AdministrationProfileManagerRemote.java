package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.exceptions.DuplicateAdministratorException;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AdministrationProfileManagerRemote {

	List<Administrator> retriveList();

	Administrator retrive(String adminId) throws NoSuchUserException;

	void add(String username, String email, String password)
			throws InvalidDataException, DuplicateAdministratorException;

	void delete(String adminId) throws NoSuchUserException;

	void editProfile(String userId, String username, String email)
			throws NoSuchUserException, InvalidDataException;

	void sendMessage(String senderId, String addresseeId, String text)
			throws NoSuchUserException;

	void insertNewPassword(String adminId, String oldPassword,
			String newPassword) throws InvalidPasswordException,
			NoSuchUserException;

}
