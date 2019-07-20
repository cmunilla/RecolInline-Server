package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;

import cmssi.museum.dao.entity.Museum;
import cmssi.museum.dao.entity.Sheet;
import cmssi.museum.dao.entity.User;

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
