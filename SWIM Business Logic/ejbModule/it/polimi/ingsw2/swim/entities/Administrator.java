/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.util.Digester;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 * 
 */
@NamedQuery(
		name = "getAdministratorByEmail", 
		query = "SELECT a FROM Administrator a WHERE a.email =:email")
@Entity
public class Administrator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7500647892207458612L;

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String username;

	@Email
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotNull
	@NotEmpty
	private String password;

	public Administrator() {
		super();
	}

	public Administrator(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.setPassword(password);
	}

	/**
	 * @return the id
	 */
	Long getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 * @throws InvalidUsernameException
	 */
	void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 * @throws InvalidEmailAddressException
	 */
	void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	Boolean checkPassword(String password) {
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
	void setPassword(String oldPassword, String newPassword)
			throws InvalidPasswordException {
		if (!checkPassword(oldPassword)) {
			throw new InvalidPasswordException();
		}

		this.setPassword(newPassword);
	}
}
