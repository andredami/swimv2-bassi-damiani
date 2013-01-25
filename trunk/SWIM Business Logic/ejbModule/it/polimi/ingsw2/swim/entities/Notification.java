/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589467236086784860L;

	@Id
	@GeneratedValue
	private Long id;

	@Lob
	@NotEmpty
	@NotNull
	private String description;

	@Temporal(TemporalType.DATE)
	private Calendar timestamp = Calendar.getInstance();

	private Boolean readByUser = false;

	@ManyToOne
	@NotNull
	private User addressee;

	public Notification() {
		super();
	}

	public Notification(User addressee, String text) {
		super();
		this.addressee = addressee;
		this.description = text;
	}

	/**
	 * @return the read
	 */
	public Boolean isRead() {
		return readByUser;
	}

	/**
	 * @param readByUser
	 *            the read to set
	 */
	public void setAsRead() {
		this.readByUser = true;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return description;
	}

	/**
	 * @return the timestamp
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	public User getAddressee() {
		return addressee;
	}
}
