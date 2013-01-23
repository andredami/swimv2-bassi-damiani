package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.FriendshipRequest;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.FriendshipManagerRemote;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class FriendshipManager
 */
@Stateless
@Remote
public class FriendshipManager implements FriendshipManagerRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;	
	
    /**
     * Default constructor. 
     */
    public FriendshipManager() {
        super();
    }

    public void ask(String addresseeId, String senderId) throws NoSuchUserException{
    	if(addresseeId.equals(senderId)){
    		return;
    	}
    	User sender;
    	try {
			sender = new ProfileManager().getUserWithFriends(addresseeId);
		} catch (NoSuchUserException e) {
			throw new NoSuchUserException("fromUser");
		}
    	User addressee;
    	try {
    		addressee = new ProfileManager().getUserWithFriends(addresseeId);
		} catch (NoSuchUserException e) {
			throw new NoSuchUserException("toUser");
		}
    	
		if(!sender.getFriendships().contains(addressee) && !addressee.getFriendships().contains(sender)){
			new NotificationManager().sendFriendshipRequest(addressee.getId().toString(),sender.getId().toString());
		} else if(sender.getFriendships().contains(addressee) && addressee.getFriendships().contains(sender)){
			return;
		} else {
			sender.addFriendship(addressee);
			addressee.addFriendship(sender);
    		em.merge(sender);
    		em.merge(addressee);
		}
    }
    
    public void confirm(String userId, String friendshipId) throws InvalidDataException{
    	FriendshipRequest request = em.find(FriendshipRequest.class, Long.parseLong(friendshipId));
    	if(request == null){
    		throw new InvalidDataException();
    	}
    	if(request.getAddressee().getId().equals(Long.parseLong(userId))){
        	User sender;
        	User addressee;
    		try {
        		sender = new ProfileManager().getUserWithFriends(request.getSender().getId().toString());
				addressee = new ProfileManager().getUserWithFriends(request.getAddressee().getId().toString());
			} catch (NoSuchUserException e) {
				throw new RuntimeException();
			}
    		sender.addFriendship(addressee);
    		addressee.addFriendship(sender);
    		em.merge(sender);
    		em.merge(addressee);
    		em.remove(request);
    		try {
				new NotificationManager().sendNotification(sender.getId().toString(), "L'utente " + addressee.getName() + " ha accettato la tua richiesta di amicizia.");
			} catch (NoSuchUserException e) {
				throw new RuntimeException();
			}    		
    	}
    }
    
    public void deny(String userId, String friendshipId) throws InvalidDataException{
    	FriendshipRequest request = em.find(FriendshipRequest.class, Long.parseLong(friendshipId));
    	if(request == null){
    		throw new InvalidDataException();
    	}
    	if(request.getAddressee().getId().equals(Long.parseLong(userId))){
        	User sender;
        	User addressee;
    		try {
        		sender = new ProfileManager().getUserWithFriends(request.getSender().getId().toString());
				addressee = new ProfileManager().getUserWithFriends(request.getAddressee().getId().toString());
			} catch (NoSuchUserException e) {
				throw new RuntimeException();
			}
    		sender.removeFriendships(addressee);
    		addressee.removeFriendships(sender);
    		em.merge(sender);
    		em.merge(addressee);
    		em.remove(request);    		
    	}
    }
    
    public void remove(String userId, String friendToBeRemovedId) throws NoSuchUserException{
    	if(userId.equals(friendToBeRemovedId)){
    		return;
    	}
    	User user;
    	try {
			user = new ProfileManager().getUserWithFriends(userId);
		} catch (NoSuchUserException e) {
			throw new NoSuchUserException();
		}
    	User friendToBeRemoved;
    	try {
    		friendToBeRemoved = new ProfileManager().getUserWithFriends(friendToBeRemovedId);
		} catch (NoSuchUserException e) {
			return;
		}
    	user.removeFriendships(friendToBeRemoved);
    	friendToBeRemoved.removeFriendships(user);
   		em.merge(user);
   		em.merge(friendToBeRemoved); 		
    }
}
