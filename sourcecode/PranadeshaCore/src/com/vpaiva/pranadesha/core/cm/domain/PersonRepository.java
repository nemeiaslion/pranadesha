package com.vpaiva.pranadesha.core.cm.domain;

import java.util.List;

import com.vpaiva.pranadesha.core.Repository;

/**
 * Person Repository
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
public interface PersonRepository extends Repository<Person, Integer> {
	
	/**
	 * Get all natural person
	 * @param page Page index starting at 0
	 * @param size Size of page
	 * @return list of natural person
	 */
	List<NaturalPerson> getAllNaturalPerson(int page, int size);
	
	/**
	 * Count of registers of Natural Person
	 * @return Natural Person count
	 */
	long getNaturalPersonCount();
	
	/**
	 * Persists Natural Person
	 * @param entity Natural Person to be persisted
	 */
	void saveNaturalPerson(NaturalPerson entity);
	
	/**
	 * Get Natural Person by Id
	 * @param id Id of Natural Person
	 * @return Natural Person or null if does not exists
	 */
	NaturalPerson getNaturalPersonById(Integer id);
}
