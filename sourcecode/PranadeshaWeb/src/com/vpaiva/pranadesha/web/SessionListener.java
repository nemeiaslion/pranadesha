package com.vpaiva.pranadesha.web;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.security.LdapSecurity;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {
	
	/**
	 * log
	 */
	private static final Logger log = LogManager.getLogger(SessionListener.class);
	
	/**
	 * LDAP Context
	 */
	@Inject
	private LdapContext context;
	
    /**
     * Default constructor. 
     */
    public SessionListener() { }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  {
    	log.debug("session created " + se.getSession().getId());
    	if (WebProperties.isLdapSecurityDevice()) {
        	try {
        		LdapSecurity.init(context);
    		} catch (NamingException e) {
    			log.catching(e);
    		}
    	}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  {
    	log.debug("session destroyed " + se.getSession().getId());
    }
	
}
