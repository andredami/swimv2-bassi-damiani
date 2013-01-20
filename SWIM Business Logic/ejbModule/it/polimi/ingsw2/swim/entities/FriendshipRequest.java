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

	FriendshipRequest(User addresse, User sender, String text) {
		super(addresse, text);
		this.sender = sender;
	}

	User getSender() {
		return sender;
	}
}
