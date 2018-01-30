/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import com.vpaiva.pranadesha.core.JpaRepository;

/**
 * Person JPA Repository Implementation
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
@RequestScoped
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

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.cm.domain.PersonRepository#getNaturalPersonById(java.lang.Integer)
	 */
	@Override
	public NaturalPerson getNaturalPersonById(Integer id) {
		return getById(id, NaturalPerson.class);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.cm.domain.PersonRepository#getNaturalPersonByCriteria(java.lang.String[], java.lang.Object[])
	 */
	@Override
	public List<NaturalPerson> getNaturalPersonByCriteria(String[] parms, Object[] values) {
		return getByCriteria(NaturalPerson.class, parms, values);
	}

}
