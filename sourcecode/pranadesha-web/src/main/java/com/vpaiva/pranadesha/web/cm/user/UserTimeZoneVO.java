/**
 * 
 */
package com.vpaiva.pranadesha.web.cm.user;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author vinic
 *
 */
public class UserTimeZoneVO implements Serializable, Comparable<UserTimeZoneVO> {

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
	 * Offset
	 */
	private Integer offset;
	
	/**
	 * User's locale
	 */
	private Locale locale;
	
	/**
	 * Time zone
	 */
	private TimeZone timeZone;
	
	/**
	 * Default constructor
	 */
	UserTimeZoneVO() { }

	/**
	 * Constructor
	 * 
	 * @param id {@link TimeZone} id
	 * @param locale
	 */
	public UserTimeZoneVO(String id, Locale locale) {
		this(TimeZone.getTimeZone(id), locale);
	}
	
	/**
	 * @param timeZone
	 * @param locale
	 */
	public UserTimeZoneVO(TimeZone timeZone, Locale locale) {
		id = timeZone.getID();
		name  = timeZone.getDisplayName(false, TimeZone.LONG, locale);
		offset = timeZone.getRawOffset();
		this.locale = locale;
		this.timeZone = timeZone;
	}
	
	/**
	 * Return name and offset
	 * @return
	 */
	public String getNameAndOffset() {
		double d = offset;
		double offset = d / (1000.0 * 60.0 * 60.0);
		String offsetLabel = String.format("%+06.2f GMT", offset);
		return name + offsetLabel;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @return the timeZone
	 */
	public TimeZone getTimeZone() {
		return timeZone;
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
		if (!(obj instanceof UserTimeZoneVO)) {
			return false;
		}
		UserTimeZoneVO other = (UserTimeZoneVO) obj;
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
		double d = offset;
		double offset = d / (1000.0 * 60.0 * 60.0);
		String offsetLabel = String.format("%+06.2f GMT", offset);
		return "UserTimeZone [id=" + id + ", name=" + name + ", offset=" + offsetLabel + ", locale=" + locale + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(UserTimeZoneVO o) {
		return id.compareTo(o.id);
	}

}
