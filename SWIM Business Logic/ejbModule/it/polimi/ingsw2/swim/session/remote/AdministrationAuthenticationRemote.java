package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.Administrator;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

import javax.ejb.Remote;

@Remote
public interface AdministrationAuthenticationRemote {

	Administrator authenticate(String username, String password) throws NoSuchUserException;

}
