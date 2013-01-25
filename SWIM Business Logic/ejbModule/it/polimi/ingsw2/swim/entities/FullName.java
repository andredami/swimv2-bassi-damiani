package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.validation.Name;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Embeddable
public class FullName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4868236874128030998L;

	@Name
	@NotNull
	@NotEmpty
	private String firstname;

	@Name
	@NotNull
	@NotEmpty
	private String surname;
	
	public FullName(){
		super();
	}

	public FullName(String firstname, String surname) {
		super();
		this.setFirstname(firstname);
		this.setSurname(surname);
	}

	FullName(String fullname) {
		super();
		String[] nameParts = fullname.split(" ");
		this.setFirstname(nameParts[0]);
		StringBuilder surnameBuilder = new StringBuilder(nameParts[1]);
		for (int i = 2; i < (nameParts.length); i++) {
			surnameBuilder.append(" " + nameParts[i]);
		}
		this.setSurname(surnameBuilder.toString());
	}

	public String getFirstname() {
		return firstname;
	}

	void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return firstname + " " + surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result
				+ ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FullName other = (FullName) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
}