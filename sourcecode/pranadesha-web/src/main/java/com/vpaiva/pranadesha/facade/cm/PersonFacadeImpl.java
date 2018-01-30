package com.vpaiva.pranadesha.facade.cm;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.core.TransactionManager;
import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.core.cm.domain.Person;
import com.vpaiva.pranadesha.core.cm.domain.PersonRepository;
import com.vpaiva.pranadesha.core.cm.domain.User;
import com.vpaiva.pranadesha.core.cm.domain.UserLocale;
import com.vpaiva.pranadesha.core.cm.domain.UserLocaleRepository;
import com.vpaiva.pranadesha.core.cm.domain.UserRepository;
import com.vpaiva.pranadesha.core.cm.domain.UserRole;
import com.vpaiva.pranadesha.core.cm.domain.UserTimeZone;
import com.vpaiva.pranadesha.core.cm.domain.UserTimeZoneRepository;
import com.vpaiva.pranadesha.facade.FacadeException;

/**
 * implementation class PersonFacade
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 * 
 */
@RequestScoped
public class PersonFacadeImpl implements PersonFacade {

	/**
	 * Person Repository
	 */
	@Inject
	private PersonRepository personRepository;
	
	/**
	 * User Repository
	 */
	@Inject
	private UserRepository userRepository;
	
	/**
	 * User locale repository
	 */
	@Inject 
	private UserLocaleRepository userLocaleRepository;
	
	/**
	 * User time-zone repository
	 */
	@Inject
	private UserTimeZoneRepository userTimeZoneRepository;
	
	/**
	 * Transaction Manager
	 */
	@Inject
	private TransactionManager tx;
	
	/**
	 * log
	 */
	private static final Logger log = LogManager.getLogger(PersonFacadeImpl.class);
	
    /**
     * Default constructor. 
     */
    public PersonFacadeImpl() { }
    
    /*
     * (non-Javadoc)
     * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getAllNaturalPerson(int, int)
     */
	@Override
	public List<NaturalPerson> getAllNaturalPerson(int page, int size) {
		List<NaturalPerson> l = personRepository.getAllNaturalPerson(page, size);
		return l;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getNaturalPersonCount()
	 */
	@Override
	public Long getNaturalPersonCount() {
		return personRepository.getNaturalPersonCount();
	}

	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#saveNaturalPerson(com.vpaiva.pranadesha.core.cm.domain.NaturalPerson)
	 */
	@Override
	public void saveNaturalPerson(NaturalPerson entity) throws FacadeException {
		try {
			personRepository.saveNaturalPerson(entity);
		} catch (Exception e) {
			log.error("error creating natural person", e);
			throw new FacadeException(e.getMessage(), e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getNaturalPersonById(java.lang.Integer)
	 */
	@Override
	public NaturalPerson getNaturalPersonById(Integer id) {
		NaturalPerson entity = personRepository.getNaturalPersonById(id);
		return entity;
	}

	/* 
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#delete(java.lang.Integer)
	 */
	@Override
	public Person delete(Integer id) throws FacadeException {
		NaturalPerson entity = getNaturalPersonById(id);
		try {
			personRepository.delete(entity);
			return entity;
		} catch (Exception e) {
			log.error("error removing natural person", e);
			throw new FacadeException(e.getMessage(), e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#createUser(com.vpaiva.pranadesha.core.cm.domain.User)
	 */
	@Override
	public void createUser(User entity) throws FacadeException {
		try {
			entity.addRole("USERS");
			userRepository.save(entity);
		} catch (Exception e) {
			log.error("error creating user " + entity, e);
			throw new FacadeException("error creating user " + entity, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#updateUser(com.vpaiva.pranadesha.core.cm.domain.User)
	 */
	@Override
	public User updateUser(User entity) throws FacadeException {
		User user = userRepository.getById(entity.getName());
		user.setPassword(entity.getPassword());
		Set<UserRole> rolesToRemove = new HashSet<UserRole>();
		for (UserRole r : user.getRoles()) {
			if (!entity.getRoles().contains(r)) {
				rolesToRemove.add(r);
			}
		}
		if (rolesToRemove.size() > 0) {
			user.getRoles().removeAll(rolesToRemove);
		}
		for (UserRole r : entity.getRoles()) {
			if (!user.getRoles().contains(r)) {
				user.addRole(r.getRole());
			}
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#updateUser(java.lang.String, com.vpaiva.pranadesha.core.cm.domain.UserLocale)
	 */
	@Override
	public User updateUser(String id, UserLocale locale) {
		User user = userRepository.getById(id);
		user.setUserLocale(locale);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getUserById(java.lang.String)
	 */
	@Override
	public User getUserById(String id) {
		return userRepository.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getAvailableLocales()
	 */
	@Override
	public List<UserLocale> getAvailableLocales() {
		return userLocaleRepository.getAll(0, Integer.MAX_VALUE);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getUserLocaleById(java.lang.String)
	 */
	@Override
	public UserLocale getUserLocaleById(String id) {
		return userLocaleRepository.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#beginTransaction()
	 */
	@Override
	public void beginTransaction() {
		tx.beginTransaction();
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#commit()
	 */
	@Override
	public void commit() {
		tx.commit();
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#rollback()
	 */
	@Override
	public void rollback() {
		tx.rollback();
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#signup(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void signup(String givenName, String surname, String mail, String password) throws FacadeException {
		NaturalPerson n = new NaturalPerson(surname, givenName, null, null, mail, null, null, null, null, null, null);
		personRepository.saveNaturalPerson(n);
		User u = new User(mail, password);
		userRepository.save(u);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getNaturalPersonByMail(java.lang.String)
	 */
	@Override
	public NaturalPerson getNaturalPersonByMail(String mail) {
		List<NaturalPerson> l = personRepository.getNaturalPersonByCriteria(new String[] { "mail" }, new Object[] { mail });
		if (l.size() == 0) {
			return null;
		}
		return l.get(0);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getAvailableTimezones()
	 */
	@Override
	public List<UserTimeZone> getAvailableTimeZones() {
		return userTimeZoneRepository.getAll(0, Integer.MAX_VALUE);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#getUserTimeZoneById(java.lang.String)
	 */
	@Override
	public UserTimeZone getUserTimeZoneById(String id) {
		return userTimeZoneRepository.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#update(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<NaturalPerson, User> update(Integer id, String givenName, String middleName, String surname,
			Date birthDate, String mail, String mobile, String streetAddress, String city, String province,
			String zipCode, String language, String timezone) throws FacadeException {
		NaturalPerson p = personRepository.getNaturalPersonById(id);
		p.setGivenName(givenName);
		p.setMiddleName(middleName);
		p.setSurname(surname);
		p.setBirthDate(birthDate);
		p.setMail(mail);
		p.setMobile(mobile);
		p.setStreetAddress(streetAddress);
		p.setCity(city);
		p.setProvince(province);
		p.setZipCode(zipCode);
		User u = userRepository.getById(mail);
		UserLocale l = userLocaleRepository.getById(language);
		u.setUserLocale(l);
		UserTimeZone z = userTimeZoneRepository.getById(timezone);
		u.setUserTimeZone(z);
		Map<NaturalPerson, User> r = new HashMap<NaturalPerson, User>();
		r.put(p, u);
		return r;
	}

}
