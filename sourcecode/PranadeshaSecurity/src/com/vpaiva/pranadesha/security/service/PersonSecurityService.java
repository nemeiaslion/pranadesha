/**
 * 
 */
package com.vpaiva.pranadesha.security.service;

import java.security.NoSuchAlgorithmException;
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

import com.vpaiva.pranadesha.security.PolicySecurity;
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
	
	@Inject
	private PolicySecurity policySecurity;
	
	public String createPerson(String sn, String mail, String userPassword, String telephoneNumber, String seeAlso, String description) throws NamingException, NoSuchAlgorithmException {
		String name = "cn=" + mail + ",ou=People," + BASE_SEARCH;
		Attributes attrs = new BasicAttributes(true);
		attrs.put("objectClass", "inetOrgPerson");
		attrs.put("sn", sn);
		attrs.put("cn", mail);
		attrs.put("uid", mail);
		attrs.put("mail", mail);
		String hash = policySecurity.hashPassword(userPassword);
		attrs.put("userPassword", hash);
		attrs.put("telephoneNumber", telephoneNumber);
		attrs.put("seeAlso", seeAlso);
		attrs.put("description", description);
		DirContext subContext = context.createSubcontext(name, attrs);
		log.info("created " + name);
		subContext.close();
		return name;
	}
	
	public void addToGroup(String groupName, String member) throws NamingException {
		String name = "cn=" + groupName + ",ou=Groups," + BASE_SEARCH;
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
		String name = "cn=" + cn + ",ou=People," + BASE_SEARCH;
		if (isMemberOf("USERS", name)) {
			String groupName = "cn=USERS,ou=Groups," + BASE_SEARCH;
			Attribute memberAttr = new BasicAttribute("member", name);
			Attributes attrsToModify = new BasicAttributes();
			attrsToModify.put(memberAttr);
			context.modifyAttributes(groupName, LdapContext.REMOVE_ATTRIBUTE, attrsToModify);
			
		}
		context.destroySubcontext(name);
		log.info("deleted " + name);
	}
	
	public Map<String, String> find(String cn) throws NamingException {
		Map<String, String> foundResult = new HashMap<String, String>();
		String name = "cn=" + cn + ",ou=People," + BASE_SEARCH;
		String[] returningAttrs = { "sn", "cn", "userPassword", "telephoneNumber", "seeAlso", "description" };
		SearchControls controls = new SearchControls();
		controls.setReturningAttributes(returningAttrs);
		controls.setSearchScope(SearchControls.OBJECT_SCOPE);
		try {
			NamingEnumeration<SearchResult> results = context.search(name, "(objectClass=person)", controls);
			while (results.hasMore()) {
				SearchResult result = results.next();
				foundResult.put("name", result.getNameInNamespace());
				NamingEnumeration<? extends Attribute> attrs = result.getAttributes().getAll();
				while (attrs.hasMore()) {
					Attribute attr = attrs.next();
					String id = attr.getID();
					NamingEnumeration<?> values = attr.getAll();
					StringBuilder sb = new StringBuilder();
					while (values.hasMore()) {
						Object value = values.next();
						if (value instanceof byte[]) {
							sb.append(new String((byte[]) value));
						} else {
							sb.append(value);
						}
					}
					foundResult.put(id, sb.toString());
				}
			}
		} catch (NameNotFoundException e) {
			log.error(e);
		}
		return foundResult;
	}

}
