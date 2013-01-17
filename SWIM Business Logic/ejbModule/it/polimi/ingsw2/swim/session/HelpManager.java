package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Help;
import it.polimi.ingsw2.swim.entities.Help.Direction;
import it.polimi.ingsw2.swim.entities.Help.State;
import it.polimi.ingsw2.swim.entities.Message;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.UserDoesNotExixtException;
import it.polimi.ingsw2.swim.util.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * Session Bean implementation class HelpManager
 */
@Stateless
public class HelpManager implements HelpManagerRemote {

	private static EntityManager em = DAO.getInstance().getEntityManager();

	/**
	 * Default constructor.
	 */
	public HelpManager() {
		super();
	}

	public void registerHelpRequest(String addresseeId, String senderId,
			String abilityName, String text) throws InvalidDataException,
			UserDoesNotExixtException {
		User addressee = em.find(User.class, Long.parseLong(addresseeId));
		if (addressee == null) {
			throw new UserDoesNotExixtException("Addressee not valid");
		}
		User sender = em.find(User.class, Long.parseLong(senderId));
		if (sender == null) {
			throw new UserDoesNotExixtException("Sender not valid");
		}
		Ability ability = em.find(Ability.class, abilityName.toLowerCase());
		if (ability == null) {
			throw new InvalidDataException();
		}
		if (text == null || text.isEmpty()) {
			throw new InvalidDataException();
		}

		em.persist(new Help(addressee, sender, ability, text));
	}

	public void forwardReplyMessage(String senderId, String helpId, String text)
			throws InvalidDataException, UserDoesNotExixtException {
		User sender = em.find(User.class, Long.parseLong(senderId));
		if (sender == null) {
			throw new UserDoesNotExixtException();
		}

		Help help;
		try {
			help = (Help) em.createNamedQuery("getCompleteHelp")
					.setParameter("id", Long.parseLong(helpId))
					.getSingleResult();
		} catch (NoResultException e) {
			throw new InvalidDataException();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new InvalidDataException();
		}

		if (text == null || text.isEmpty()) {
			throw new InvalidDataException();
		}

		if (help.getSender().equals(sender)) {
			help.addMessage(Direction.FORTH, text);
		} else if (help.getAddressee().equals(sender)) {
			help.addMessage(Direction.BACK, text);
		} else {
			throw new InvalidDataException();
		}

		em.merge(help);
	}

	public void editHelpRelationStatus(String helpId, State status)
			throws InvalidDataException {
		Help help = em.find(Help.class, Long.parseLong(helpId));
		if (help == null) {
			throw new InvalidDataException();
		}
		if (status != State.REQUESTED) {
			if (help.getState() != State.REQUESTED) {
				throw new IllegalStateException();
			}
			help.accept();
		} else {
			throw new IllegalStateException();
		}
		em.merge(help);
	}

	public List<Message> retriveConversationByHelpRelationship(String helpId)
			throws InvalidDataException {
		Help help;
		try {
			help = (Help) em.createNamedQuery("getCompleteHelp")
					.setParameter("id", Long.parseLong(helpId))
					.getSingleResult();
		} catch (NoResultException e) {
			throw new InvalidDataException();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			throw new InvalidDataException();
		}
		return help.getConversation();
	}

	public void registerFeedback(String helpId, String userId, int feedback) throws InvalidDataException, UserDoesNotExixtException {
		if (feedback < -5 || feedback > 5) {
			throw new IllegalArgumentException();
		}
		Help help = em.find(Help.class, Long.parseLong(helpId));
		if (help == null) {
			throw new InvalidDataException();
		}

		User user = em.find(User.class, Long.parseLong(userId));
		if (user == null) {
			throw new UserDoesNotExixtException();
		}

		User evaluatedUser;
		if (help.getAddressee().equals(user)) {
			help.setHelpedFeedback(feedback);
			evaluatedUser = help.getSender();
		} else if (help.getSender().equals(user)) {
			help.setHelperFeedback(feedback);
			evaluatedUser = help.getAddressee();
		} else {
			throw new InvalidDataException();
		}
		
		evaluatedUser.addFeedback(feedback);
		em.merge(evaluatedUser);
		em.merge(help);
	}

}
