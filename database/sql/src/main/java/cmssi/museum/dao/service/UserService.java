package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;

import cmssi.museum.dao.entity.User;

/**
 * the UserService is an extended CRUDService dedicated to {@link User} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class UserService extends CRUDService<User> {
	
	/**
	 * Constructor
	 */
	public UserService(){
		super(User.class);
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
