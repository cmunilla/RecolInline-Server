package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import cmssi.museum.dao.entity.Domain;
import cmssi.museum.dao.entity.Model;
import cmssi.museum.dao.entity.Type;
import cmssi.museum.dao.entity.User;

/**
 * the DomainService is an extended CRUDService dedicated to {@link Domain} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Stateless
public class DomainService extends CRUDService<Domain> {
	
	/**
	 * Constructor
	 */
	public DomainService(){
		super(Domain.class);
	}
	
	/**
	 * Returns the List of {@link Type}s attached to the {@link Domain}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idDomain the Integer identifier of the {@link Domain}
	 * 
	 * @return the List of {@link Type}s for the specified {@link Domain}
	 */
	public List<Type> getTypes(Integer idDomain)
	{
		Query query = getSession().getNamedQuery("TypesFromDomain");
		query.setParameter("idDomainFk", idDomain);
		return (List<Type>) query.list();
	}
	
	/**
	 * Returns the List of {@link Model}s attached to the {@link Domain}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idDomain the Integer identifier of the {@link Domain}
	 * 
	 * @return the List of {@link Model}s for the specified {@link Domain}
	 */
	public List<Model> getModels(Integer idDomain)
	{
		Query query = getSession().getNamedQuery("ModelsFromDomain");
		query.setParameter("idDomainFk", idDomain);
		return (List<Model>) query.list();
	}

}
