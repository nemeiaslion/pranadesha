/**
 * 
 */
package com.vpaiva.pranadesha.core.um.domain;

import java.io.Serializable;

/**
 * Course Entity
 * 
 * @author vinicius
 * @version 1.0, 2017-10-04
 *
 */
public class Course implements Serializable, Comparable<Course> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Maximum length for name
	 */
	private static final int NAME_MAX_LENGTH = 33;
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * name
	 */
	private String name;
	
	/**
	 * Prerequisite
	 */
	private Course prerequisite;
	
	/**
	 * Default Constructor
	 */
	Course() { }
	
	/**
	 * Public constructor
	 * 
	 * @param name Course name
	 */
	public Course(String name) {
		this(name, null);
	}
	
	/**
	 * Public constructor
	 * 
	 * @param name Name of course
	 * @param prerequisite Prerequisite for this course
	 */
	public Course(String name, Course prerequisite) {
		this(null, name, prerequisite);
	}
	
	/**
	 * Public constructor
	 * 
	 * @param id Id of course
	 * @param name Name of course
	 */
	public Course(Integer id, String name) {
		this(id, name, null);
	}
	
	/**
	 * Public constructor
	 * 
	 * @param id Id of course
	 * @param name Name of course
	 * @param prerequisite Prerequisite for this course
	 */
	public Course(Integer id, String name, Course prerequisite) {
		setId(id);
		setName(name);
		setPrerequisite(prerequisite);
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		if (name.length() > NAME_MAX_LENGTH) {
			throw new IllegalArgumentException("\"" + name + "\" is larger than " + NAME_MAX_LENGTH + " (" + name.length() + ")");
		}
		this.name = name;
	}

	/**
	 * @return the prerequisite
	 */
	public Course getPrerequisite() {
		return prerequisite;
	}

	/**
	 * @param prerequisite the prerequisite to set
	 */
	public void setPrerequisite(Course prerequisite) {
		this.prerequisite = prerequisite;
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
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;
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
		return "Course [id=" + id + ", name=" + name + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Course o) {
		return name.compareTo(o.name);
	}
	
	

}
