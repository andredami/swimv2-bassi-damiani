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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 * 
 */
@NamedQueries({
		@NamedQuery(name = "getAdministratorByEmail", query = "SELECT a FROM Administrator a WHERE a.email =:email"),
		@NamedQuery(name = "getAdministratorByUsername", query = "SELECT a FROM Administrator a WHERE a.username =:username") })
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
	public Long getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 * @throws InvalidUsernameException
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 * @throws InvalidEmailAddressException
	 */
	public void setEmail(String email) {
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
	public void setPassword(String oldPassword, String newPassword)
			throws InvalidPasswordException {
		if (!checkPassword(oldPassword)) {
			throw new InvalidPasswordException();
		}

		this.setPassword(newPassword);
	}
}
