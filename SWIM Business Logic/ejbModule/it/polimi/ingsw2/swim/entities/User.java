/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.InvalidActivationCode;
import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.exceptions.LastAbilityDeletionException;
import it.polimi.ingsw2.swim.util.Digester;
import it.polimi.ingsw2.swim.validation.AddressType;
import it.polimi.ingsw2.swim.validation.Telephone;
import it.polimi.ingsw2.swim.validation.URLType;

import java.io.Serializable;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Andrea Damiani
 * 
 */
@NamedQueries({
		@NamedQuery(name = "getUserByEmail", query = "SELECT u FROM User u WHERE u.email =:email"),
		@NamedQuery(name = "getUserWithFriends", query = "SELECT u FROM User u LEFT JOIN FETCH u.friendships WHERE u.id =:id AND u.status = REGISTERED"),
		@NamedQuery(name = "getUserWithAbilities", query = "SELECT u FROM User u LEFT JOIN FETCH u.abilities WHERE u.id =:id AND u.status = REGISTERED"),
		@NamedQuery(name = "getUserWithNotification", query = "SELECT u FROM User u LEFT JOIN FETCH u.notifications WHERE u.id =:id AND u.status = REGISTERED"),
		@NamedQuery(name = "getCompleteUser", query = "SELECT u FROM User u LEFT JOIN FETCH u.abilities LEFT JOIN FETCH u.friendships LEFT JOIN FETCH u.notifications WHERE u.id =:id AND u.status = REGISTERED") })
@Entity
public class User extends TempUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118322731871513243L;

	private static final SecureRandom random = new SecureRandom();

	@Id
	@GeneratedValue
	private Long id;

	@URLType
	private String picture;

	@Enumerated
	@NotNull
	private Status status = Status.WAITING_FOR_CONFIRMATION;

	@AddressType
	@Embedded
	private Address address;

	@Telephone
	private String telephone;

	@Telephone
	private String mobile;

	@Telephone
	private String fax;

	private String skype;

	private Float evaluation = (float) 0;

	private Integer evaluationCount = 0;

	@ManyToMany
	@NotEmpty
	private Set<Ability> abilities;

	// @ManyToMany(mappedBy = "firendships")
	@ManyToMany
	private Set<User> friendships;

	@OneToMany(mappedBy = "addressee", cascade = CascadeType.ALL)
	@OrderBy("timestamp DESC")
	private List<Notification> notifications;

	private String activationCode;

	private String tempPassword;

	public User() {
		super();
	}

	public User(TempUser tempUser, Set<Ability> abilities) {
		super(tempUser);
		this.abilities = abilities;
		this.activationCode = new BigInteger(130, random).toString(32);
	}

	public User(String password, String email, FullName name, Calendar birthdate,
			Gender gender, Set<Ability> abilities) {
		super(password, email, name, birthdate, gender);
		this.abilities = abilities;
		this.activationCode = new BigInteger(130, random).toString(32);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture
	 *            the picture to set
	 * @throws MalformedURLException
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	public void confirmRegistration(String activationCode)
			throws InvalidActivationCode {
		if (this.status != Status.WAITING_FOR_CONFIRMATION) {
			throw new IllegalStateException();
		}
		if (this.activationCode.equals(activationCode)) {
			this.status = Status.REGISTERED;
			this.activationCode = null;
		} else {
			throw new InvalidActivationCode();
		}
	}

	public void ban() {
		this.status = Status.BANNED;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 * @throws InvalidAddressException
	 */
	public void setAddress(String street, String streetNumber, String zip,
			String city, String province) {
		if (this.address == null) {
			this.address = new Address(street, streetNumber, zip, city,
					province);
		} else {
			this.address.setStreet(street);
			this.address.setStreetNumber(streetNumber);
			this.address.setZip(zip);
			this.address.setCity(city);
			this.address.setProvince(province);
		}
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 * @throws InvalidTelephoneNumberException
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 * @throws InvalidTelephoneNumberException
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 * @throws InvalidTelephoneNumberException
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the skype
	 */
	public String getSkype() {
		return skype;
	}

	/**
	 * @param skype
	 *            the skype to set
	 */
	public void setSkype(String skype) {
		this.skype = skype;
	}

	/**
	 * @return the evaluation
	 */
	public Float getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation
	 *            the evaluation to set
	 */
	public void addFeedback(int feedback) {
		if (feedback < -5 || feedback > 5) {
			throw new IllegalArgumentException();
		}
		this.evaluation = ((this.evaluation * this.evaluationCount) + feedback)
				/ (this.evaluationCount + 1);
		this.evaluationCount++;
	}

	/**
	 * @author Andrea Damiani
	 * 
	 */

	public enum Status implements Serializable {
		WAITING_FOR_CONFIRMATION, REGISTERED, BANNED;
	}

	public enum Gender implements Serializable {
		M, F;
	}

	public Set<Ability> getAbilities() {
		return this.abilities;
	}

	public void addAbility(Ability ability) {
		this.abilities.add(ability);
	}

	public void removeAbility(Ability ability)
			throws LastAbilityDeletionException {
		if (abilities.size() == 1 && abilities.contains(ability)) {
			throw new LastAbilityDeletionException();
		}
		this.abilities.remove(ability);
	}

	public Set<User> getFriendships() {
		return this.friendships;
	}

	public void addFriendship(User user) {
		if (user != this) {
			this.friendships.add(user);
		}
	}

	public void removeFriendships(User user) {
		this.friendships.remove(user);
	}

	public List<Notification> getNotification() {
		return this.notifications;
	}

	public void addNotification(Notification notification) {
		this.notifications.add(0, notification);
	}

	public String getActivationCode() {
		return activationCode;
	}

	@Override
	public void setPassword(String oldPassword, String newPassword)
			throws InvalidPasswordException {
		super.setPassword(oldPassword, newPassword);
		this.tempPassword = null;
	}

	public String setTemporaryPassword() {
		String plainPassword = new BigInteger(130, random).toString(16);
		this.tempPassword = Digester.digest(plainPassword);
		return plainPassword;
	}

	@Override
	public Boolean checkPassword(String password) {
		if (super.checkPassword(password)) {
			return true;
		}
		if (tempPassword != null) {
			if(this.tempPassword.equals(Digester.digest(password))){
				tempPassword = null;
				return true;
			}
		}
		return false;
	}
}
