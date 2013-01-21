/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 * 
 */
@Entity
public class FriendshipRequest extends Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589467236086784860L;

	@ManyToOne
	@NotNull
	private User sender;

	public FriendshipRequest() {
		super();
	}

	public FriendshipRequest(User addresse, User sender) {
		super(addresse, "L'utente " + sender.getName() + " vuole diventare tuo amico.");
		this.sender = sender;
	}

	public User getSender() {
		return sender;
	}
}
