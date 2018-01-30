/**
 * 
 */
package com.vpaiva.pranadesha.web.cm.user;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.core.cm.domain.User;
import com.vpaiva.pranadesha.core.cm.domain.UserLocale;
import com.vpaiva.pranadesha.core.cm.domain.UserTimeZone;
import com.vpaiva.pranadesha.facade.FacadeException;
import com.vpaiva.pranadesha.facade.cm.PersonFacade;
import com.vpaiva.pranadesha.web.GenericRequestScopedMB;

/**
 * @author vinic
 *
 */
@RequestScoped
@Named("myProfileMB")
public class MyProfileMB extends GenericRequestScopedMB {
	
	/**
	 * Logger
	 */
	private static final Logger log = LogManager.getLogger(MyProfileMB.class);
	
	private Integer id;
	
	/**
	 * Given Name
	 */
	private String givenName;
	
	/**
	 * Middle Name
	 */
	private String middleName;
	
	/**
	 * Surname
	 */
	private String surname;
	
	/**
	 * Birthday date
	 */
	private Date birthDate;
	
	/**
	 * Mail
	 */
	private String mail;
	
	/**
	 * Mobile
	 */
	private String mobile;
	
	/**
	 * Street address
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
	 * Zip Code
	 */
	private String zipCode;
	
	/**
	 * Language
	 */
	private String language;
	
	/**
	 * Time-zone
	 */
	private String timezone;
	
	/**
	 * Languages
	 */
	private Set<UserLocaleVO> languages;
	
	/**
	 * Time-zones
	 */
	private Set<UserTimeZoneVO> timezones;
	
	/**
	 * Person Facade
	 */
	@Inject
	private PersonFacade personFacade;
	
	/**
	 * User Managed Bean
	 */
	@Inject
	private UserMB userMB;
	
	/**
	 * Initialize
	 */
	@PostConstruct
	public void init() {
		loadLanguages();
		loadTimeZones();
		loadData();
	}
	
	/**
	 * Fill data from User storage in session
	 */
	private void loadData() {
		NaturalPerson person = personFacade.getNaturalPersonByMail(userMB.getUserName());
		id = person.getId();
		givenName = person.getGivenName();
		middleName = person.getMiddleName();
		surname = person.getSurname();
		birthDate = person.getBirthDate();
		mail = person.getMail();
		mobile = person.getMobile();
		streetAddress = person.getStreetAddress();
		city = person.getCity();
		province = person.getProvince();
		zipCode = person.getZipCode();
		User user = personFacade.getUserById(userMB.getUserName());
		language = (user.getUserLocale() == null) ? "" : user.getUserLocale().getId();
		timezone = (user.getUserTimeZone() == null) ? "" : user.getUserTimeZone().getId();
	}
	
	/**
	 * Load languages
	 */
	private void loadLanguages() {
		List<UserLocale> l = personFacade.getAvailableLocales();
		languages = new TreeSet<UserLocaleVO>();
		Locale locale = getLocale();
		for (UserLocale userLocale : l) {
			UserLocaleVO vo = new UserLocaleVO(userLocale.getId(), userLocale.getLocale().getDisplayName(locale));
			languages.add(vo);
		}
	}
	
	/**
	 * Load Time Zones
	 */
	private void loadTimeZones() {
		List<UserTimeZone> l = personFacade.getAvailableTimeZones();
		timezones = new TreeSet<UserTimeZoneVO>();
		Locale locale = getLocale();
		for (UserTimeZone i : l) {
			TimeZone timeZone = TimeZone.getTimeZone(i.getId());
			UserTimeZoneVO vo = new UserTimeZoneVO(timeZone, locale);
			timezones.add(vo);
		}
	}
	
	/**
	 * Save
	 */
	public void save() {
		log.traceEntry();
		try {
			personFacade.beginTransaction();
			Map<NaturalPerson, User> r = personFacade.update(id, givenName, middleName, surname, birthDate, mail, mobile, streetAddress, city, province, zipCode, language, timezone);
			Entry<NaturalPerson, User> e = r.entrySet().iterator().next();
			User u = e.getValue();
			userMB.setLocale(u.getLocale());
			personFacade.commit();
		} catch (FacadeException e) {
			personFacade.rollback();
			log.catching(e);
		}
		log.traceExit();
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
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * 
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the languages
	 */
	public Set<UserLocaleVO> getLanguages() {
		return languages;
	}

	/**
	 * @return the timezones
	 */
	public Set<UserTimeZoneVO> getTimezones() {
		return timezones;
	}
}
