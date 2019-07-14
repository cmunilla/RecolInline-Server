package cmssi.museum.dao.service;

import javax.ejb.Stateless;

import cmssi.museum.dao.entity.Model;

/**
 * the ModelService is an extended CRUDService dedicated to {@link Model} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Stateless
public class ModelService extends CRUDService<Model> {
	
	/**
	 * Constructor
	 */
	public ModelService(){
		super(Model.class);
	}
	
}
