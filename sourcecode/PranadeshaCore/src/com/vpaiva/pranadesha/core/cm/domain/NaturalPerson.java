/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.util.Date;

/**
 * Natural Person Entity
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
public class NaturalPerson extends Person {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Surname
	 */
	private String surname;
	
	/**
	 * Given Name
	 */
	private String givenName;
	
	/**
	 * Middle Name
	 */
	private String middleName;
	
	/**
	 * User Password
	 */
	private String userPassword;
	
	/**
	 * Description
	 */
	private String description;
	
	/**
	 * e-mail
	 */
	private String mail;
	
	/**
	 * Birthday Date
	 */
	private Date birthDate;
	
	/**
	 * Mobile number
	 */
	private String mobile;
	
	/**
	 * Default Constructor
	 */
	NaturalPerson() { }
	
	/**
	 * @param surname
	 * @param givenName
	 * @param middleName
	 * @param userPassword
	 * @param description
	 * @param mail
	 * @param birthDate
	 * @param mobile
	 */
	public NaturalPerson(String surname, String givenName, String middleName, String userPassword, String description,
			String mail, Date birthDate, String mobile, String streetAddress, String city, String province, String zipCode) {
		super(streetAddress, city, province, zipCode);
		this.surname = surname;
		this.givenName = givenName;
		this.middleName = middleName;
		this.userPassword = userPassword;
		this.description = description;
		this.mail = mail;
		this.birthDate = birthDate;
		this.mobile = mobile;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.cm.domain.Person#compareTo(com.vpaiva.pranadesha.core.cm.domain.Person)
	 */
	@Override
	public int compareTo(Person o) {
		if (o instanceof NaturalPerson) {
			NaturalPerson n = (NaturalPerson) o;
			if (!surname.equals(n.surname)) {
				return surname.compareTo(n.surname);
			}
			if (!givenName.equals(n.givenName)) {
				return givenName.compareTo(n.givenName);
			}
		}
		return super.compareTo(o);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NaturalPerson [mail=" + mail + ", id=" + getId() + "]";
	}
	
	
	
}
