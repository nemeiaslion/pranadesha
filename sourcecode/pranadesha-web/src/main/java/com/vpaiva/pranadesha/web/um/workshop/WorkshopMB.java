/**
 * 
 */
package com.vpaiva.pranadesha.web.um.workshop;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import com.vpaiva.pranadesha.core.um.domain.Course;
import com.vpaiva.pranadesha.core.um.domain.Workshop;
import com.vpaiva.pranadesha.facade.um.CourseFacade;
import com.vpaiva.pranadesha.facade.um.WorkshopFacade;

/**
 * Workshop Managed Bean
 * 
 * @author vinicius
 * @version 1.0, 2017-10-13
 */
@RequestScoped
@Named("workshopMB")
public class WorkshopMB {
	
	/**
	 * Workshop Facade
	 */
	@Inject
	private WorkshopFacade workshopFacade;
	
	/**
	 * Course Facade
	 */
	@Inject
	private CourseFacade courseFacade;
	
	/* ###############################################################
	 *                  attributes
	 * ############################################################### 
	 * */

	/**
	 * Managed id passed as parameter from one page to another
	 */
	@ManagedProperty(value="#{param.id}")
	private String managedId;
	
	/**
	 * Id of class
	 */
	private Integer id;
	
	/**
	 * Course Id
	 */
	private Integer courseId;
	
	/**
	 * Description
	 */
	private String description;
	
	/**
	 * Initial date
	 */
	private Date initDate;
	
	/**
	 * End date
	 */
	private Date endDate;
	
	/**
	 * Street address
	 */
	private	String street;
	
	/**
	 * Neighborhood
	 */
	private String neighborhood;
	
	/**
	 * ZIP code
	 */
	private String zip;
	
	/**
	 * Primary phone contact
	 */
	private String phone;
	
	/**
	 * List of workshops
	 */
	private List<Workshop> workshops;
	
	/* ###############################################################
	 *                  constructors
	 * ############################################################### 
	 * */
	
	public WorkshopMB() { }
	
	/* ###############################################################
	 *                  bean logic
	 * ############################################################### 
	 * */
	
	/**
	 * Save
	 */
	public void save() {
		Course course = courseFacade.getById(courseId);
		Workshop workshop = new Workshop(course, initDate, endDate);
		workshopFacade.create(workshop);
	}
	
	/**
	 * Search
	 */
	public String search() {
		workshops = workshopFacade.getAll(0, 10);
		return "showResults";
	}
	
	/* ###############################################################
	 *                  getters and setters
	 * ############################################################### 
	 * */

	/**
	 * @return the managedId
	 */
	public String getManagedId() {
		return managedId;
	}

	/**
	 * @param managedId the managedId to set
	 */
	public void setManagedId(String managedId) {
		this.managedId = managedId;
	}

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
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the initDate
	 */
	public Date getInitDate() {
		return initDate;
	}

	/**
	 * @param initDate the initDate to set
	 */
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the neighborhood
	 */
	public String getNeighborhood() {
		return neighborhood;
	}

	/**
	 * @param neighborhood the neighborhood to set
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the workshops
	 */
	public List<Workshop> getWorkshops() {
		return workshops;
	}

	/**
	 * @param workshops the workshops to set
	 */
	public void setWorkshops(List<Workshop> workshops) {
		this.workshops = workshops;
	}
	
	
}
