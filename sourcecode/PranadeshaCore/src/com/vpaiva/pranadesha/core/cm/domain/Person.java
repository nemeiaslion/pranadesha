/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import java.io.Serializable;

/**
 * Person Entity
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 */
public abstract class Person implements Comparable<Person>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * Street Address
	 */
	private String streetAddress;
	
	/**
	 * City
	 */
	private String city;
	
	/**
	 * Province
	 */
	private String province;
	
	/**
	 * ZIP Code
	 */
	private String zipCode;
	
	/**
	 * Default constructor
	 */
	Person() { }
	
	/**
	 * constructor
	 * 
	 * @param streetAddress
	 * @param city
	 * @param province
	 * @param zipCode
	 */
	public Person(String streetAddress, String city, String province, String zipCode) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.province = province;
		this.zipCode = zipCode;
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
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
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
		return "Person [id=" + id + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Person o) {
		return id.compareTo(o.id);
	}

}
