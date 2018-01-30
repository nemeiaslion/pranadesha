/**
 * 
 */
package com.vpaiva.pranadesha.core.um.domain;

import com.vpaiva.pranadesha.core.Repository;

/**
 * @author vinicius
 *
 */
public interface CourseRepository extends Repository<Course, Integer> {
	
	/**
	 * Validates if provided {@code id} is related as prerequisite
	 * @param id Id of course
	 * @return true if it is found in prerequisite for some other course
	 */
	Boolean isPrerequisite(Integer id);

}
