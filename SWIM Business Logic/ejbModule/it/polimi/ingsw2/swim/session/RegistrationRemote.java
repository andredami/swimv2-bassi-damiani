package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserAlreadyExistsException;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Remove;

import org.jboss.ejb3.annotation.CacheConfig;

@Remote
@CacheConfig(idleTimeoutSeconds = 300, removalTimeoutSeconds = 0)
public interface RegistrationRemote {

	void createUser(String password, String email, String firstname,
			String surname, Date birthdate, Gender gender)
			throws InvalidDataException, UserAlreadyExistsException;

	void registerUser(String[] entries) throws InvalidDataException,
			UserAlreadyExistsException;

	@Remove
	void abort();

	void sendActivationEmail();
}
