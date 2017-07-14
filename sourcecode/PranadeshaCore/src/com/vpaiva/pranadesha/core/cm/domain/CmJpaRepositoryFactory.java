/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA implementation for Communication Module Repository Factory
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
@RequestScoped
public class CmJpaRepositoryFactory {

	@PersistenceContext(unitName="PranadeshaCore")
	private EntityManager em;

	/**
	 * Get person repository
	 * @return person repository
	 */
	@Produces
	public PersonRepository getPersonRepository() {
		PersonRepository r = new PersonJpaRepository();
		r.joinSession(em);
		return r;
	}

}
