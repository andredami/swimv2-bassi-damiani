/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 * 
 */
@Entity
public class Abuse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1415796722820168971L;

	@Id
	private long id;

	@Lob
	@NotEmpty
	@NotNull
	private String descriprion;

	private boolean handeled = false;

	public Abuse(String descriprion) {
		this.descriprion = descriprion;
	}

	/**
	 * @return the id
	 */
	Long getId() {
		return id;
	}

	/**
	 * @return the descriprion
	 */
	String getDescriprion() {
		return descriprion;
	}

	/**
	 * @return the handeled
	 */
	Boolean getHandeled() {
		return handeled;
	}

	/**
	 * @param handeled
	 *            the handeled to set
	 */
	void handle() {
		this.handeled = true;
	}

}
