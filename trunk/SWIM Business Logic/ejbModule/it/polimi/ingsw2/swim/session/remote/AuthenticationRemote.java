package it.polimi.ingsw2.swim.session.remote;

public interface AuthenticationRemote {

	public boolean authenticate(String email, String password);
	
}
