package com.vpaiva.pranadesha.security;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LdapSecurity
 * @author vinicius
 * @version 1.0, 2017-07-10
 */
public class LdapSecurity {
	
	private static final Logger log = LogManager.getLogger(LdapSecurity.class);
	
	/**
	 * Get Ldap Context
	 * @return The Ldap Context
	 * @throws NamingException
	 */
	public static LdapContext getContext() throws NamingException {
		LdapContext context = null;
		Hashtable<String, String> env = new Hashtable<String, String>();
		SecurityProperties p = SecurityProperties.getInstance();
		env.put(Context.INITIAL_CONTEXT_FACTORY, p.getFactory());
		env.put(Context.PROVIDER_URL, p.getUrl());
		env.put(Context.SECURITY_AUTHENTICATION, p.getAuthentication());
		env.put(Context.SECURITY_PRINCIPAL, p.getBindDN());
		env.put(Context.SECURITY_CREDENTIALS, p.getPassword());
		context = new InitialLdapContext(env, null);
		return context;
	}
	
	/**
	 * Initialize Ldap
	 * @param context
	 * @throws NamingException
	 */
	public static void init(LdapContext context) throws NamingException {
		String baseDN = SecurityProperties.getInstance().getBaseDN();
		//Create ou=Pranadesha
		String name = "ou=Pranadesha," + baseDN;
		createOU(context, name);
		//ou=People,ou=Pranadesha
		name = "ou=People,ou=Pranadesha," + baseDN;
		createOU(context, name);
		//cn=root,ou=People,ou=Pranadesha
		name = "cn=root,ou=People,ou=Pranadesha," + baseDN;
		createPerson(context, name, "root", "root", "root", "root", "root", "root@local", "root");
		//ou=Inactive,ou=People,ou=Pranadesha
		name = "ou=Inactive,ou=People,ou=Pranadesha," + baseDN;
		createOU(context, name);
		//ou=Groups,ou=Pranadesha
		name = "ou=Groups,ou=Pranadesha," + baseDN;
		createOU(context, name);
		//cn=USERS,ou=Groups,ou=Pranadesha
		name = "cn=USERS,ou=Groups,ou=Pranadesha," + baseDN;
		String[] members = { "cn=root,ou=People,ou=Pranadesha," + baseDN };
		createGroupOfNames(context, name, "USERS", "Pranadesha Users", members);
		//
		name = SecurityProperties.getInstance().getBaseDN();
		SearchControls controls = new SearchControls();
		String[] returningAttrs = { "userPassword" };
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(returningAttrs);
		String filter = "(|(objectClass=inetOrgPerson)(objectClass=simpleSecurityObject))";
		NamingEnumeration<SearchResult> results = context.search(name, filter, controls);
		while (results.hasMore()) {
			SearchResult result = results.next();
			String dn = result.getNameInNamespace();
			Attribute attr = result.getAttributes().get("userPassword");
			if (attr != null) {
				byte[] value = (byte[]) attr.get();
				String userPassword = new String(value);
				log.info("{}/{}", dn, userPassword);
			}
		}
		
	}
	
	/**
	 * create organizational unit
	 * @param context
	 * @param name
	 * @throws NamingException
	 */
	private static void createOU(LdapContext context, String name) throws NamingException {
		String[] returningAttrs = { "name", "objectClass" };
		String filter = "(objectClass=organizationalUnit)";
		SearchResult result = findObjectByName(context, name, filter, returningAttrs);
		if (result == null) {
			log.debug("{} does not exists", name);
			Attributes attrs = new BasicAttributes(true);
			Attribute objectClass = new BasicAttribute("objectClass");
			objectClass.add("organizationalUnit");
			attrs.put(objectClass);
			DirContext subContext = context.createSubcontext(name, attrs);
			log.info("created " + name);
			subContext.close();
		}
	}
	
	/**
	 * Creates a person
	 * 
	 * @param context
	 * @param name
	 * @param cn
	 * @param uid
	 * @param gn
	 * @param sn
	 * @param description
	 * @param mail
	 * @param userPassword
	 * @throws NamingException
	 */
	private static void createPerson(LdapContext context, String name, String cn, 
			String uid, String gn, String sn, String description, String mail, 
			String userPassword) throws NamingException {
		String[] returningAttrs = { "name", "objectClass" };
		String filter = "(objectClass=person)";
		SearchResult result = findObjectByName(context, name, filter, returningAttrs);
//		if (result != null) {
//			context.destroySubcontext(name);
//		}
//		result = findObjectByName(context, name, filter, returningAttrs);
		if (result == null) {
			Attributes attrs = new BasicAttributes(true);
			Attribute objectClass = new BasicAttribute("objectClass");
			objectClass.add("person");
			objectClass.add("inetOrgPerson");
			attrs.put(objectClass);
			attrs.put("cn", cn);
			attrs.put("uid", uid);
			attrs.put("gn", gn);
			attrs.put("sn", sn);
			attrs.put("description", description);
			attrs.put("mail", mail);
			attrs.put("userPassword", userPassword);
			DirContext subContext = context.createSubcontext(name, attrs);
			log.info("created " + name);
			subContext.close();
		}
	}
	
	/**
	 * Create groupOfNames
	 * @param context
	 * @param name
	 * @param cn
	 * @param description
	 * @param members
	 * @throws NamingException
	 */
	private static void createGroupOfNames(LdapContext context, String name, String cn, String description, String[] members) throws NamingException {
		String[] returningAttrs = { "name", "objectClass" };
		String filter = "(objectClass=groupOfNames)";
		SearchResult result = findObjectByName(context, name, filter, returningAttrs);
		if (result == null) {
			Attributes attrs = new BasicAttributes(true);
			attrs.put("objectClass", "groupOfNames");
			attrs.put("cn", cn);
			attrs.put("description", description);
			for (String member : members) {
				attrs.put("member", member);
			}
			DirContext subContext = context.createSubcontext(name, attrs);
			log.info("created " + name);
			subContext.close();
		}
	}
	
	/**
	 * Find object by Name
	 * @param context LDAP Context
	 * @param name Name of object
	 * @param filter Filter
	 * @param returningAttrs attributes returned in {@link SearchResult}
	 * @return Search Object search result. Return {@code null} if name was not found.
	 * @throws NamingException
	 */
	public static SearchResult findObjectByName(LdapContext context, String name, String filter, String[] returningAttrs) throws NamingException {
		SearchResult result = null;
		SearchControls controls = new SearchControls();
		controls.setReturningAttributes(returningAttrs);
		controls.setSearchScope(SearchControls.OBJECT_SCOPE);
		try {
			NamingEnumeration<SearchResult> results = context.search(name, filter, controls);
			if (results.hasMore()) {
				result = results.next();
			}
			return result;
		} catch (NameNotFoundException e) {
			return null;
		}
	}

}
