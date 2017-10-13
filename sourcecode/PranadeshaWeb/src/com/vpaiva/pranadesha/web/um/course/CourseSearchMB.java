/**
 * 
 */
package com.vpaiva.pranadesha.web.um.course;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import com.vpaiva.pranadesha.core.um.domain.Course;
import com.vpaiva.pranadesha.facade.um.CourseFacade;

/**
 * Course Search Managed Bean
 * @author vinicius
 * @version 1.0, 2017-10-11
 *
 */
public class CourseSearchMB implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Course Façade
	 */
	@EJB
	private CourseFacade courseFacade;
	
	/**
	 * Courses
	 */
	private List<Course> courses;

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

}
