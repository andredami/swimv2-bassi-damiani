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
import javax.persistence.Lob;
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
@SequenceGenerator(name="MESSAGE_SEQUENCE")
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589467236086784860L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MESSAGE_SEQUENCE")
	private Long id;
	
	@Lob
	@NotEmpty
	@NotNull
	private String text;
	
	@Temporal(TemporalType.DATE)
	private Date timestamp = Calendar.getInstance().getTime();
	
	private Boolean read = false;
	
	public Message(String text){
		this.text = text;
	}

	/**
	 * @return the read
	 */
	Boolean isRead() {
		return read;
	}

	/**
	 * @param read the read to set
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
	
	
}
