package com.vpaiva.pranadesha.facade.um;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.vpaiva.pranadesha.core.um.domain.Course;
import com.vpaiva.pranadesha.core.um.domain.CourseRepository;
import com.vpaiva.pranadesha.facade.FacadeException;

/**
 * implementation class CourseFacade
 * @version 1.0, 2017-10-11
 */
@RequestScoped
public class CourseFacadeImpl implements CourseFacade {

	/**
	 * Course Repository
	 */
	@Inject
	private CourseRepository courseRepository;
	
    /**
     * Default constructor. 
     */
    public CourseFacadeImpl() { }
	
	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#getAll(int, int)
	 */
	@Override
	public List<Course> getAll(int page, int size) {
		return courseRepository.getAll(page, size);
	}

	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#create(java.lang.Object)
	 */
	@Override
	public void create(Course course) {
		courseRepository.save(course);
	}

	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#update(java.lang.Object)
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

	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#delete(java.lang.Object)
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

	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#getById(java.lang.Object)
	 */
	@Override
	public Course getById(Integer id) {
		return courseRepository.getById(id);
	}

}
