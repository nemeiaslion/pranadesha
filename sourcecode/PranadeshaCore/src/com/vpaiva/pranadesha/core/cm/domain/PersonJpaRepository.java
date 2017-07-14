/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.util.List;

import com.vpaiva.pranadesha.core.JpaRepository;

/**
 * Person JPA Repository Implementation
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
class PersonJpaRepository extends JpaRepository<Person, Integer> implements PersonRepository {
	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.cm.domain.PersonRepository#getAllNaturalPerson(int, int)
	 */
	@Override
	public List<NaturalPerson> getAllNaturalPerson(int page, int size) {
		return getAll(NaturalPerson.class, page, size);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.cm.domain.PersonRepository#getNaturalPersonCount()
	 */
	@Override
	public long getNaturalPersonCount() {
		return getCount(NaturalPerson.class);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.cm.domain.PersonRepository#saveNaturalPerson(com.vpaiva.pranadesha.core.cm.domain.NaturalPerson)
	 */
	@Override
	public void saveNaturalPerson(NaturalPerson entity) {
		save(entity, NaturalPerson.class);
	}

}
