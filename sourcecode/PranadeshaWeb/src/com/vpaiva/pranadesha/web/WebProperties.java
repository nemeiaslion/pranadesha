/**
 * 
 */
package com.vpaiva.pranadesha.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Application Properties
 * 
 * @author vinicius
 * @version 1.0, 2017-10-12
 *
 */
public class WebProperties {
	/**
	 * User Interface Bundle base name
	 */
	public static final String UIBUNDLE = "com.vpaiva.pranadesha.web.UIBundle";
	
	/**
	 * Web properties
	 */
	public static final String WEB_PROPERTIES = "com/vpaiva/pranadesha/web/web.properties";
	
	/**
	 * ldap
	 */
	public static final String SECURITY_DEVICE_LDAP = "ldap";
	
	/**
	 * properties
	 */
	private static Properties properties;
	
	static {
		ClassLoader cl = WebProperties.class.getClassLoader();
		InputStream is = cl.getResourceAsStream(WEB_PROPERTIES);
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { is.close(); } catch (IOException e) { }
		}
	}
	
	/**
	 * 
	 * @return Security Device
	 */
	public static String getSecurityDevice() {
		return properties.getProperty("security.device");
	}
	
	/**
	 * 
	 * @return Ldap security device
	 */
	public static boolean isLdapSecurityDevice() {
		return getSecurityDevice().equals(SECURITY_DEVICE_LDAP);
	}
}
