/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.io.Serializable;

/**
 * @author vinic
 *
 */
/**
 * @author vinic
 *
 */
/**
 * @author vinic
 *
 */
public class UserRole implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * User
	 */
	private User user;
	
	/**
	 * Role
	 */
	private String role;
	
	/**
	 * Default constructor 
	 */
	UserRole() { }
	
	/**
	 * Constructor
	 * @param user
	 * @param role
	 */
	public UserRole(User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (!(obj instanceof UserRole)) {
			return false;
		}
		UserRole other = (UserRole) obj;
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRole [user=" + user + ", role=" + role + "]";
	}
}
