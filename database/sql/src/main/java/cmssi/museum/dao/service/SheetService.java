package cmssi.museum.dao.service;

import java.util.List;

import javax.ejb.Stateless;

import cmssi.museum.dao.entity.Domain;
import cmssi.museum.dao.entity.LineInstance;
import cmssi.museum.dao.entity.Museum;
import cmssi.museum.dao.entity.Sheet;
import cmssi.museum.dao.service.format.FieldFormat;
import cmssi.museum.dao.service.format.ModelFormat;
import cmssi.museum.dao.service.format.SheetFormat;
import cmssi.museum.dao.service.format.StringFieldFormat;

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
	public List<Sheet> getSheets(Integer idMuseum, int from, int to){
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
	public List<Sheet> getSheetsFromDomain(Integer idMuseum, Integer idDomain, int from, int to){
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
	public int getSheetsCount(Integer idMuseum)	{
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
	public int getSheetsCountFromDomain(Integer idMuseum, Integer idDomain)	{
		var query = getSession().getNamedQuery("SheetsCountFromDomainFromMuseum");
		query.setParameter("idMuseumFk", idMuseum);
		query.setParameter("idDomainFk", idDomain);
		return (Integer) query.uniqueResult();
	}
	
	/**
	 * Returns the List of {@link LineInstance}s attached to the {@link Sheet}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idSheet the Integer identifier of the {@link Sheet}	 
	 * @param idUser the Integer identifier of the {@link User} asking for 
	 * the {@link LineInstance}s list
	 * 
	 * @return the List of {@link LineInstance}s for the specified {@link Sheet}
	 * and {@link User}
	 */
	public List<LineInstance> getSheetLines(Integer idSheet, Integer idUser){
		var query = getSession().getNamedQuery("sheetLines");
		query.setParameter("idSheetFk", idSheet);
		query.setParameter("idUserFk", idUser);
		return (List<LineInstance>) query.list();
	}
	

	/**
	 * Returns the JSON String formated List of {@link LineInstance}s attached 
	 * to the {@link Sheet} whose Integer identifier is passed as parameter
	 * 
	 * @param idSheet the Integer identifier of the {@link Sheet}	 
	 * @param idUser the Integer identifier of the {@link User} asking for 
	 * the sheet
	 *
	 * @return the List of {@link LineInstance}s for the specified {@link Sheet}
	 */
	public String getSheetInstance(Integer idSheet, Integer idUser){
		var list = getSheetLines(idSheet, idUser);
		var sheet = new SheetFormat();	
		list.forEach(ln -> {
			ModelFormat mf = new ModelFormat(ln.getLabelModel());
			FieldFormat ff = null;
			if(ln.isText()) {
			  ff = new StringFieldFormat(ln.getLabelField());
			} else {
			  ff = new FieldFormat(ln.getLabelField());
			}
			ff.append(ln.getLine());
			mf.append(ff);
			sheet.append(mf);
			if(ln.getLine()!=null 
				&& "identity".equals(mf.getName()) 
				&& "name".equals(ff.getName())){
				sheet.setName(ln.getLine());
			}			
		});
		return sheet.format();
	}

}
