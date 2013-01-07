/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.InvalidAddressException;
import it.polimi.ingsw2.swim.exceptions.InvalidAgeException;
import it.polimi.ingsw2.swim.exceptions.InvalidDateException;
import it.polimi.ingsw2.swim.exceptions.InvalidEmailAddressException;
import it.polimi.ingsw2.swim.exceptions.InvalidFullnameException;
import it.polimi.ingsw2.swim.exceptions.InvalidGenderException;
import it.polimi.ingsw2.swim.exceptions.InvalidTelephoneNumberException;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

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

/**
 * @author Andrea Damiani
 * 
 */

@Entity
@SequenceGenerator(name="USER_SEQUENCE")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118322731871513243L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USER_SEQUENCE")
	private Long id;
	
	private String email;
	
	@Embedded
	private FullName name;
	
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@Enumerated
	private Gender gender;
	
	private String picture;
	
	@Enumerated
	private Status status = Status.WAITING_FOR_CONFIRMATION;
	
	@Embedded
	private Address address;
	
	private String telephone;
	
	private String mobile;
	
	private String fax;
	
	private String skype;
	
	private Float evaluation = (float) 0;
	
	public User(String email, String firstname, String surname, String gender, String birthdate) throws InvalidEmailAddressException, InvalidFullnameException, InvalidDateException, InvalidAgeException, InvalidGenderException{
		super();
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
	 * @param email
	 *            the email to set
	 * @throws InvalidEmailAddressException
	 */
	void setEmail(String email) throws InvalidEmailAddressException {
		if (email == null || email.isEmpty() || !email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$")) {
			throw new InvalidEmailAddressException();
		}
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
	 */
	private void setBirthdate(String birthdate) throws InvalidDateException, InvalidAgeException{
		if(birthdate == null || birthdate.isEmpty()){
			throw new InvalidDateException();
		}
		Calendar birthdateCal = Calendar.getInstance();
		Date parsedBirthdate = null;
		try {
			parsedBirthdate = DateFormat.getDateInstance().parse(birthdate);
		} catch (ParseException e) {
			throw new InvalidDateException();
		}
		birthdateCal.setTime(parsedBirthdate);
		
		int birthdateYear = birthdateCal.get(Calendar.YEAR);
		int birthdateMonth = birthdateCal.get(Calendar.MONTH);
		int birthdateDay = birthdateCal.get(Calendar.DAY_OF_MONTH);
		
		Calendar currentCal = Calendar.getInstance();
		currentCal.setTimeInMillis(System.currentTimeMillis());
		
		int currentYear = currentCal.get(Calendar.YEAR);
		int currentMonth = currentCal.get(Calendar.MONTH);
		int currentDay = currentCal.get(Calendar.DAY_OF_MONTH);
		
		if(currentYear - birthdateYear < 0){
			throw new InvalidAgeException();
		} else {
			if(currentYear - birthdateYear == 0){
				if(currentMonth - birthdateMonth < 0){
					throw new InvalidAgeException();
				} else {
					if(currentMonth - birthdateMonth == 0 && currentDay - birthdateDay < 0){
						throw new InvalidAgeException();
					}
				}
			}
		}
		
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
	private void setGender(String gender) throws InvalidGenderException {
		if(gender == null || gender.isEmpty() || Gender.valueOf(gender) == null){
			throw new InvalidGenderException();
		}
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
	void setPicture(String picture) throws MalformedURLException {
		if(picture == null || picture.isEmpty()){
			this.picture = null;
		} else {
			this.picture = (new URL(picture)).toString();
		}
	}

	/**
	 * @return the status
	 */
	Status getStatus() {
		return status;
	}

	void confirmRegistration() {
		if(this.status != Status.WAITING_FOR_CONFIRMATION){
			throw new IllegalStateException();
		}
		this.status = Status.REGISTERED;
	}
	
	void ban(){
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
	void setAddress(String street, String streetNumber, String zip, String city,
			String province) throws InvalidAddressException {
		if(this.address == null){
			this.address = new Address(street, streetNumber, zip, city, province);
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
	void setTelephone(String telephone) throws InvalidTelephoneNumberException {
		if(telephone != null && !telephone.isEmpty()){
		telephone.replaceAll("[ .-()]", "");
		if(!telephone.matches("^\\+?[0-9]{3,}$")){
			throw new InvalidTelephoneNumberException();
		}}
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
	void setMobile(String mobile) throws InvalidTelephoneNumberException {
		if(mobile != null && !mobile.isEmpty()){
				mobile.replaceAll("[ .-()]", "");
		if(!mobile.matches("^\\+?[0-9]{3,}$")){
			throw new InvalidTelephoneNumberException();
		}}
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
	void setFax(String fax) throws InvalidTelephoneNumberException {
		if(fax != null && !fax.isEmpty()){
		fax.replaceAll("[ .-()]", "");
		if(!fax.matches("^\\+?[0-9]{3,}$")){
			throw new InvalidTelephoneNumberException();
		}}
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
		//TODO: Autocomputation
		this.evaluation = evaluation;
	}

	/**
	 * @author Andrea Damiani
	 * 
	 */
	@Embeddable
	class Address implements Serializable {
	
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
				String province) throws InvalidAddressException {
			super();
			this.street = street;
			this.streetNumber = streetNumber;
			this.zip = zip;
			this.city = city;
			this.province = province;
			if (!isValid()) {
				throw new InvalidAddressException();
			}
		}
	
		Address(String address) {
			super();
			// This method should use an external web service to parse the
			// address
		}
	
		/**
		 * @return the street
		 */
		String getStreet() {
			return street;
		}
	
		/**
		 * @param street
		 *            the street to set
		 * @throws InvalidAddressException
		 */
		void setStreet(String street) throws InvalidAddressException {
			if (!isValid(street, null, null, null, null)) {
				throw new InvalidAddressException();
			}
			this.street = street;
		}
	
		/**
		 * @return the streetNumber
		 */
		String getStreetNumber() {
			return streetNumber;
		}
	
		/**
		 * @param streetNumber
		 *            the streetNumber to set
		 * @throws InvalidAddressException
		 */
		void setStreetNumber(String streetNumber) throws InvalidAddressException {
			if (!isValid(null, streetNumber, null, null, null)) {
				throw new InvalidAddressException();
			}
			this.streetNumber = streetNumber;
		}
	
		/**
		 * @return the zip
		 */
		String getZip() {
			return zip;
		}
	
		/**
		 * @param zip
		 *            the zip to set
		 * @throws InvalidAddressException
		 */
		void setZip(String zip) throws InvalidAddressException {
			if (!isValid(null, null, zip, null, null)) {
				throw new InvalidAddressException();
			}
			this.zip = zip;
		}
	
		/**
		 * @return the city
		 */
		String getCity() {
			return city;
		}
	
		/**
		 * @param city
		 *            the city to set
		 * @throws InvalidAddressException
		 */
		void setCity(String city) throws InvalidAddressException {
			if (!isValid(null, null, null, city, null)) {
				throw new InvalidAddressException();
			}
			this.city = city;
		}
	
		/**
		 * @return the province
		 */
		String getProvince() {
			return province;
		}
	
		/**
		 * @param province
		 *            the province to set
		 * @throws InvalidAddressException
		 */
		void setProvince(String province) throws InvalidAddressException {
			if (!isValid(null, null, null, null, province)) {
				throw new InvalidAddressException();
			}
			this.province = province;
		}
	
		boolean isValid() {
			return isValid(null, null, null, null, null);
		}
	
		boolean isValid(String street, String streetNumber, String zip,
				String city, String province) {
	
			if (street == null || street.isEmpty()) {
				street = this.street;
			}
			if (streetNumber == null || streetNumber.isEmpty()) {
				streetNumber = this.streetNumber;
			}
			if (zip == null || zip.isEmpty()) {
				zip = this.zip;
			}
			if (city == null || city.isEmpty()) {
				city = this.city;
			}
			if (province == null || province.isEmpty()) {
				province = this.province;
			}
	
			// Connection to external web service used to validate addresses
	
			return true;
		}

		/* (non-Javadoc)
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

		/* (non-Javadoc)
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

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return street + " " + streetNumber + ", (" + zip + ") " + city + ", " + province;
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
	
		private String firstname;
		private String surname;
	
		FullName(String firstname, String surname) throws InvalidFullnameException {
			super();
			this.setFirstname(firstname);
			this.setSurname(surname);
		}
	
		FullName(String fullname) throws InvalidFullnameException {
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
	
		void setFirstname(String firstname) throws InvalidFullnameException {
			if (firstname.matches("[^[ a-zA-Z]]")) {
				throw new InvalidFullnameException();
			}
			this.firstname = firstname;
		}
	
		String getSurname() {
			return surname;
		}
	
		void setSurname(String surname) throws InvalidFullnameException {
			if (surname.matches("[^[ a-zA-Z]]")) {
				throw new InvalidFullnameException();
			}
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
