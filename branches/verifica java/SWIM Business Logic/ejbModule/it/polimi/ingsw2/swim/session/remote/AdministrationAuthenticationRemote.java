package it.polimi.ingsw2.swim.session.remote;

import javax.ejb.Remote;

@Remote
public interface AdministrationAuthenticationRemote {

	boolean authenticate(String username, String password);

}
