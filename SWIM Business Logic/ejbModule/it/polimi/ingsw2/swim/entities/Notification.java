/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 * 
 */
@Entity
@SequenceGenerator(name = "MESSAGE_SEQUENCE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589467236086784860L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQUENCE")
	private Long id;

	@Lob
	@NotEmpty
	@NotNull
	private String text;

	@Temporal(TemporalType.DATE)
	private Date timestamp = Calendar.getInstance().getTime();

	private Boolean read = false;

	@ManyToOne
	@NotNull
	private User addressee;

	public Notification() {
		super();
	}

	public Notification(User addresse, String text) {
		super();
		this.addressee = addresse;
		this.text = text;
	}

	/**
	 * @return the read
	 */
	Boolean isRead() {
		return read;
	}

	/**
	 * @param read
	 *            the read to set
	 */
	void setAsRead() {
		this.read = true;
	}

	/**
	 * @return the id
	 */
	Long getId() {
		return id;
	}

	/**
	 * @return the text
	 */
	String getText() {
		return text;
	}

	/**
	 * @return the timestamp
	 */
	Date getTimestamp() {
		return timestamp;
	}

	User getAddressee() {
		return addressee;
	}
}
