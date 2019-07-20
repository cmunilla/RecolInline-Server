package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;

import cmssi.museum.dao.entity.Domain;
import cmssi.museum.dao.entity.Museum;
import cmssi.museum.dao.entity.Sheet;

/**
 * the SheetService is an extended CRUDService dedicated to {@link Sheet} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class SheetService extends CRUDService<Sheet> {
	
	/**
	 * Constructor
	 */
	public SheetService(){
		super(Sheet.class);
	}
	
	/**
	 * Returns the List of {@link Sheet}s attached to the {@link Museum}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idMuseum the Integer identifier of the {@link Museum}
	 * 
	 * @return the List of {@link Sheet}s for the specified {@link Museum}
	 */
	public List<Sheet> getSheets(Integer idMuseum, int from, int to)
	{
		var query = getSession().getNamedQuery("SheetsFromMuseum");
		query.setParameter("idMuseumFk", idMuseum);
		query.setParameter("fstIndex", from);
		query.setParameter("lstIndex", to);
		return (List<Sheet>) query.list();
	}

	/**
	 * Returns the List of {@link Sheet}s attached to the {@link Museum}
	 * and {@link Domain} whose Integer identifiers are respectively passed 
	 * as parameters
	 * 
	 * @param idMuseum the Integer identifier of the {@link Museum}
	 * @param idDomain the Integer identifier of the {@link Domain}
	 * 
	 * @return the List of {@link Sheet}s for the specified {@link Museum} and
	 * {@link Domainb}
	 */
	public List<Sheet> getSheetsFromDomain(Integer idMuseum, Integer idDomain, int from, int to)
	{
		var query = getSession().getNamedQuery("SheetsFromDomainFromMuseum");
		query.setParameter("idMuseumFk", idMuseum);
		query.setParameter("idDomainFk", idDomain);
		query.setParameter("fstIndex", from);
		query.setParameter("lstIndex", to);
		return (List<Sheet>) query.list();
	}

	/**
	 * Returns the number of {@link Sheet}s attached to the {@link Museum}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idMuseum the Integer identifier of the {@link Museum}
	 * 
	 * @return the number of {@link Sheet}s for the specified {@link Museum}
	 */
	public int getSheetsCount(Integer idMuseum)
	{
		var query = getSession().getNamedQuery("SheetsCountFromMuseum");
		query.setParameter("idMuseumFk", idMuseum);
		return (Integer) query.uniqueResult();
	}
	

	/**
	 * Returns the number of {@link Sheet}s attached to the {@link Museum} and
	 * {@link Domain} whose Integer identifiers are respectively passed as 
	 * parameters
	 * 
	 * @param idMuseum the Integer identifier of the {@link Museum}
	 * @param idDomain the Integer identifier of the {@link Domain}
	 * 
	 * @return the number of {@link Sheet}s for the specified {@link Museum} and
	 * {@link Domain}
	 */
	public int getSheetsCountFromDomain(Integer idMuseum, Integer idDomain)
	{
		var query = getSession().getNamedQuery("SheetsCountFromDomainFromMuseum");
		query.setParameter("idMuseumFk", idMuseum);
		query.setParameter("idDomainFk", idDomain);
		return (Integer) query.uniqueResult();
	}
}
