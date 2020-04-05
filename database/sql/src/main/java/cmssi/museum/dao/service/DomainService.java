/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.dao.service;

import javax.ejb.Stateless;

import cmssi.museum.dao.entity.Domain;

/**
 * the DomainService is an extended CRUDService dedicated to {@link Domain} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class DomainService extends CRUDService<Domain> {
	
	/**
	 * Constructor
	 */
	public DomainService(){
		super(Domain.class);
	}

}
