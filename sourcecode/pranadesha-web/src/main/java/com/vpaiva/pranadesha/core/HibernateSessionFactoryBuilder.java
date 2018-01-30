package com.vpaiva.pranadesha.core;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * @author vinicius
 * @version 1.0, 2018-02-07
 */
@Singleton
public class HibernateSessionFactoryBuilder {
	
	/**
	 * Session Factory
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * Build Session Factory
	 */
	@PostConstruct
	void buildSessionFactory() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		try {
			MetadataSources sources = new MetadataSources(serviceRegistry);
			sources.addResource("com/vpaiva/pranadesha/core/um/domain/Course.hbm.xml");
			sources.addResource("com/vpaiva/pranadesha/core/um/domain/Workshop.hbm.xml");
			sources.addResource("com/vpaiva/pranadesha/core/cm/domain/Person.hbm.xml");
			sources.addResource("com/vpaiva/pranadesha/core/cm/domain/User.hbm.xml");
			//
			Metadata metadata = sources.buildMetadata();
			sessionFactory = metadata.buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * @return Session Factory
	 */
	@Produces
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Destroy Session Factory
	 */
	@PreDestroy
	void destroySessionFactory() {
		sessionFactory.close();
	}
	
	
}
