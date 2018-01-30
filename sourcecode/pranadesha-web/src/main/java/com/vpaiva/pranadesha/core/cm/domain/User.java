/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author vinic
 *
 */
public class User implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name
	 */
	private String name;
	
	/**
	 * Password
	 */
	private String password;
	
	/**
	 * User locale
	 */
	private UserLocale userLocale;
	
	/**
	 * User Time Zone
	 */
	private UserTimeZone userTimeZone;
	
	/**
	 * Roles
	 */
	private Set<UserRole> roles;
	
	/**
	 * Default Constructor 
	 */
	User() { }
	
	/**
	 * Constructor
	 * 
	 * @param name
	 * @param password
	 */
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		addRole("USERS");
	}

	/**
	 * Add Role to this user
	 * 
	 * @param role
	 * @return
	 */
	public UserRole addRole(String role) {
		UserRole userRole = new UserRole(this, role);
		if (roles == null) {
			roles = new HashSet<UserRole>();
		}
		roles.add(userRole);
		return userRole;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userLocale
	 */
	public UserLocale getUserLocale() {
		return userLocale;
	}

	/**
	 * @param userLocale the userLocale to set
	 */
	public void setUserLocale(UserLocale userLocale) {
		this.userLocale = userLocale;
	}

	/**
	 * @return the userTimeZone
	 */
	public UserTimeZone getUserTimeZone() {
		return userTimeZone;
	}

	/**
	 * @param userTimeZone the userTimeZone to set
	 */
	public void setUserTimeZone(UserTimeZone userTimeZone) {
		this.userTimeZone = userTimeZone;
	}

	/**
	 * @return the roles
	 */
	public Set<UserRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	/**
	 * @return Locale
	 */
	public Locale getLocale() {
		return userLocale.getLocale();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
	
}
