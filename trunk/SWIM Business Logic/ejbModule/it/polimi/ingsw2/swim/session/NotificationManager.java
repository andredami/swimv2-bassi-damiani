package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.FriendshipRequest;
import it.polimi.ingsw2.swim.entities.Help;
import it.polimi.ingsw2.swim.entities.Message;
import it.polimi.ingsw2.swim.entities.Notification;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.local.ProfileManagerLocal;
import it.polimi.ingsw2.swim.session.remote.NotificationManagerRemote;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class NotificationManager
 */
@Stateless
@Remote
public class NotificationManager implements NotificationManagerRemote {

	@Resource(name="maxNotification", mappedName="maxNotification")
	private static int  MAX_NOTIFICATION = 25;

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em; 
	
	
    /**
     * Default constructor. 
     */
    public NotificationManager() {
        super();
    }

    @Override
	public void sendNotification(String addresseeId, String text) throws NoSuchUserException {
		User addressee;
		addressee = getProfileManager().getUserWithNotifications(addresseeId);
		addressee.addNotification(new Notification(addressee, text));
		em.merge(addressee);
	}
	
	void sendFriendshipRequest(String addresseeId, String senderId) throws NoSuchUserException {
		User sender;
		try {
			sender = getProfileManager().getUserWithNotifications(addresseeId);
		} catch (NoSuchUserException e) {
			throw new NoSuchUserException("fromUser");
		}
		User addressee;
		try {
			addressee = getProfileManager().getUserWithNotifications(addresseeId);
		} catch (NoSuchUserException e) {
			throw new NoSuchUserException("toUser");
		}
		if(!sender.getFriendships().contains(addressee) && !addressee.getFriendships().contains(sender)){
			addressee.addNotification(new FriendshipRequest(addressee,sender));
			em.merge(addressee);
		}
	}
	
	@Override
	public void markNotificationAsRead(String notificationId){
		Notification notification = em.find(Notification.class, Long.parseLong(notificationId));
		if(notification != null){
			notification.setAsRead();
			em.merge(notification);
		}
	}
	
	@Override
	public List<Notification> retriveNotificationsByUser(String userId) throws NoSuchUserException{
		User user = getProfileManager().getUserWithNotifications(userId);
		List<Notification> allNotifications = user.getNotification();
		List<Notification> output = new LinkedList<Notification>();
		int count = 0;
		Iterator<Notification> iterator = allNotifications.iterator();
		while(count < MAX_NOTIFICATION && iterator.hasNext()){
			Notification notification = iterator.next();
			if(notification.getClass() == Notification.class && !notification.isRead()){
				output.add(notification);
				count++;
			}
		}
		return output;
	}
	
	@Override
	public List<Message> retriveIncomingMessagesByUser(String userId) throws NoSuchUserException{
		User user = getProfileManager().getUserWithNotifications(userId);
		List<Notification> allNotifications = user.getNotification();
		List<Message> output = new LinkedList<Message>();
		Iterator<Notification> iterator = allNotifications.iterator();
		while(iterator.hasNext()){
			Notification notification = iterator.next();
			if(notification.getClass() == Message.class && !notification.isRead()){
				output.add((Message) notification);
			}
		}
		return output;
	}
	
	@Override
	public List<FriendshipRequest> retriveFriendshipRequestsByUser(String userId) throws NoSuchUserException{
		User user = getProfileManager().getUserWithNotifications(userId);
		List<Notification> allNotifications = user.getNotification();
		List<FriendshipRequest> output = new LinkedList<FriendshipRequest>();
		Iterator<Notification> iterator = allNotifications.iterator();
		while(iterator.hasNext()){
			Notification notification = iterator.next();
			if(notification.getClass() == FriendshipRequest.class && !notification.isRead()){
				output.add((FriendshipRequest) notification);
			}
		}
		return output;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Help> retriveHelpRelationStatusByUser(String userId) throws NoSuchUserException{
		return em.createNamedQuery("getActiveHelpByUser").setParameter("user", Long.parseLong(userId)).getResultList();
	}
	
	private ProfileManagerLocal getProfileManager(){
		try {
			return (ProfileManagerLocal) (new InitialContext()).lookup("ProfileManager/local");
		} catch (NamingException e) {
			throw new RuntimeException();
		}
	}
}
