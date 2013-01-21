package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.AlreadyHandledException;
import it.polimi.ingsw2.swim.session.AbuseDispatcher;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * Represents a user-written report for inappropriate or offensive contents of
 * the system.
 * 
 * @author Andrea Damiani
 * @category Entity
 * @serial
 */
@Entity
public class Abuse implements Serializable {

	private static final long serialVersionUID = -1415796722820168971L;

	/**
	 * @category Primary Key
	 */
	@Id
	@GeneratedValue
	private long id;

	/**
	 * Contains the reporting user email address. To enable also unregistered
	 * users of the system to report abuses, {@link Abuse} will not point to a
	 * {@link User} entity. Anyway, when a Registered {@link User} reports an
	 * abuse, the system will automatically fill this field with the registered
	 * email address.
	 * 
	 * @see AbuseDispatcher
	 */
	@Email
	@NotEmpty
	@NotNull
	private String email;

	/**
	 * Contains the description of the abuse found by the user.
	 */
	@Lob
	@NotEmpty
	@NotNull
	private String descriprion;

	/**
	 * Is TRUE if an Administrator has taken charge of verifying the abuse,
	 * FALSE otherwise.
	 */
	private boolean handled = false;

	public Abuse() {
		super();
	}

	public Abuse(String email, String descriprion) {
		super();
		this.email = email;
		this.descriprion = descriprion;
	}

	public Long getId() {
		return id;
	}

	public String getDescriprion() {
		return descriprion;
	}

	public String getEmail() {
		return email;
	}

	Boolean isHandled() {
		return handled;
	}

	/**
	 * Takes the {@link Abuse} into handeled state. If the abuse is still
	 * handled, it will throw an {@link AlreadyHandledException}.
	 * 
	 * @throws AlreadyHandledException
	 */
	public void handle() throws AlreadyHandledException {
		if (this.handled == true) {
			throw new AlreadyHandledException();
		}
		this.handled = true;
	}
}
