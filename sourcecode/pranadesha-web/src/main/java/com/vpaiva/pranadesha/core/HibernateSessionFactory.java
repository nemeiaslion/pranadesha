/**
 * 
 */
package com.vpaiva.pranadesha.core;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

/**
 * @author vinicius
 * @version 1.0, 2018-02-07
 *
 */
@RequestScoped
public class HibernateSessionFactory {
	
	/**
	 * Session Factory
	 */
	@Inject
	private SessionFactory sessionFactory;
	
	/**
	 * Current Session
	 */
	private Session currentSession;
	
	/**
	 * Opens a new Session
	 * 
	 * @return A new Session
	 */
	public Session openSession() {
		return sessionFactory.openSession();
	}
	
	/**
	 * @return The current session
	 */
	public Session getCurrentSession() {
		if (currentSession == null) {
			currentSession = openSession();
		}
		return currentSession;
	}
	
	/**
	 * Opens a new stateless session
	 * 
	 * @return The stateless session
	 */
	public StatelessSession openStatelessSession() {
		return sessionFactory.openStatelessSession();
	}
	
	/**
	 * Close session
	 */
	@PreDestroy
	private void closeSession() {
		if (currentSession != null && currentSession.isOpen()) {
			currentSession.close();
		}
	}

}
