package cmssi.museum.dao.service;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * the CRUDService allows to create, read, update, and delete persistent 
 * instances of a specific entity type (i.e. @Entity annotated)
 * 
 * <T> the handled entity Type 
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@SuppressWarnings("unchecked")
abstract class CRUDService<T> extends ReadService<T>
{	
	/**
	 * Constructor
	 */
	public CRUDService(Class<T> entityType){
		super(entityType);
	}

	/**
	 * Adds the entity instance passed as parameter in 
	 * the associated persistence store
	 * 
	 * @param added the entity to be added
	 * 
	 * @return the Serializable identifier of the  newly created 
	 * entity in the associated persistence store
	 */
	public <K extends Serializable> K add(T added){
		if(added == null) {
			return null;
		}
		Session session = getSession();
		Transaction t = session.beginTransaction();
		t.begin();
		try {
			K k = (K) session.save(added);
			t.commit();
			return k;
		}catch(HibernateException e) {
			t.rollback();
		}
		return null;
	}
	
	/**
	 * Updates the entity instance passed as parameter in 
	 * the associated persistence store
	 * 
	 * @param updated the entity to be updated
	 * 
	 * @return true if the entity has been updated in the
	 * associated persistence store - false otherwise
	 */
	public boolean update(T updated){
		if(updated == null) {
			return false;
		}
		Session session = getSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(updated);
			t.commit();
			return true;
		}catch(HibernateException e) {
			t.rollback();
		}
		return false;
	}

	/**
	 * Removes the entity instance passed as parameter from
	 * the associated persistence store
	 * 
	 * @param removed the entity to be removed
	 * 
	 * @return true if the entity has been removed from the
	 * associated persistence store - false otherwise
	 */
	public boolean remove(T removed){
		if(removed == null) {
			return false;
		}
		Session session = getSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(removed);
			t.commit();
			return true;
		}catch(HibernateException e) {
			t.rollback();
		}
		return false;
	}
}
