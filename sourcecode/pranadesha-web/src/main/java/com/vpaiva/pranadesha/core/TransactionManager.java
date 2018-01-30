/**
 * 
 */
package com.vpaiva.pranadesha.core;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * Interceptor binding to handle transactions marked by 
 * {@link Transactional} annotation. For methods which are 
 * marked as transactional, this method begins a 
 * {@link Transaction} on current session before method execution,
 * commits on method end and rollbacks in case of any
 * exception.
 * @author vinicius
 * @version 1.0, 2018-02-07
 *
 */
@RequestScoped
public class TransactionManager {
	
	/**
	 * Session Factory
	 */
	@Inject
	private HibernateSessionFactory sessionFactory;
	
	/**
	 * Transaction
	 */
	private Transaction tx;
	
	/**
	 * Begin transaction
	 */
	public void beginTransaction() {
		if (tx == null) {
			tx = sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	/**
	 * Commit
	 */
	public void commit() {
		if (tx != null && tx.getStatus().equals(TransactionStatus.ACTIVE)) {
			try {
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
			}
		}
	}
	
	/**
	 * Rollback
	 */
	public void rollback() {
		if (tx != null && tx.getStatus().equals(TransactionStatus.ACTIVE)) {
			try {
				tx.rollback();
			} catch (Exception e) { }
		}
	}
	
	/**
	 * Handle transaction
	 */
	@PreDestroy
	public void handleTransaction() {
		commit();
	}
	

}
