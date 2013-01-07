/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.InvalidInputException;

import java.io.Serializable;

import javax.persistence.Entity;

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

	private String name;
	
	public Alias(String name) throws InvalidInputException{
		if(name == null || name.isEmpty()){
			throw new InvalidInputException();
		}
		this.name = name;
	}

	/**
	 * @return the name
	 */
	String getName() {
		return name;
	}
	
}
