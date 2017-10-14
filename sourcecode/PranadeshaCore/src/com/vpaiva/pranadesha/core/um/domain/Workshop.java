/**
 * 
 */
package com.vpaiva.pranadesha.core.um.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Entity
 * 
 * @author vinicius
 * @version 1.0, 2017-10-13
 *
 */
public class Workshop implements Serializable, Comparable<Workshop> {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id of class
	 */
	private Integer id;
	
	/**
	 * Course
	 */
	private Course course;
	
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
	 * Default constructor
	 */
	Workshop() { }
	
	/**
	 * Public constructor
	 * @param course
	 * @param initDate
	 * @param endDate
	 */
	public Workshop(Course course, Date initDate, Date endDate) {
		this(null, course, null, initDate, endDate, null, null, null, null);
	}

	/**
	 * Public constructor
	 * @param id
	 * @param course
	 * @param description
	 * @param initDate
	 * @param endDate
	 * @param street
	 * @param neighborhood
	 * @param zip
	 * @param phone
	 */
	public Workshop(Integer id, Course course, String description, Date initDate, Date endDate, String street,
			String neighborhood, String zip, String phone) {
		this.id = id;
		this.course = course;
		this.description = description;
		this.initDate = initDate;
		this.endDate = endDate;
		this.street = street;
		this.neighborhood = neighborhood;
		this.zip = zip;
		this.phone = phone;
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
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Workshop o) {
		if (!initDate.equals(o.initDate)) {
			return o.initDate.compareTo(initDate);
		}
		return id.compareTo(o.id);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Workshop)) {
			return false;
		}
		Workshop other = (Workshop) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Class [id=" + id + ", course=" + course + ", initDate=" + initDate + "]";
	}

}
