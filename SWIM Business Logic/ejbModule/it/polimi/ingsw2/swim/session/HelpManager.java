package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.entities.Help;
import it.polimi.ingsw2.swim.entities.Help.Direction;
import it.polimi.ingsw2.swim.entities.Help.State;
import it.polimi.ingsw2.swim.entities.Message;
import it.polimi.ingsw2.swim.entities.User;
import it.polimi.ingsw2.swim.exceptions.InvalidDataException;
import it.polimi.ingsw2.swim.exceptions.NoSuchUserException;
import it.polimi.ingsw2.swim.session.remote.HelpManagerRemote;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class HelpManager
 */
@Stateless
@Remote
public class HelpManager implements HelpManagerRemote {

	@PersistenceContext(unitName = "persistentData")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public HelpManager() {
		super();
	}

	@Override
	public void registerHelpRequest(String addresseeId, String senderId,
			String abilityName, String text) throws InvalidDataException,
			NoSuchUserException {
		User addressee = em.find(User.class, Long.parseLong(addresseeId));
		if (addressee == null) {
			throw new NoSuchUserException("toUser");
		}
		User sender = em.find(User.class, Long.parseLong(senderId));
		if (sender == null) {
			throw new NoSuchUserException("fromUser");
		}
		Ability ability = em.find(Ability.class, abilityName.toLowerCase());
		if (ability == null) {
			throw new InvalidDataException();
		}
		if (text == null || text.isEmpty()) {
			throw new InvalidDataException();
		}

		em.persist(new Help(addressee, sender, ability, text));
		new NotificationManager().sendNotification(
				addressee.getId().toString(), "L'utente " + sender.getName()
						+ " ti ha chiesto aiuto.");
	}
	
	@Override
	public void forwardReplyMessage(String senderId, String helpId, String text)
			throws InvalidDataException, NoSuchUserException {
		User sender = em.find(User.class, Long.parseLong(senderId));
		if (sender == null) {
			throw new NoSuchUserException();
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

		User addressee;
		try {
			addressee = new ProfileManager().getUserWithNotifications(help.getAddressee().getId().toString());
		} catch (NoSuchUserException e) {
			throw new RuntimeException();
		}
		addressee.addNotification(help.getConversation().get(help.getConversation().size() - 1));
		em.merge(addressee);
		em.merge(help);
	}

	@Override
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
			try {
				new NotificationManager().sendNotification(help.getSender().getId().toString(),
						"L'utente " + help.getAddressee().getName()
								+ " ha accettato di aiutarti.");
			} catch (NoSuchUserException e) {
				throw new RuntimeException();
			}
		} else {
			throw new IllegalStateException();
		}
		em.merge(help);
	}

	@Override
	public void denyHelp(String helpId) throws InvalidDataException {
		Help help = em.find(Help.class, Long.parseLong(helpId));
		if (help == null) {
			throw new InvalidDataException();
		}

		if (help.getState() != State.REQUESTED) {
			throw new IllegalStateException();
		}
		em.remove(help);
		try {
			new NotificationManager().sendNotification(help.getSender().getId().toString(),
					"L'utente " + help.getAddressee().getName()
							+ " ha rifiutato di aiutarti.");
		} catch (NoSuchUserException e) {
			throw new RuntimeException();
		}
	}

	@Override
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

	@Override
	public void registerFeedback(String helpId, String userId, int feedback)
			throws InvalidDataException, NoSuchUserException {
		if (feedback < -5 || feedback > 5) {
			throw new IllegalArgumentException();
		}
		Help help = em.find(Help.class, Long.parseLong(helpId));
		if (help == null) {
			throw new InvalidDataException();
		}

		User user = em.find(User.class, Long.parseLong(userId));
		if (user == null) {
			throw new NoSuchUserException();
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
