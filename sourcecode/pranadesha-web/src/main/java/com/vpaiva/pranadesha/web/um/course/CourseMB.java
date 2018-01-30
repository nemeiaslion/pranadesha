/**
 * 
 */
package com.vpaiva.pranadesha.web.um.course;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.core.TransactionManager;
import com.vpaiva.pranadesha.core.um.domain.Course;
import com.vpaiva.pranadesha.facade.FacadeException;
import com.vpaiva.pranadesha.facade.um.CourseFacade;

/**
 * Course Managed Bean
 * @author vinicius
 * @version 1.0, 2017-10-11
 *
 */
@RequestScoped
@Named("courseMB")
public class CourseMB {
	
	
	/**
	 * Logger 
	 */
	private static final Logger log = LogManager.getLogger(CourseMB.class);
	
	/**
	 * Course Facade
	 */
	@Inject
	private CourseFacade courseFacade;
	
	/**
	 * Transaction Manager
	 */
	@Inject
	private TransactionManager transactionManager;
	
	/* ###############################################################
	 *                  attributes
	 * ############################################################### 
	 * */
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * name
	 */
	private String name;
	
	/**
	 * Prerequisite id
	 */
	private Integer prerequisiteId;
	
	/**
	 * Prerequisite Name
	 */
	private String prerequisiteName;
	
	/**
	 * Courses
	 */
	private List<Course> courses;
	
	/* ###############################################################
	 *                  constructors
	 * ############################################################### 
	 * */
	
	public CourseMB() { }
	
	/* ###############################################################
	 *                  bean logic
	 * ############################################################### 
	 * */
	
	/**
	 * init
	 */
	@PostConstruct
	public void init() {
		String managedId = null;
		String[] id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap().get("id");
		if (id != null) {
			managedId = id[0];
		}
		if (managedId != null && !managedId.isEmpty()) {
			try {
				Integer courseId = Integer.parseInt(managedId);
				Course course = courseFacade.getById(courseId);
				name = course.getName();
				this.id = course.getId();
				if (course.getPrerequisite() != null) {
					prerequisiteId = course.getPrerequisite().getId();
					prerequisiteName = course.getPrerequisite().getName();
				}
			} catch (NumberFormatException e) {
				log.error("id not valid", e);
			}
		}
	}
	
	/**
	 *
	 * Save Course in repository
	 *
	 */
	public void save() {
		Course prerequisite = null;
		if (prerequisiteId != null && prerequisiteName != null) {
			prerequisite = new Course(prerequisiteId, prerequisiteName);
		}
		try {
			transactionManager.beginTransaction();
			if (id == null) {
				Course course = new Course(name, prerequisite);
				courseFacade.create(course);
			} else {
				Course course = new Course(id, name, prerequisite);
				courseFacade.update(course);
			}
			transactionManager.commit();
		} catch (Exception e) {
			log.error("unexpected error", e);
			transactionManager.rollback();
		}
		//
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = context.getViewRoot().getLocale();
		Application application = context.getApplication();
		String messageBundle = application.getMessageBundle();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);
		String text = bundle.getString("register.saved");
		//
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, text, text);
		context.addMessage(null, fm);
	}
	
	/**
	 * Delete Course from repository
	 */
	public void delete() {
		try {
			transactionManager.beginTransaction();
			courseFacade.delete(id);
			setId(null);
			setName(null);
			setPrerequisiteId(null);
			setPrerequisiteName(null);
			transactionManager.commit();
		} catch (FacadeException e) {
			transactionManager.rollback();
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			context.addMessage(null, fm);
		}
	}
	
	/**
	 * Search
	 */
	public String search() {
		courses = courseFacade.getAll(0, 10);
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = context.getViewRoot().getLocale();
		Application application = context.getApplication();
		String messageBundle = application.getMessageBundle();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundle, locale);
		StringBuffer sb = new StringBuffer();
		if (courses.size() > 0) {
			String text = bundle.getString("search.found");
			MessageFormat mf = new MessageFormat(text, locale);
			try {
				mf.format(new Object[] { courses.size() }, sb, null);
			} catch (Exception e) {
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
				context.addMessage(null, fm);
				return null;
			}
		} else {
			String text = bundle.getString("search.notfound");
			sb.append(text);
		}
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, sb.toString(), sb.toString());
		context.addMessage(null, fm);
		return "showResults";
	}
	
	/**
	 * Edit selected item
	 * 
	 * @param id
	 * @return
	 */
	public String edit(String id) {
		Integer courseId = Integer.parseInt(id);
		Course course = courseFacade.getById(courseId);
		name = course.getName();
		this.id = course.getId();
		if (course.getPrerequisite() != null) {
			prerequisiteId = course.getPrerequisite().getId();
			prerequisiteName = course.getPrerequisite().getName();
		}
		return "edit";
	}
	
	/* ###############################################################
	 *                  getters and setters
	 * ############################################################### 
	 * */

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the prerequisiteId
	 */
	public Integer getPrerequisiteId() {
		return prerequisiteId;
	}

	/**
	 * @param prerequisiteId the prerequisiteId to set
	 */
	public void setPrerequisiteId(Integer prerequisiteId) {
		this.prerequisiteId = prerequisiteId;
	}

	/**
	 * @return the prerequisiteName
	 */
	public String getPrerequisiteName() {
		return prerequisiteName;
	}

	/**
	 * @param prerequisiteName the prerequisiteName to set
	 */
	public void setPrerequisiteName(String prerequisiteName) {
		this.prerequisiteName = prerequisiteName;
	}

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}
	
}
