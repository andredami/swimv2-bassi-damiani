package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.entities.User.Gender;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.util.Digester;
import it.polimi.ingsw2.swim.validation.OfAge;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@MappedSuperclass
public class TempUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -602855167934559517L;

	@NotEmpty
	@NotNull
	private String password;

	@Email
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String email;

	@Embedded
	@NotNull
	private FullName name;

	@Temporal(TemporalType.DATE)
	@OfAge
	@NotNull
	private Calendar birthdate;

	@Enumerated
	@NotNull
	private Gender gender;
	
	public TempUser(){
		super();
	}
	
	public TempUser(String password, String email, FullName name, Calendar birthdate,
			Gender gender) {
		super();
		this.setPassword(password);
		this.email = email.toLowerCase();
		this.name = name;
		this.birthdate = birthdate;
		this.gender = gender;
	}
	
	public TempUser(TempUser tempUser) {
		this.password = new String(tempUser.password);
		this.email = new String(tempUser.email.toLowerCase());
		this.name = new FullName(new String(tempUser.name.getFirstname()), new String(tempUser.name.getSurname()));
		Calendar birthdate = Calendar.getInstance();
		birthdate.clear();
		birthdate.set(tempUser.birthdate.get(Calendar.YEAR), tempUser.birthdate.get(Calendar.MONTH), tempUser.birthdate.get(Calendar.DAY_OF_MONTH));
		this.birthdate = birthdate;
		this.gender = tempUser.gender;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public Boolean checkPassword(String password) {
		return this.password.equals(Digester.digest(password));
	}

	private void setPassword(String password) {
		this.password = Digester.digest(password);
	}

	/**
	 * @param password
	 *            the password to set
	 * @throws InvalidPasswordException
	 */
	protected void setPassword(String oldPassword, String newPassword)
			throws InvalidPasswordException {
		if (!checkPassword(oldPassword)) {
			throw new InvalidPasswordException();
		}

		this.setPassword(newPassword);
	}

	/**
	 * @param email
	 *            the email to set
	 * @throws InvalidEmailAddressException
	 */
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	/**
	 * @return the name
	 */
	public FullName getName() {
		return name;
	}

	/**
	 * @return the birthdate
	 */
	public Calendar getBirthdate() {
		return birthdate;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}
}
