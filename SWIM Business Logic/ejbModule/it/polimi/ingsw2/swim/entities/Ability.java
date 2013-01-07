package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.InvalidInputException;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Ability implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -214793458991333187L;
	
	@Id
	private String name;
	
	@Lob
	private String description;
	
	private Boolean isStub;
	
	
	public Ability(String name) throws InvalidInputException{
		this.setName(name);
	}
	
	public Ability(String name, String description) throws InvalidInputException{
		this(name);
		this.setDescription(description);
	}
	
	public Ability(String name, String description, Boolean isStub) throws InvalidInputException{
		this(name,description);
		this.isStub = isStub;
	}
	
	/**
	 * @return the name
	 */
	String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 * @throws InvalidInputException 
	 */
	void setName(String name) throws InvalidInputException {
		if(name == null || name.isEmpty()){
			throw new InvalidInputException();
		}
		this.name = name;
	}
	/**
	 * @return the description
	 */
	String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the isStub
	 */
	Boolean isStub() {
		return isStub;
	}
	/**
	 * @param isStub the isStub to set
	 */
	void confirm() {
		this.isStub = false;
	}
	
}
