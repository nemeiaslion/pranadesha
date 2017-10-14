package com.vpaiva.pranadesha.facade.um;

import java.util.List;

import javax.ejb.Local;

import com.vpaiva.pranadesha.core.um.domain.Course;
import com.vpaiva.pranadesha.facade.Facade;
import com.vpaiva.pranadesha.facade.FacadeException;

/**
 * Course Façace Service
 * 
 * @author vinicius
 * @version 1.0, 2017-10-11
 *
 */
@Local
public interface CourseFacade extends Facade<Course, Integer> {
	
	/**
	 * Get all courses
	 * @param page Current page
	 * @param size Page size
	 * @return List of courses
	 */
	List<Course> getAll(int page, int size);
	
	/**
	 * Update course register
	 * 
	 * @param course Course to update
	 * @return saved course
	 * 
	 */
	Course update(Course course);
	
	/**
	 * Create a course
	 * 
	 * @param course course to be saved
	 * @return saved course
	 */
	void create(Course course);
	
	/**
	 * Deletes a course
	 * 
	 * @param id Identifier of course
	 * @return deleted course
	 * @throws FacadeException 
	 */
	Course delete(Integer id) throws FacadeException;
	
	/**
	 * Get course by id
	 * @param id key id for course
	 * @return course or null if not found
	 */
	Course getById(Integer id);

}
