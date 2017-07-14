/**
 * 
 */
package com.vpaiva.pranadesha.web.cm.person;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.facade.cm.PersonFacade;

/**
 * Search Natural Person Managed Bean
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
public class SearchNaturalPersonMB {
	/**
	 * personEjb
	 */
	@EJB
	private PersonFacade personEjb;
	
	/**
	 * Results
	 */
	private List<NaturalPerson> results;

	/**
	 * Default Constructor
	 */
	public SearchNaturalPersonMB() {
		super();
	}
	
	/**
	 * Initialization
	 */
	@PostConstruct
	public void init() {
		results = personEjb.getAllNaturalPerson(0, 10);
	}

	/**
	 * @return the results
	 */
	public List<NaturalPerson> getResults() {
		return results;
	}
	
	

}
