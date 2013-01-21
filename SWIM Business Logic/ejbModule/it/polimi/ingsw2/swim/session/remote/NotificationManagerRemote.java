package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.FriendshipRequest;
import it.polimi.ingsw2.swim.entities.Help;
import it.polimi.ingsw2.swim.entities.Message;
import it.polimi.ingsw2.swim.entities.Notification;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;

import java.util.List;

public interface NotificationManagerRemote {

	void markNotificationAsRead(String notificationId);

	List<FriendshipRequest> retriveFriendshipRequestsByUser(String userId)
			throws UserDoesNotExixtException;

	List<Help> retriveHelpRelationStatusByUser(String userId)
			throws UserDoesNotExixtException;

	List<Message> retriveIncomingMessagesByUser(String userId)
			throws UserDoesNotExixtException;

	List<Notification> retriveNotificationsByUser(String userId)
			throws UserDoesNotExixtException;

	void sendNotification(String addresseeId, String text)
			throws UserDoesNotExixtException;

}
