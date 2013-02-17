package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.TempUser;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;

import java.util.Calendar;

public interface RegistrationRemote {

	void abort();

	void createUser(String password, String email, String firstname,
			String surname, Calendar birthdate, Gender gender)
			throws InvalidDataException, UserAlreadyExistsException;

	User registerUser(String[] entries) throws InvalidDataException,
			UserAlreadyExistsException;

	void sendActivationEmail();

	TempUser getTempUser();

}
