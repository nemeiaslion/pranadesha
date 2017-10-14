package com.vpaiva.pranadesha.facade.um;

import javax.ejb.Local;

import com.vpaiva.pranadesha.core.um.domain.Workshop;
import com.vpaiva.pranadesha.facade.Facade;

@Local
public interface WorkshopFacade extends Facade<Workshop, Integer> {

}
