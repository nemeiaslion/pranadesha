package com.vpaiva.pranadesha.facade.cm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.core.cm.domain.Person;
import com.vpaiva.pranadesha.core.cm.domain.User;
import com.vpaiva.pranadesha.core.cm.domain.UserLocale;
import com.vpaiva.pranadesha.core.cm.domain.UserTimeZone;
import com.vpaiva.pranadesha.facade.FacadeException;

/**
 * Person Facace Service
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 *
 */
public interface PersonFacade {
	/**
	 * Get all natural person
	 * @param page Index of page starting at 0
	 * @param size Size of page
	 * @return List of natural person
	 */
	List<NaturalPerson> getAllNaturalPerson(int page, int size);
	
	/**
	 * Get count of natural person
	 * 
	 * @return count of natural person
	 * 
	 */
	Long getNaturalPersonCount();
	
	/**
	 * Save
	 * @param entity
	 */
	void saveNaturalPerson(NaturalPerson entity) throws FacadeException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	NaturalPerson getNaturalPersonById(Integer id);
	
	/**
	 * 
	 * @param id
	 * @return Deleted person
	 */
	Person delete(Integer id) throws FacadeException;
	
	/**
	 * Creates user
	 * 
	 * @param entity
	 * @throws FacadeException
	 */
	void createUser(User entity) throws FacadeException;
	
	/**
	 * Update user
	 * 
	 * @param entity
	 * @return
	 * @throws FacadeException
	 */
	User updateUser(User entity) throws FacadeException;
	
	/**
	 * Get user by Id
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(String id);
	
	/**
	 * @param id
	 * @param locale
	 * @return
	 */
	User updateUser(String id, UserLocale locale);
	
	/**
	 * @return
	 */
	List<UserLocale> getAvailableLocales();
	
	/**
	 * @param id
	 * @return
	 */
	UserLocale getUserLocaleById(String id);
	
	/**
	 * beginTransaction
	 */
	void beginTransaction();
	
	/**
	 * commit
	 */
	void commit();
	
	/**
	 * rollback
	 */
	void rollback();
	
	/**
	 * Sign-up
	 * @param givenName
	 * @param surname
	 * @param mail
	 * @param password
	 * @throws FacadeException
	 */
	void signup(String givenName, String surname, String mail, String password) throws FacadeException;
	
	/**
	 * Get Natural Person by e-mail
	 * 
	 * @param mail e-mail
	 * @return
	 */
	NaturalPerson getNaturalPersonByMail(String mail);
	
	/**
	 * All Available Time-zones
	 * 
	 * @return All Available Time-zones
	 */
	List<UserTimeZone> getAvailableTimeZones();
	
	/**
	 * Get user time zone by id
	 * @param id time-zone id
	 * @return Time zone
	 */
	UserTimeZone getUserTimeZoneById(String id);
	
	/**
	 * Update user and person
	 * 
	 * @param id Person ID
	 * @param givenName Given name
	 * @param middleName Middle name
	 * @param surname Surname
	 * @param birthDate Birthday date
	 * @param mail Mail
	 * @param mobile Mobile
	 * @param streetAddress Street address
	 * @param city City
	 * @param province Province
	 * @param zipCode ZIP Code
	 * @param language User default language
	 * @param timezone User default time-zone
	 * @return Person and User
	 * @throws FacadeException
	 */
	Map<NaturalPerson, User> update(Integer id, String givenName, String middleName, String surname, 
			Date birthDate, String mail, String mobile, String streetAddress, String city, 
			String province, String zipCode, String language, String timezone) throws FacadeException;
}
