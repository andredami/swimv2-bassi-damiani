/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.AssertTrue;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 * 
 */
@Entity
@SequenceGenerator(name = "HELP_SEQUENCE")
public class Help extends Notification implements Serializable {

	enum Direction {
		BACK, FORTH;
	}

	/**
	 * @author Administrator
	 * 
	 */
	enum State {
		REQUESTED, ACCEPTED, CLOSED;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -915761807832799403L;

	@ManyToOne
	@NotNull
	private User sender;

	@Min(value = 0)
	@Max(value = 5)
	private Integer helperFeedback = null;

	@Min(value = 0)
	@Max(value = 5)
	private Integer helpedFeedback = null;

	@Enumerated
	@NotNull
	private State state = State.REQUESTED;

	@ManyToOne
	@NotNull
	private Ability ability;

	@OneToMany(mappedBy = "helpRelation", cascade = CascadeType.ALL)
	@OrderBy("timestamp DESC")
	private List<Message> conversation = new ArrayList<Message>();

	public Help() {
		super();
		this.conversation = new LinkedList<Message>();
	}

	public Help(User addresse, User sender, Ability ability, String text) {
		super(addresse, text);
		if (addresse.getAbilities().contains(ability)) {
			this.ability = ability;
		} else {
			throw new IllegalArgumentException();
		}
		this.sender = sender;
		this.conversation = new LinkedList<Message>();
	}

	/**
	 * @return the helperFeedback
	 */
	Integer getHelperFeedback() {
		return helperFeedback;
	}

	/**
	 * @param helperFeedback
	 *            the helperFeedback to set
	 */
	void setHelperFeedback(Integer helperFeedback) {
		if (this.helperFeedback != null) {
			throw new IllegalStateException();
		}
		if (helperFeedback == null) {
			throw new IllegalArgumentException();
		}
		if (this.helpedFeedback != null) {
			close();
		}
		this.helperFeedback = helperFeedback;
	}

	/**
	 * @return the helpedFeedback
	 */
	Integer getHelpedFeedback() {
		return helpedFeedback;
	}

	/**
	 * @param helpedFeedback
	 *            the helpedFeedback to set
	 */
	void setHelpedFeedback(Integer helpedFeedback) {
		if (this.helpedFeedback != null) {
			throw new IllegalStateException();
		}
		if (helpedFeedback == null) {
			throw new IllegalArgumentException();
		}
		if (this.helperFeedback != null) {
			close();
		}
		this.helpedFeedback = helpedFeedback;
	}

	/**
	 * @return the state
	 */
	State getState() {
		return state;
	}

	void accept() {
		if (this.state != State.REQUESTED) {
			throw new IllegalStateException();
		}
		this.state = State.ACCEPTED;
	}

	void close() {
		if (this.state != State.ACCEPTED) {
			throw new IllegalStateException();
		}
		this.state = State.CLOSED;
	}

	Ability getAbility() {
		return ability;
	}

	List<Message> getConversation() {
		return conversation;
	}

	void addMessage(Direction direction, String message) {
		if (direction == Direction.BACK) {
			this.conversation.add(new Message(this.sender, this.getAddressee(),
					message, this));
		} else if (direction == Direction.FORTH) {
			this.conversation.add(new Message(this.getAddressee(), this.sender,
					message, this));
		} else {
			throw new IllegalArgumentException();
		}
	}

	@AssertTrue
	boolean checkState() {
		if (this.helperFeedback != null && this.helpedFeedback != null) {
			if (this.state != State.CLOSED) {
				return false;
			}
		}
		if (this.helperFeedback == null || this.helpedFeedback == null) {
			if (this.state == State.CLOSED) {
				return false;
			}
		}
		return true;
	}

	User getSender() {
		return sender;
	}

}
