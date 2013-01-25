package it.polimi.ingsw2.swim.session.remote;

import java.util.List;

import it.polimi.ingsw2.swim.entities.Help.State;
import it.polimi.ingsw2.swim.entities.Message;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;

public interface HelpManagerRemote {

	void registerHelpRequest(String addresseeId, String senderId,
			String abilityName, String text) throws InvalidDataException,
			NoSuchUserException;

	void forwardReplyMessage(String senderId, String helpId, String text)
			throws InvalidDataException, NoSuchUserException;

	void editHelpRelationStatus(String helpId, State status)
			throws InvalidDataException;

	void denyHelp(String helpId) throws InvalidDataException;

	List<Message> retriveConversationByHelpRelationship(String helpId)
			throws InvalidDataException;

	void registerFeedback(String helpId, String userId, int feedback)
			throws InvalidDataException, NoSuchUserException;

}
