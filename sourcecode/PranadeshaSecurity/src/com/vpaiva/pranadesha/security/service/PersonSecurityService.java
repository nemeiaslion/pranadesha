/**
 * 
 */
package com.vpaiva.pranadesha.security.service;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
import javax.naming.ldap.LdapContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.security.SecurityProperties;

/**
 * @author vinicius
 *
 */
@RequestScoped
public class PersonSecurityService {
	
	private static final Logger log = LogManager.getLogger(PersonSecurityService.class);
	
	private static final String BASE_SEARCH = SecurityProperties.getInstance().getBaseSearchDN();
	
	@Inject
	private LdapContext context;
	
	public String createSimpleSecurityObject(String cn, String userPassword) throws NamingException {
		String name = "cn=" + cn + "ou=People," + BASE_SEARCH;
		Attributes attrs = new BasicAttributes(true);
		attrs.put("objectClass", "simpleSecurityObject");
		attrs.put("userPassword", userPassword);
		DirContext subContext = context.createSubcontext(name, attrs);
		log.info("created " + name);
		subContext.close();
		return name;
	}
	
	public void addToGroup(String groupName, String member) throws NamingException {
		String name = "cn=" + groupName + "ou=Groups," + BASE_SEARCH;
		Attribute memberAttr = new BasicAttribute("member", member);
		Attributes attrsToModify = new BasicAttributes();
		attrsToModify.put(memberAttr);
		context.modifyAttributes(name, LdapContext.ADD_ATTRIBUTE, attrsToModify);
	}
	
	public boolean isMemberOf(String groupName, String member) throws NamingException {
		String filter = "(&(objectClass=groupOfNames)(cn=" + groupName + ")(member=" + member + "))";
		SearchControls controls = new SearchControls();
		String[] returningAttrs = { "name", "member", "cn" };
		controls.setReturningAttributes(returningAttrs);
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		try {
			NamingEnumeration<SearchResult> results = context.search(BASE_SEARCH, filter, controls);
			if (results.hasMore()) {
				results.next();
				return true;
			} else {
				return false;
			}
			
		} catch (NameNotFoundException e) { 
			return false;
		}
	}
	
	public void delete(String cn) throws NamingException {
		String name = "cn=" + cn + "ou=People," + BASE_SEARCH;
		context.destroySubcontext(name);
		log.info("deleted " + name);
	}
	
	public Map<String, String> find(String cn) throws NamingException {
		Map<String, String> foundResult = new HashMap<String, String>();
		String name = "cn=" + cn + ",ou=People," + BASE_SEARCH;
		String[] returningAttrs = { "name" };
		SearchControls controls = new SearchControls();
		controls.setReturningAttributes(returningAttrs);
		controls.setSearchScope(SearchControls.OBJECT_SCOPE);
		try {
			NamingEnumeration<SearchResult> results = context.search(name, "(objectClass=simpleSecurityObject)", controls);
			SearchResult result = null;
			if ((result = results.next()) != null) {
				foundResult.put("name", result.getName());
				NamingEnumeration<? extends Attribute> attrs = result.getAttributes().getAll();
				Attribute attr = null;
				while ((attr = attrs.next()) != null) {
					foundResult.put(attr.getID(), attr.get().toString());
				}
			}
		} catch (NameNotFoundException e) {
			log.error(e);
		}
		return foundResult;
	}

}
