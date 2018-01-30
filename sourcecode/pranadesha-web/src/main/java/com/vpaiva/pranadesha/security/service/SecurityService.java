package com.vpaiva.pranadesha.security.service;

import java.util.Hashtable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.vpaiva.pranadesha.security.SecurityProperties;

/**
 * 
 * @author vinicius
 *
 */
@RequestScoped
public class SecurityService {
	
	/**
	 * 
	 * @return
	 */
	@Produces @RequestScoped
	public LdapContext getLdapContext() {
		LdapContext context = null;
		Hashtable<String, String> env = new Hashtable<String, String>();
		SecurityProperties p = SecurityProperties.getInstance();
		env.put(Context.INITIAL_CONTEXT_FACTORY, p.getFactory());
		env.put(Context.PROVIDER_URL, p.getUrl());
		env.put(Context.SECURITY_AUTHENTICATION, p.getAuthentication());
		env.put(Context.SECURITY_PRINCIPAL, p.getBindDN());
		env.put(Context.SECURITY_CREDENTIALS, p.getPassword());
		try {
			context = new InitialLdapContext(env, null);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return context;
	}
	
	public void disposesContext(@Disposes LdapContext context) {
		try {
			context.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
