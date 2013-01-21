package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;

import java.util.Date;

public interface RegistrationRemote {

	void abort();

	void createUser(String password, String email, String firstname,
			String surname, Date birthdate, Gender gender)
			throws InvalidDataException, UserAlreadyExistsException;

	void registerUser(String[] entries) throws InvalidDataException,
			UserAlreadyExistsException;

	void sendActivationEmail();

}
