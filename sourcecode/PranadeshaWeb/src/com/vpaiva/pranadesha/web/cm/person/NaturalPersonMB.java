package com.vpaiva.pranadesha.web.cm.person;

import java.util.Date;

import javax.ejb.EJB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.facade.cm.PersonFacade;

/**
 * Person Managed Bean
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
public class NaturalPersonMB {
	
	/**
	 * log
	 */
	private static final Logger log = LogManager.getLogger(NaturalPersonMB.class);
	
	/**
	 * personEjb
	 */
	@EJB
	private PersonFacade personEjb;
	
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
	 * Users Description
	 */
	private String description;
	
	/**
	 * Surname
	 */
	private String surname;
	
	/**
	 * Birthday Date
	 */
	private Date birthDate;
	
	/**
	 * E-mail
	 */
	private String mail;
	
	/**
	 * Mobile Number
	 */
	private String mobile;
	
	/**
	 * Street address
	 */
	private String streetAddress;
	
	/**
	 * City
	 */
	private String city;
	
	/**
	 * Province/State
	 */
	private String province;
	
	/**
	 * ZIP Code
	 */
	private String zipCode;
	
	/**
	 * Default Constructor
	 */
	public NaturalPersonMB() { }
	
	/**
	 * Save
	 */
	public void save() {
		NaturalPerson np = new NaturalPerson(surname, givenName, middleName, userPassword, description, mail, birthDate, mobile, streetAddress, city, province, zipCode);
		personEjb.saveNaturalPerson(np);
		log.info("created {}", np);
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

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
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
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
