/**
 * 
 */
package com.vpaiva.pranadesha.core;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;

/**
 * @author vinicius
 *
 */
public abstract class JpaRepository<T, IdT> implements Repository<T, IdT> {
	
	/**
	 * Session Factory
	 */
	@Inject
	protected HibernateSessionFactory sessionFactory;

	/**
	 * JPA Entity Manager
	 */
	protected EntityManager em;
	
	/**
	 * Transaction 
	 */
	protected Transaction tx;

	/**
	 * entityClass
	 */
	protected Class<T> entityClass;
	
	/**
	 * Default Constructor
	 */
	@SuppressWarnings("unchecked")
	public JpaRepository() {
		ParameterizedType genericSuperclass = null;
		try {
			genericSuperclass = (ParameterizedType)getClass().getGenericSuperclass();
		} catch (Exception e) {
			genericSuperclass = (ParameterizedType)getClass().getSuperclass().getGenericSuperclass();
		}
		entityClass = (Class<T>)genericSuperclass.getActualTypeArguments()[0];
	}
	
	/**
	 * init
	 */
	@PostConstruct
	protected void init() {
		joinSession(sessionFactory.getCurrentSession());
	}
	
	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.Repository#getAll(int, int)
	 */
	@Override
	public List<T> getAll(int page, int size) {
		return getAll(entityClass, page, size);
	}
	
	/**
	 * Parameterized query for get all
	 * @param entityClass Class entity
	 * @param page Index of page starting at 0
	 * @param size Size of page
	 * @return List of R
	 */
	protected <R> List<R> getAll(Class<R> entityClass, int page, int size) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<R> criteria = builder.createQuery(entityClass);
		Root<R> root = criteria.from(entityClass);
		criteria.select(root);
		TypedQuery<R> query = em.createQuery(criteria);
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.Repository#getCount()
	 */
	@Override
	public Long getCount() {
		return getCount(entityClass);
	}
	
	/**
	 * Parameterized get count
	 * @param entityClass Class entity
	 * @return count of R
	 */
	protected <R> Long getCount(Class<R> entityClass) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<R> t = criteria.from(entityClass);
		criteria.select(builder.count(t));
		return em.createQuery(criteria).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.Repository#joinSession(java.lang.Object)
	 */
	@Override
	public void joinSession(Object session) {
		this.em = (EntityManager) session;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.Repository#save(java.lang.Object)
	 */
	@Override
	public void save(T entity) {
		save(entity, entityClass);
	}
	
	/**
	 * Save
	 * @param entity
	 * @param entityClass
	 */
	protected <R> void save(R entity, Class<R> entityClass) {
		em.persist(entity);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.Repository#getById(java.lang.Object)
	 */
	@Override
	public T getById(IdT id) {
		return getById(id, entityClass);
	}
	
	/**
	 * Find a entity
	 * @param id Id of entity
	 * @param entityClass Type Class of Entity
	 * @return the entity
	 */
	protected <R> R getById(IdT id, Class<R> entityClass) {
		return em.find(entityClass, id);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.Repository#delete(java.lang.Object)
	 */
	@Override
	public void delete(T entity) {
		em.remove(entity);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.core.Repository#getByCriteria(java.lang.Object[], java.lang.Object[])
	 */
	@Override
	public List<T> getByCriteria(String[] parms, Object[] values) {
		return getByCriteria(entityClass, parms, values);
	}
	
	protected <R> List<R> getByCriteria(Class<R> entityClass, String[] parms, Object[] values) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<R> criteria = cb.createQuery(entityClass);
		Root<R> root = criteria.from(entityClass);
		criteria.select(root);
		for (int i = 0; i < parms.length; i++) {
			String parm = parms[i];
			Object value = values[i];
			criteria.where(cb.equal(root.get(parm), value));
		}
		TypedQuery<R> query = em.createQuery(criteria);
		return query.getResultList();
	}

}
