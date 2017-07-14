package com.vpaiva.pranadesha.web;

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
	
	private static final Logger log = LogManager.getLogger(SessionListener.class);
    /**
     * Default constructor. 
     */
    public SessionListener() { }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  {
    	log.debug("session created " + se.getSession().getId());
    	LdapContext context = null;
    	try {
			context = LdapSecurity.getContext();
			LdapSecurity.init(context);
		} catch (NamingException e) {
			log.catching(e);
		} finally {
			if (context != null) {
				try { context.close(); } catch (Exception e) { log.error(e); }
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
