/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.dao.service;

import javax.ejb.Stateless;

import cmssi.museum.dao.entity.Museum;

/**
 * the MuseumService is an extended CRUDService dedicated to {@link Museum} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class MuseumService extends CRUDService<Museum> {
	
	/**
	 * Constructor
	 */
	public MuseumService(){
		super(Museum.class);
	}
}
