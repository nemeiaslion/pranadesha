/**
 * 
 */
package com.vpaiva.pranadesha.core.cm.domain;

import javax.enterprise.context.RequestScoped;

import com.vpaiva.pranadesha.core.JpaRepository;

/**
 * @author vinic
 *
 */
@RequestScoped
public class UserLocaleJPARepository extends JpaRepository<UserLocale, String> implements UserLocaleRepository {

}
