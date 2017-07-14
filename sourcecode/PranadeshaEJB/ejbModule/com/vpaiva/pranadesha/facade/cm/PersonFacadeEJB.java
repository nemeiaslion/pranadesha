package com.vpaiva.pranadesha.facade.cm;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vpaiva.pranadesha.core.cm.domain.NaturalPerson;
import com.vpaiva.pranadesha.core.cm.domain.PersonRepository;
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
	public void saveNaturalPerson(NaturalPerson entity) {
		personRepository.saveNaturalPerson(entity);
		try {
			Map<String, String> attrs = personSecurityService.find(entity.getMail());
			if (attrs.size() == 0) {
				String name = personSecurityService.createSimpleSecurityObject(entity.getMail(), entity.getUserPassword());
				if (!personSecurityService.isMemberOf("USERS", name)) {
					personSecurityService.addToGroup("USERS", name);
				}
			}
		} catch (NamingException e) {
			log.catching(e);
		}
	}

}
