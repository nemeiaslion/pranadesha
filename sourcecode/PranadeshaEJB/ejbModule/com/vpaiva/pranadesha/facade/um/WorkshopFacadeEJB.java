package com.vpaiva.pranadesha.facade.um;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.vpaiva.pranadesha.core.um.domain.Workshop;
import com.vpaiva.pranadesha.core.um.domain.WorkshopRepository;
import com.vpaiva.pranadesha.facade.FacadeException;

/**
 * Session Bean implementation class WorkshopFacadeEJB
 */
@Stateless
public class WorkshopFacadeEJB implements WorkshopFacade {
	
	/**
	 * Workshop repository
	 */
	@Inject
	private WorkshopRepository workshopRepository;
	
    /**
     * Default constructor. 
     */
    public WorkshopFacadeEJB() { }

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#create(java.lang.Object)
	 */
	@Override
	public void create(Workshop entity) {
		workshopRepository.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#update(java.lang.Object)
	 */
	@Override
	public Workshop update(Workshop entity) {
		Workshop item = workshopRepository.getById(entity.getId());
		item.setCourse(entity.getCourse());
		item.setDescription(entity.getDescription());
		item.setEndDate(entity.getEndDate());
		item.setInitDate(entity.getInitDate());
		item.setNeighborhood(entity.getNeighborhood());
		item.setPhone(entity.getPhone());
		item.setStreet(entity.getStreet());
		item.setZip(entity.getZip());
		return item;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#delete(java.lang.Object)
	 */
	@Override
	public Workshop delete(Integer id) throws FacadeException {
		Workshop item = workshopRepository.getById(id);
		workshopRepository.delete(item);
		return item;
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#getById(java.lang.Object)
	 */
	@Override
	public Workshop getById(Integer id) {
		return workshopRepository.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.vpaiva.pranadesha.facade.Facade#getAll(int, int)
	 */
	@Override
	public List<Workshop> getAll(int page, int size) {
		return workshopRepository.getAll(page, size);
	}
    

}
