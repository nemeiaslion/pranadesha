package com.vpaiva.pranadesha.facade.um;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.vpaiva.pranadesha.core.um.domain.Course;
import com.vpaiva.pranadesha.core.um.domain.CourseRepository;
import com.vpaiva.pranadesha.facade.FacadeException;

/**
 * Session Bean implementation class CourseFacadeEJB
 */
@Stateless
public class CourseFacadeEJB implements CourseFacade {

	/**
	 * Course Repository
	 */
	@Inject
	private CourseRepository courseRepository;
	
    /**
     * Default constructor. 
     */
    public CourseFacadeEJB() { }
	
	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.um.CourseFacade#getAll(int, int)
	 */
	@Override
	public List<Course> getAll(int page, int size) {
		return courseRepository.getAll(page, size);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.um.CourseFacade#create(com.vpaiva.pranadesha.core.um.domain.Course)
	 */
	@Override
	public void create(Course course) {
		courseRepository.save(course);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.um.CourseFacade#update(com.vpaiva.pranadesha.core.um.domain.Course)
	 */
	@Override
	public Course update(Course course) {
		Course entity = courseRepository.getById(course.getId());
		entity.setName(course.getName());
		if (course.getPrerequisite() != null) {
			Course prerequisite = courseRepository.getById(course.getPrerequisite().getId());
			entity.setPrerequisite(prerequisite);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.um.CourseFacade#delete(java.lang.Integer)
	 */
	@Override
	public Course delete(Integer id) throws FacadeException {
		if (courseRepository.isPrerequisite(id)) {
			throw new FacadeException();
		}
		Course entity = courseRepository.getById(id);
		courseRepository.delete(entity);
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.um.CourseFacade#getById(java.lang.Integer)
	 */
	@Override
	public Course getById(Integer id) {
		return courseRepository.getById(id);
	}

}
