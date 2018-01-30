/**
 * 
 */
package com.vpaiva.pranadesha.core.um.domain;

import javax.enterprise.context.RequestScoped;

import com.vpaiva.pranadesha.core.JpaRepository;

/**
 * @author vinicius
 *
 */
@RequestScoped
class WorkshopJpaRepository extends JpaRepository<Workshop, Integer> implements WorkshopRepository {

}
