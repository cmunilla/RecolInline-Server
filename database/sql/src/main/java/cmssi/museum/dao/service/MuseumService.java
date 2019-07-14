package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;

import cmssi.museum.dao.entity.Museum;
import cmssi.museum.dao.entity.User;

/**
 * the MuseumService is an extended CRUDService dedicated to {@link Museum} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Stateless
public class MuseumService extends CRUDService<Museum> {
	
	/**
	 * Constructor
	 */
	public MuseumService(){
		super(Museum.class);
	}
	
	/**
	 * Returns the List of {@link User}s attached to the {@link Museum}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idMuseum the Integer identifier of the {@link Museum}
	 * 
	 * @return the List of {@link User}s for the specified {@link Museum}
	 */
	public List<User> getUsers(Integer idMuseum)
	{
		var query = getSession().getNamedQuery("UsersFromMuseum");
		query.setParameter("idMuseumFk", idMuseum);
		return (List<User>) query.list();
	}
	
}
