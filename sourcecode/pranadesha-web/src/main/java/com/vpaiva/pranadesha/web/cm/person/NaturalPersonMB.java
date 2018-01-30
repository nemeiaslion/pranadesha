package com.vpaiva.pranadesha.web.cm.person;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.core.cm.domain.Person;
import com.vpaiva.pranadesha.facade.FacadeException;
import com.vpaiva.pranadesha.facade.cm.PersonFacade;

/**
 * Person Managed Bean
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
@RequestScoped
@Named("naturalPersonMB")
public class NaturalPersonMB {
	
	/**
	 * log
	 */
	private static final Logger log = LogManager.getLogger(NaturalPersonMB.class);
	
	/**
	 * personFacade
	 */
	@Inject
	private PersonFacade personFacade;
	
	/* ###############################################################
	 *                  attributes
	 * ############################################################### 
	 * */
	
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
	 * Natural Person Id
	 * <p>Just filled when a register is being edited</p>
	 */
	private Integer id;
	
	/**
	 * Managed id passed as parameter from one page to another
	 */
	private String managedId;
	
	/* ###############################################################
	 *                  constructors
	 * ############################################################### 
	 * */
	
	/**
	 * Default Constructor
	 */
	public NaturalPersonMB() { }
	
	/* ###############################################################
	 *                  bean logic
	 * ############################################################### 
	 * */
	
	/**
	 * Initializes bean
	 */
	@PostConstruct
	public void init() {
		if (managedId != null && !managedId.isEmpty()) {
			try {
				Integer id = Integer.parseInt(managedId);
				NaturalPerson item = personFacade.getNaturalPersonById(id);
				if (item == null) {
					FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "not found", "not found");
					FacesContext.getCurrentInstance().addMessage(null, m);
				} else {
					setId(item.getId());
					setGivenName(item.getGivenName());
					setMiddleName(item.getMiddleName());
					setSurname(item.getSurname());
					setDescription(item.getDescription());
					setBirthDate(item.getBirthDate());
					setMail(item.getMail());
					setMobile(item.getMobile());
					setStreetAddress(item.getStreetAddress());
					setCity(item.getCity());
					setProvince(item.getProvince());
					setZipCode(item.getZipCode());
				}
			} catch (NumberFormatException e) {
				log.error("error parsing id " + managedId, e);
			}
		}
	}
	
	/**
	 * Delete
	 */
	public String delete() {
		try {
			Person p = personFacade.delete(id);
			log.info("deleted {}", p);
			return clear();
		} catch (FacadeException e) {
			log.error("error on save natural person", e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, m);
			return null;
		}
	}
	
	/**
	 * Save
	 */
	public void save() {
		NaturalPerson np = new NaturalPerson(surname, givenName, middleName, description, mail, birthDate, mobile, streetAddress, city, province, zipCode);
		try {
			personFacade.saveNaturalPerson(np);
			log.info("created {}", np);
			setId(np.getId());
		} catch (FacadeException e) {
			log.error("error on save natural person", e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, m);
		}
	}
	
	/**
	 * Clear
	 */
	public String clear() {
		setId(null);
		setSurname(null);
		setMiddleName(null);
		setGivenName(null);
		setUserPassword(null);
		setDescription(null);
		setBirthDate(null);
		setMail(null);
		setMobile(null);
		setCity(null);
		setProvince(null);
		setStreetAddress(null);
		setZipCode(null);
		return "new";
	}
	
	/* ###############################################################
	 *                  getters and setters
	 * ############################################################### 
	 * */
	
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

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the managedId
	 */
	public String getManagedId() {
		return managedId;
	}

	/**
	 * @param managedId the managedId to set
	 */
	public void setManagedId(String managedId) {
		this.managedId = managedId;
	}
}
