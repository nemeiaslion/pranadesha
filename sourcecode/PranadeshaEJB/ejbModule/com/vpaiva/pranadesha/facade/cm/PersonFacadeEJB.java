package com.vpaiva.pranadesha.facade.cm;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.core.cm.domain.Person;
import com.vpaiva.pranadesha.core.cm.domain.PersonRepository;
import com.vpaiva.pranadesha.facade.FacadeException;
import com.vpaiva.pranadesha.security.service.PersonSecurityService;

/**
 * Session Bean implementation class PersonEJB
 * 
 * @author vinicius
 * @version 1.0, 2017-07-11
 * 
 */
@Stateless
public class PersonFacadeEJB implements PersonFacade {
	
	/**
	 * Person Repository
	 */
	@Inject
	private PersonRepository personRepository;
	
	/**
	 * Person Security Service
	 */
	@Inject
	private PersonSecurityService personSecurityService;
	
	/**
	 * log
	 */
	private static final Logger log = LogManager.getLogger(PersonFacadeEJB.class);
	
    /**
     * Default constructor. 
     */
    public PersonFacadeEJB() { }
    
    /*
     * (non-Javadoc)
     * @see com.vpaiva.pranadesha.facade.cm.PersonEJBLocal#getAllNaturalPerson(int, int)
     */
	@Override
	public List<NaturalPerson> getAllNaturalPerson(int page, int size) {
		List<NaturalPerson> l = personRepository.getAllNaturalPerson(page, size);
		return l;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonEJBLocal#getNaturalPersonCount()
	 */
	@Override
	public Long getNaturalPersonCount() {
		return personRepository.getNaturalPersonCount();
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonEJBLocal#save(com.vpaiva.pranadesha.core.cm.domain.NaturalPerson)
	 */
	@Override
	public void saveNaturalPerson(NaturalPerson entity) throws FacadeException {
		try {
			Map<String, String> attrs = personSecurityService.find(entity.getMail());
			if (attrs.size() == 0) {
				String name = personSecurityService.createPerson(entity.getSurname(), entity.getMail(), entity.getUserPassword(), entity.getMobile(), "", entity.getDescription());
				if (!personSecurityService.isMemberOf("USERS", name)) {
					personSecurityService.addToGroup("USERS", name);
				}
			}
			personRepository.saveNaturalPerson(entity);
		} catch (NamingException | NoSuchAlgorithmException e) {
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
		try {
			Map<String, String> attrs = personSecurityService.find(entity.getMail());
			if (attrs.size() > 0) {
				entity.setUserPassword(attrs.get("userPassword"));
				entity.setDescription(attrs.get("description"));
			}
		} catch (NamingException e) {
			log.error(e);
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.cm.PersonFacade#delete(java.lang.Integer)
	 */
	@Override
	public Person delete(Integer id) throws FacadeException {
		NaturalPerson entity = getNaturalPersonById(id);
		try {
			personSecurityService.delete(entity.getMail());
			personRepository.delete(entity);
			return entity;
		} catch (NamingException e) {
			log.error("error removing natural person", e);
			throw new FacadeException(e.getMessage(), e);
		}
		
	}

}
