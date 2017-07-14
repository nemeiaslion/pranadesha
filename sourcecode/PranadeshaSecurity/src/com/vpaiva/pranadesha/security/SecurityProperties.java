package com.vpaiva.pranadesha.security;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * This class reads file in root of ClassLoader {@code /security.properties}
 * 
 * @author vinicius
 * @version 1.0, 2017-07-10
 */
public class SecurityProperties {
	
	/**
	 * log
	 */
	private static final Logger log = LogManager.getLogger(SecurityProperties.class);
	
	/**
	 * singleton object
	 */
	private static final SecurityProperties instance;
	
	/**
	 * static
	 */
	static {
		instance = new SecurityProperties();
	}
	
	/**
	 * get instance
	 * @return the instance
	 */
	public static SecurityProperties getInstance() {
		return instance;
	}
	
	/**
	 * properties
	 */
	private Properties properties;
	
	/**
	 * Private constructor to avoid external instances
	 */
	private SecurityProperties() {
		InputStream is = null;
		properties = new Properties();
		try {
			is = getClass().getClassLoader().getResourceAsStream("security.properties");
			properties.load(is);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (is != null) {
				try { is.close(); } catch (Exception e) { log.error(e); }
			}
		}
	}
	
	/**
	 * @return LDAP Distinguished Name
	 */
	public String getBaseDN() {
		return properties.getProperty("base.dn");
	}
	
	/**
	 * @return LDAP Distinguished Name for search
	 */
	public String getBaseSearchDN() {
		return properties.getProperty("base.search.dn");
	}
	
	/**
	 * @return Organization Unit
	 */
	public String getOU() {
		return properties.getProperty("ou");
	}
	
	/**
	 * @return Distinguished Name Bind
	 */
	public String getBindDN() {
		return properties.getProperty("bind.dn");
	}
	
	/**
	 * @return The Password
	 */
	public String getPassword() {
		return properties.getProperty("password");
	}
	
	/**
	 * @return The URL
	 */
	public String getUrl() {
		return properties.getProperty("url");
	}
	
	/**
	 * @return The Factory
	 */
	public String getFactory() {
		return properties.getProperty("factory");
	}
	
	/**
	 * @return The Authentication Method
	 */
	public String getAuthentication() {
		return properties.getProperty("authentication");
	}
}
