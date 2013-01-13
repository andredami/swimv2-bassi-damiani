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
import javax.persistence.Embeddable;
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
	Address getAddress() {
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
	Float getEvaluation() {
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
	@Embeddable
	public class Address implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5709124674179797574L;

		private String street;
		private String streetNumber;
		private String zip;
		private String city;
		private String province;

		Address(String street, String streetNumber, String zip, String city,
				String province) {
			super();
			this.street = street;
			this.streetNumber = streetNumber;
			this.zip = zip;
			this.city = city;
			this.province = province;
		}

		public Address(String address) {
			super();
			// This method should use an external web service to parse the
			// address
		}

		/**
		 * @return the street
		 */
		public String getStreet() {
			return street;
		}

		/**
		 * @param street
		 *            the street to set
		 * @throws InvalidAddressException
		 */
		public void setStreet(String street) {
			this.street = street;
		}

		/**
		 * @return the streetNumber
		 */
		public String getStreetNumber() {
			return streetNumber;
		}

		/**
		 * @param streetNumber
		 *            the streetNumber to set
		 * @throws InvalidAddressException
		 */
		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		}

		/**
		 * @return the zip
		 */
		public String getZip() {
			return zip;
		}

		/**
		 * @param zip
		 *            the zip to set
		 * @throws InvalidAddressException
		 */
		public void setZip(String zip) {
			this.zip = zip;
		}

		/**
		 * @return the city
		 */
		public String getCity() {
			return city;
		}

		/**
		 * @param city
		 *            the city to set
		 * @throws InvalidAddressException
		 */
		public void setCity(String city) {
			this.city = city;
		}

		/**
		 * @return the province
		 */
		public String getProvince() {
			return province;
		}

		/**
		 * @param province
		 *            the province to set
		 * @throws InvalidAddressException
		 */
		public void setProvince(String province) {
			this.province = province;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((city == null) ? 0 : city.hashCode());
			result = prime * result
					+ ((province == null) ? 0 : province.hashCode());
			result = prime * result
					+ ((street == null) ? 0 : street.hashCode());
			result = prime * result
					+ ((streetNumber == null) ? 0 : streetNumber.hashCode());
			result = prime * result + ((zip == null) ? 0 : zip.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Address other = (Address) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (city == null) {
				if (other.city != null)
					return false;
			} else if (!city.equals(other.city))
				return false;
			if (province == null) {
				if (other.province != null)
					return false;
			} else if (!province.equals(other.province))
				return false;
			if (street == null) {
				if (other.street != null)
					return false;
			} else if (!street.equals(other.street))
				return false;
			if (streetNumber == null) {
				if (other.streetNumber != null)
					return false;
			} else if (!streetNumber.equals(other.streetNumber))
				return false;
			if (zip == null) {
				if (other.zip != null)
					return false;
			} else if (!zip.equals(other.zip))
				return false;
			return true;
		}

		private User getOuterType() {
			return User.this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return street + " " + streetNumber + ", (" + zip + ") " + city
					+ ", " + province;
		}
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
