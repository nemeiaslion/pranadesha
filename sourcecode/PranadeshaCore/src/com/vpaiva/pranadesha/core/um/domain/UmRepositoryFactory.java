/**
 * 
 */
package com.vpaiva.pranadesha.core.um.domain;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA implementation for University Module Repository Factory
 * 
 * @author vinicius
 * @version 1.0, 2017-10-11
 *
 */
@RequestScoped
public class UmRepositoryFactory {

	@PersistenceContext(unitName="PranadeshaCore")
	private EntityManager em;
	
	/**
	 * Produces proper CourseRepository implementation
	 * @return CourseRepository instance
	 */
	@Produces
	public CourseRepository getCourseRepository() {
		CourseRepository r = new CourseJpaRepository();
		r.joinSession(em);
		return r;
	}
	
	/**
	 * Produces proper WorkshopRepository implementation
	 * @return WorkshopRepository implementation
	 */
	@Produces
	public WorkshopRepository getClassRepository() {
		WorkshopRepository r = new WorkshopJpaRepository();
		r.joinSession(em);
		return r;
	}

}
