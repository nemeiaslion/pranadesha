/**
 * 
 */
package com.vpaiva.pranadesha.web.cm.user;

import java.io.Serializable;

/**
 * @author vinic
 *
 */
public class UserLocaleVO implements Serializable, Comparable<UserLocaleVO> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id
	 */
	private String id;
	
	/**
	 * Name
	 */
	private String name;

	/**
	 * @param id
	 * @param name
	 */
	public UserLocaleVO(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(UserLocaleVO o) {
		return id.compareTo(o.id);
	}

}
