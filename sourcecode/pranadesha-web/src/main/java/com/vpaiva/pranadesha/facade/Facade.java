/**
 * 
 */
package com.vpaiva.pranadesha.facade;

import java.util.List;

/**
 * @author vinicius
 * @version 1.0, 2017-10-13
 */
public interface Facade<T, IdT> {
	
	/**
	 * Creates new entity
	 * @param entity Entity to be created
	 */
	void create(T entity);
	
	/**
	 * Updates existent entity
	 * @param entity Entity to be updated
	 * @return Updated entity
	 */
	T update(T entity);
	
	/**
	 * Deletes a entity
	 * @param id Id from entity to be deleted
	 * @return Deleted entity
	 * @throws FacadeException
	 */
	T delete(IdT id) throws FacadeException;
	
	/**
	 * Get a entity by Id
	 * @param id Id from entity
	 * @return Entity or null if not found
	 */
	T getById(IdT id);
	
	/**
	 * Get all entities
	 * @param page Number of page starting at 0
	 * @param size Size of list to be returned
	 * @return List of entities
	 */
	List<T> getAll(int page, int size);
	
}
