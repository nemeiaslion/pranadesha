/**
 * 
 */
package com.vpaiva.pranadesha.web.cm.user;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.core.cm.domain.User;
import com.vpaiva.pranadesha.core.cm.domain.UserLocale;
import com.vpaiva.pranadesha.facade.cm.PersonFacade;

/**
 * @author vinic
 *
 */
/**
 * @author vinic
 *
 */
@SessionScoped
@Named("userMB")
public class UserMB implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * logger
	 */
	private static final Logger log = LogManager.getLogger(UserMB.class);
	
	/**
	 * Person Facade
	 */
	@Inject
	private PersonFacade personFacade;
	
	/**
	 * User's Locale
	 */
	private Locale locale;
	
	/**
	 * Locale code
	 */
	private String localeCode;
	
	/**
	 * User Name
	 */
	private String userName;
	
	/**
	 * User
	 */
	private User user;
	
	/**
	 * Set of User Locale
	 */
	private Set<UserLocaleVO> userLocales;
	
	/**
	 * Surname
	 */
	private String surname;
	
	/**
	 * Given name
	 */
	private String givenName;
	
	/**
	 * When {@code true} indicates that data needs to be refreshed
	 */
	private Boolean dirty = false;
	
	/**
	 * Load locales
	 */
	private void loadLocales() {
		List<UserLocale> l = personFacade.getAvailableLocales();
		Locale locale = getLocale();
		userLocales = new TreeSet<UserLocaleVO>();
		for (UserLocale userLocale : l) {
			UserLocaleVO vo = new UserLocaleVO(userLocale.getId(), userLocale.getLocale().getDisplayName(locale));
			userLocales.add(vo);
		}
	}

	/**
	 * value change event listener
	 * 
	 * @param e
	 */
	public void localeChanged(ValueChangeEvent e) {
		String newLocaleValue = e.getNewValue().toString();
		UserLocale userLocale = personFacade.getUserLocaleById(newLocaleValue);
		setLocale(userLocale.getLocale());
		loadLocales();
		if (getAuthenticated()) {
			personFacade.beginTransaction();
			User user = personFacade.updateUser(getUserName(), userLocale);
			personFacade.commit();
			log.info("updated " + user);
		}
	}

	/**
	 * @return the localeCode
	 */
	public String getLocaleCode() {
		if (dirty) {
			getUser();
		}
		if (localeCode == null) {
			getLocale().toString();
		}
		return localeCode;
	}

	/**
	 * @param localeCode the localeCode to set
	 */
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		if (dirty) {
			getUser();
		}
		if (locale == null) {
			return FacesContext.getCurrentInstance().getViewRoot().getLocale();
		}
		return locale;
	}
	
	/**
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		this.locale = locale;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		if (dirty) {
			getUser();
		}
		return userName;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		if (dirty) {
			String name = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			User user = personFacade.getUserById(name);
			setUser(user);
			dirty = false;
		}
		return user;
	}

	/**
	 * @param user the user to set
	 */
	private void setUser(User user) {
		this.user = user;
		this.userName = user.getName();
		NaturalPerson p = personFacade.getNaturalPersonByMail(userName);
		this.givenName = p.getGivenName();
		this.surname = p.getSurname();
		if (user.getUserLocale() != null) {
			setLocale(user.getLocale());
			setLocaleCode(user.getLocale().toString());
		}
	}

	/**
	 * @return the userLocales
	 */
	public Set<UserLocaleVO> getUserLocales() {
		if (userLocales == null) {
			loadLocales();
		}
		return userLocales;
	}
	
	/**
	 * @return true if user is authenticated
	 */
	public Boolean getAuthenticated() {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (principal == null || principal.getName() == null) {
			return Boolean.FALSE;
		} else {
			String name = principal.getName();
			String loggedName = getUserName();
			if (!name.equals(loggedName)) {
				dirty = true;
			}
			return true;
		}
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		if (dirty) {
			getUser();
		}
		return surname;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		if (dirty) {
			getUser();
		}
		return givenName;
	}
}
