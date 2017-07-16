package com.vpaiva.pranadesha.facade.cm;

import java.util.List;

import javax.ejb.Local;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.core.cm.domain.Person;
import com.vpaiva.pranadesha.facade.FacadeException;

/**
 * Person Façace Service
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
@Local
public interface PersonFacade {
	/**
	 * Get all natural person
	 * @param page Index of page starting at 0
	 * @param size Size of page
	 * @return List of natural person
	 */
	List<NaturalPerson> getAllNaturalPerson(int page, int size);
	
	/**
	 * Get count of natural person
	 * 
	 * @return count of natural person
	 * 
	 */
	Long getNaturalPersonCount();
	
	/**
	 * Save
	 * @param entity
	 */
	void saveNaturalPerson(NaturalPerson entity) throws FacadeException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	NaturalPerson getNaturalPersonById(Integer id);
	
	/**
	 * 
	 * @param id
	 * @return Deleted person
	 */
	Person delete(Integer id) throws FacadeException;
	
}
