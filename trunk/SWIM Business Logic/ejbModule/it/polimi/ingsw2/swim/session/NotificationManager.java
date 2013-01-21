package it.polimi.ingsw2.swim.session;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.polimi.ingsw2.swim.entities.FriendshipRequest;
import it.polimi.ingsw2.swim.entities.Help;
import it.polimi.ingsw2.swim.entities.Message;
import it.polimi.ingsw2.swim.entities.Notification;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.NotificationManagerRemote;
import it.polimi.ingsw2.swim.util.DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * Session Bean implementation class NotificationManager
 */
@Stateless
public class NotificationManager implements NotificationManagerRemote {

	private static final int  MAX_NOTIFICATION = 25;
	private static EntityManager em = DAO.getInstance().getEntityManager();
	
    /**
     * Default constructor. 
     */
    public NotificationManager() {
        super();
    }

    @Override
	public void sendNotification(String addresseeId, String text) throws NoSuchUserException {
		User addressee = new ProfileManager().getUserWithNotifications(addresseeId);
		addressee.addNotification(new Notification(addressee, text));
		em.persist(addressee);
	}
	
	void sendFriendshipRequest(String addresseeId, String senderId) throws NoSuchUserException {
		User sender;
    	try {
			sender = new ProfileManager().getUserWithNotifications(addresseeId);
		} catch (NoSuchUserException e) {
			throw new NoSuchUserException("fromUser");
		}
    	User addressee;
    	try {
    		addressee = new ProfileManager().getUserWithNotifications(addresseeId);
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
		User user = new ProfileManager().getUserWithNotifications(userId);
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
		User user = new ProfileManager().getUserWithNotifications(userId);
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
		User user = new ProfileManager().getUserWithNotifications(userId);
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
}
