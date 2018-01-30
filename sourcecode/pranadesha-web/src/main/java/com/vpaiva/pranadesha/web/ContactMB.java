/**
 * 
 */
package com.vpaiva.pranadesha.web;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

/**
 * @author vinic
 *
 */
@RequestScoped
@Named("contactMB")
public class ContactMB extends GenericRequestScopedMB implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name
	 */
	private String name;
	
	/**
	 * e-mail
	 */
	private String email;
	
	/**
	 * Subject
	 */
	private String subject;
	
	/**
	 * Message
	 */
	private String message;
	
	/**
	 * Default Constructor
	 */
	public ContactMB() { }
	
	/**
	 * Send
	 */
	public void send() {
		setName("");
		setEmail("");
		setSubject("");
		setMessage("");
		addMessage(FacesMessage.SEVERITY_INFO, "contact.sent", null);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
