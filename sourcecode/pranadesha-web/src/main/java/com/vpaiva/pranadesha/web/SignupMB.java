/**
 * 
 */
package com.vpaiva.pranadesha.web;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.facade.cm.PersonFacade;

/**
 * @author vinic
 *
 */
@RequestScoped
@Named("signupMB")
public class SignupMB extends GenericRequestScopedMB implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * log
	 */
	private static final Logger log = LogManager.getLogger(SignupMB.class);
	
	/**
	 * Person facade
	 */
	@Inject
	private PersonFacade personFacade;
	
	/**
	 * Given Name
	 */
	private String givenName;
	
	/**
	 * Surname
	 */
	private String surname;
	
	/**
	 * Mail
	 */
	private String mail;
	
	/**
	 * Password
	 */
	private String password;
	
	/**
	 * Confirm Password
	 */
	private String confirmPassword;
	
	/**
	 * Default constructor
	 */
	public SignupMB() { }
	
	/**
	 * Sign-up
	 */
	public void signup() {
		log.traceEntry();
		try {
			personFacade.beginTransaction();
			personFacade.signup(givenName, surname, mail, password);
			personFacade.commit();
			addMessage(FacesMessage.SEVERITY_INFO, "signup.success", new Object[] { mail });
			clear();
		} catch (Exception e) {
			personFacade.rollback();
			log.catching(e);
			addMessage(FacesMessage.SEVERITY_ERROR, "common.unexpectederror", null);
		}
		log.traceExit();
	}
	
	/**
	 * Clear
	 */
	private void clear() {
		setGivenName("");
		setSurname("");
		setMail("");
		setConfirmPassword("");
		setPassword("");
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
