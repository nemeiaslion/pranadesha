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
public class UserJPARepository extends JpaRepository<User, String> implements UserRepository {

}
