/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.io.Serializable;
import java.util.Locale;

/**
 * User Locale entity responsible for provide available locale for user
 * 
 * @author vinic
 * @version 1.0, 2018-02-13
 */
public class UserLocale implements Serializable, Comparable<UserLocale> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	private String id;
	
	/**
	 * Default constructor
	 */
	UserLocale() { }

	/**
	 * Constructor
	 * 
	 * @param id locale id
	 */
	public UserLocale(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return Locale
	 */
	public Locale getLocale() {
		String lang = id.replace('_', '-');
		return Locale.forLanguageTag(lang);
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
		if (!(obj instanceof UserLocale)) {
			return false;
		}
		UserLocale other = (UserLocale) obj;
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
		return "UserLocale [id=" + id + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(UserLocale o) {
		return id.compareTo(o.id);
	}
	
}
