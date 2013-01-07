/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.InvalidPasswordException;
import it.polimi.ingsw2.swim.util.Digester;
import it.polimi.ingsw2.swim.validation.AddressType;
import it.polimi.ingsw2.swim.validation.Name;
import it.polimi.ingsw2.swim.validation.OfAge;
import it.polimi.ingsw2.swim.validation.Telephone;
import it.polimi.ingsw2.swim.validation.URLType;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * @author Andrea Damiani
 * 
 */

@Entity
@SequenceGenerator(name = "USER_SEQUENCE")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118322731871513243L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCE")
	private Long id;

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
	private Date birthdate;

	@Enumerated
	@NotNull
	private Gender gender;

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

	public User(String email, String password, String firstname,
			String surname, String gender, String birthdate) throws ParseException {
		super();
		this.setPassword(password);
		this.setEmail(email);
		this.name = new FullName(firstname, surname);
		this.setBirthdate(birthdate);
		this.setGender(gender);
	}

	/**
	 * @return the id
	 */
	Long getId() {
		return id;
	}

	/**
	 * @return the email
	 */
	String getEmail() {
		return email;
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
	void setPassword(String oldPassword, String newPassword) throws InvalidPasswordException{
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
	void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the name
	 */
	FullName getName() {
		return name;
	}

	/**
	 * @return the birthdate
	 */
	Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 * @throws ParseException 
	 * @throws InvalidDateException 
	 */
	private void setBirthdate(String birthdate) throws ParseException {
		Date parsedBirthdate = null;
		parsedBirthdate = DateFormat.getDateInstance().parse(birthdate);
		this.birthdate = parsedBirthdate;
	}

	/**
	 * @return the gender
	 */
	Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 * @throws InvalidGenderException
	 */
	private void setGender(String gender) {
		this.gender = Gender.valueOf(gender);
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
	Status getStatus() {
		return status;
	}

	void confirmRegistration() {
		if (this.status != Status.WAITING_FOR_CONFIRMATION) {
			throw new IllegalStateException();
		}
		this.status = Status.REGISTERED;
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
		// TODO: Autocomputation
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

	enum Status implements Serializable {
		WAITING_FOR_CONFIRMATION, REGISTERED, BANNED;
	}

	enum Gender implements Serializable {
		M, F;
	}

	@Embeddable
	class FullName implements Serializable {

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

		FullName(String firstname, String surname){
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

		String getFirstname() {
			return firstname;
		}

		void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		String getSurname() {
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
			result = prime * result + getOuterType().hashCode();
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
			if (!getOuterType().equals(other.getOuterType()))
				return false;
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

		private User getOuterType() {
			return User.this;
		}

	}

}
