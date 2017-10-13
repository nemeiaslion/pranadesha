/**
 * 
 */
package com.vpaiva.pranadesha.core.um.domain;

import javax.persistence.Query;

import com.vpaiva.pranadesha.core.JpaRepository;

/**
 * @author vinicius
 *
 */
class CourseJpaRepository extends JpaRepository<Course, Integer> implements CourseRepository {

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.um.domain.CourseRepository#isPrerequisite(java.lang.Integer)
	 */
	@Override
	public Boolean isPrerequisite(Integer id) {
		Query query = em.createNamedQuery("course.isPrerequisite");
		query.setParameter("id", id);
		Long count = (Long) query.getSingleResult();
		return (count > 0);
	}

}
