package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;
import org.hibernate.Query;
import cmssi.museum.dao.entity.Type;

/**
 * the TypeService is an extended CRUDService dedicated to {@link Type} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class TypeService extends CRUDService<Type> {
	
	/**
	 * Constructor
	 */
	public TypeService(){
		super(Type.class);
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
	
}
