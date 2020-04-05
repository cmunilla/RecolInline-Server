/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import cmssi.museum.dao.entity.Domain;
import cmssi.museum.dao.entity.Model;

/**
 * the ModelService is an extended CRUDService dedicated to {@link Model} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class ModelService extends CRUDService<Model> {
	
	/**
	 * Constructor
	 */
	public ModelService(){
		super(Model.class);
	}

	/**
	 * Returns the List of {@link Model}s attached to the {@link Domain}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idDomain the Integer identifier of the {@link Domain}
	 * 
	 * @return the List of {@link Model}s for the specified {@link Domain}
	 */
	public List<Model> getModels(Integer idDomain){
		Query query = getSession().getNamedQuery("ModelsFromDomain");
		query.setParameter("idDomainFk", idDomain);
		return (List<Model>) query.list();
	}

}
