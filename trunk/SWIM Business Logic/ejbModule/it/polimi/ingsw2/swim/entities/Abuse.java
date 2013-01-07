/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.InvalidInputException;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

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
	private Long id;
	
	@Lob
	private String descriprion;
	
	private Boolean handeled = false;
	
	public Abuse(String descriprion) throws InvalidInputException{
		if(descriprion == null || descriprion.isEmpty()){
			throw new InvalidInputException();
		}
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
	 * @param handeled the handeled to set
	 */
	void handle() {
		this.handeled = true;
	}
	
	
}
