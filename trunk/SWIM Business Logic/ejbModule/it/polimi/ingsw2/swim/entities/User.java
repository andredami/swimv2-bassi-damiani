/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.InvalidActivationCode;
import it.polimi.ingsw2.swim.validation.AddressType;
import it.polimi.ingsw2.swim.validation.Telephone;
import it.polimi.ingsw2.swim.validation.URLType;

import java.io.Serializable;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Andrea Damiani
 * 
 */

@Entity
@SequenceGenerator(name = "USER_SEQUENCE")
public class User extends TempUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118322731871513243L;
	
	private static final SecureRandom random = new SecureRandom(); 

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCE")
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

	@ManyToMany(mappedBy = "users")
	@NotEmpty
	private Set<Ability> abilities;

	@ManyToMany(mappedBy = "firendships")
	private Set<User> friendships;

	@OneToMany(mappedBy = "addressee", cascade = CascadeType.ALL)
	@OrderBy("timestamp DESC")
	private List<Notification> notifications;

	private String activationCode;

	public User() {
		super();
	}

	public User(TempUser tempUser, Set<Ability> abilities){
		super(tempUser);
		this.abilities = abilities;
		this.activationCode = new BigInteger(130, random).toString(32);
	}
	
	public User(String password, String email, FullName name, Date birthdate,
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
	String getPicture() {
		return picture;
	}

	/**
	 * @param picture
	 *            the picture to set
	 * @throws MalformedURLException
	 */
	void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	public void confirmRegistration(String activationCode) throws InvalidActivationCode {
		if (this.status != Status.WAITING_FOR_CONFIRMATION) {
			throw new IllegalStateException();
		}
		if (this.activationCode.equals(activationCode)){
			this.status = Status.REGISTERED;
			this.activationCode = null;
		} else {
			throw new InvalidActivationCode();
		}
	}

	void ban() {
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
	void setAddress(String street, String streetNumber, String zip,
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
	String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 * @throws InvalidTelephoneNumberException
	 */
	void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the mobile
	 */
	String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 * @throws InvalidTelephoneNumberException
	 */
	void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the fax
	 */
	String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 * @throws InvalidTelephoneNumberException
	 */
	void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the skype
	 */
	String getSkype() {
		return skype;
	}

	/**
	 * @param skype
	 *            the skype to set
	 */
	void setSkype(String skype) {
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
	void setEvaluation(Float evaluation) {
		this.evaluation = evaluation;
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

	Set<Ability> getAbilities() {
		return this.abilities;
	}

	public void addAbility(Ability ability) {
		this.abilities.add(ability);
	}

	void removeAbility(Ability ability) {
		this.abilities.remove(ability);
	}

	Set<User> getFiendships() {
		return this.friendships;
	}

	void addFirendship(User user) {
		this.friendships.add(user);
	}

	void removeFriendships(User user) {
		this.friendships.remove(user);
	}

	List<Notification> getNotification() {
		return this.notifications;
	}

	void addNotification(Notification notification) {
		this.notifications.add(notification);
	}

	void removeFriendships(Notification notification) {
		this.notifications.remove(notification);
	}

	public String getActivationCode() {
		return activationCode;
	}
}
