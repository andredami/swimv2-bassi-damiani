/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 * 
 */
@Entity
@SequenceGenerator(name = "MESSAGE_SEQUENCE")
public class Message extends Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589467236086784860L;

	@ManyToOne
	@NotNull
	private User sender;

	@ManyToOne
	@NotNull
	private Help helpRelation;

	public Message() {
		super();
	}

	Message(User addresse, User sender, String text, Help helpRelation) {
		super(addresse, text);
		if (inConversation(helpRelation, addresse, sender)) {
			this.sender = sender;
			this.helpRelation = helpRelation;
		} else {
			throw new IllegalArgumentException();
		}
	}

	User getSender() {
		return sender;
	}

	Help getHelpRelation() {
		return helpRelation;
	}

	private boolean inConversation(Help h, User a, User b) {
		if ((a == h.getAddressee() && b == h.getSender())
				|| (a == h.getSender() && b == h.getAddressee())) {
			return true;
		}
		return false;
	}
}
