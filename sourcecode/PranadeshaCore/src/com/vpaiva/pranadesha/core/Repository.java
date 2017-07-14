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
	
}
