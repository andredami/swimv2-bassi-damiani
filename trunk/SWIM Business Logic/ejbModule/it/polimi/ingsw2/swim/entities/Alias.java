/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 *
 */
@Entity
public class Alias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5843877340059083829L;

	@Id
	@NotEmpty
	private String name;
	
	@ManyToOne
	@NotNull
	private Ability ability;
	
	public Alias(String name){
		this.name = name;
	}

	/**
	 * @return the name
	 */
	String getName() {
		return name;
	}
	
}
