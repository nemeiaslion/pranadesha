/**
 * 
 */
package com.vpaiva.pranadesha.web;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author vinic
 *
 */
public class GenericRequestScopedMB {
	
	/**
	 * log
	 */
	private static final Logger log = LogManager.getLogger(GenericRequestScopedMB.class);

	/**
	 * FacesContext
	 */
	protected FacesContext context;
	
	/**
	 * Locale
	 */
	protected Locale locale;
	
	/**
	 * Application
	 */
	protected Application application;
	
	/**
	 * messageBundle
	 */
	protected String messageBundle;
	
	/**
	 * ResourceBundle
	 */
	protected ResourceBundle bundle;

	/**
	 * @return the context
	 */
	public FacesContext getContext() {
		if (context == null) {
			context = FacesContext.getCurrentInstance();
		}
		return context;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		if (locale == null) {
			locale = getContext().getViewRoot().getLocale();
		}
		return locale;
	}

	/**
	 * @return the application
	 */
	public Application getApplication() {
		if (application == null) {
			application = getContext().getApplication();
		}
		return application;
	}

	/**
	 * @return the messageBundle
	 */
	public String getMessageBundle() {
		if (messageBundle == null) {
			messageBundle = getApplication().getMessageBundle();
		}
		return messageBundle;
	}

	/**
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(getMessageBundle(), getLocale());
		}
		return bundle;
	}
	
	/**
	 * Add message
	 */
	protected void addMessage(FacesMessage.Severity severity, String key, Object[] arguments) {
		try {
			String text = getBundle().getString(key);
			StringBuffer result = new StringBuffer();
			if (arguments != null && arguments.length > 0) {
				MessageFormat mf = new MessageFormat(text, getLocale());
				mf.format(arguments, result, null);
			} else {
				result.append(text);
			}
			FacesMessage fm = new FacesMessage(severity, result.toString(), result.toString());
			getContext().addMessage(null, fm);
		} catch (Exception e) {
			log.error("unable to add message to FacesContext. key={};arguments={}", key, Arrays.toString(arguments));
			log.catching(Level.DEBUG, e);
		}
	}
	
}
