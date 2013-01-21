package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.FriendshipRequest;
import it.polimi.ingsw2.swim.entities.Help;
import it.polimi.ingsw2.swim.entities.Message;
import it.polimi.ingsw2.swim.entities.Notification;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

import java.util.List;

public interface NotificationManagerRemote {

	void markNotificationAsRead(String notificationId);

	List<FriendshipRequest> retriveFriendshipRequestsByUser(String userId)
			throws NoSuchUserException;

	List<Help> retriveHelpRelationStatusByUser(String userId)
			throws NoSuchUserException;

	List<Message> retriveIncomingMessagesByUser(String userId)
			throws NoSuchUserException;

	List<Notification> retriveNotificationsByUser(String userId)
			throws NoSuchUserException;

	void sendNotification(String addresseeId, String text)
			throws NoSuchUserException;

}
