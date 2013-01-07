package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class Ability implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -214793458991333187L;

	@Id
	@NotEmpty
	@NotNull
	private String name;

	@Lob
	@NotEmpty
	@NotNull
	private String description;

	private boolean isStub;

	public Ability(String name) {
		this.setName(name);
	}

	public Ability(String name, String description) {
		this(name);
		this.setDescription(description);
	}

	public Ability(String name, String description, Boolean isStub) {
		this(name, description);
		this.isStub = isStub;
	}

	/**
	 * @return the name
	 */
	String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @throws InvalidInputException
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
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
	 * @param isStub
	 *            the isStub to set
	 */
	void confirm() {
		this.isStub = false;
	}

}
