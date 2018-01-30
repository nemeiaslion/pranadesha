package com.vpaiva.pranadesha.core;

import java.util.List;

/**
 * Repository 
 * @author vinicius
 * @version 1.0, 2017-07-11
 * 
 * @param <T> Entity type
 * @param <IdT> Id Type of Entity
 */
public interface Repository<T, IdT> {
	
	/**
	 * Get all items
	 * 
	 * @param page Index of page starting at 0
	 * @param size Size of page
	 * @return List of items
	 */
	List<T> getAll(int page, int size);
	
	/**
	 * Get count of items
	 * 
	 * @return Count of items
	 */
	Long getCount();
	
	/**
	 * Joins a session
	 * @param session Transaction to be joined
	 */
	void joinSession(Object session);
	
	/**
	 * Persists the entity
	 * @param entity Entity to be persisted
	 */
	void save(T entity);
	
	/**
	 * Removes entity from repository
	 * @param entity Entity to be removed
	 */
	void delete(T entity);
	
	/**
	 * Get entity by id
	 * @param id primary key value
	 * @return Entity identified by id
	 */
	T getById(IdT id);
	
	/**
	 * Get list of T by criteria
	 * 
	 * @param parms
	 * @param values
	 * @return
	 */
	List<T> getByCriteria(String[] parms, Object[] values);
	
}
