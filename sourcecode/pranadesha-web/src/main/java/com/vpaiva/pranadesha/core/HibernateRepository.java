package com.vpaiva.pranadesha.core;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author vinic
 *
 * @param <T>
 * @param <IdT>
 */
public abstract class HibernateRepository<T, IdT> extends JpaRepository<T, IdT> {
	
	/**
	 * Session Factory
	 */
	@Inject
	protected HibernateSessionFactory sessionFactory;
	
	/**
	 * Default Constructor
	 */
	public HibernateRepository() { }
	
	/**
	 * init
	 */
	@PostConstruct
	protected void init() {
		joinSession(sessionFactory.getCurrentSession());
	}
	
}
