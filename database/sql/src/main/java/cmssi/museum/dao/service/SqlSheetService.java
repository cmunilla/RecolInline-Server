/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, Micro Entreprise FR - All Rights Reserved
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.dao.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.util.List;

import javax.ejb.Stateless;

import cmssi.museum.controler.api.format.sign.ArtifactSheetSigner;
import cmssi.museum.dao.entity.Domain;
import cmssi.museum.dao.entity.Field;
import cmssi.museum.dao.entity.FieldSkeleton;
import cmssi.museum.dao.entity.Museum;
import cmssi.museum.dao.entity.Sheet;
import cmssi.museum.dao.entity.User;
import cmssi.museum.database.api.format.FieldFormat;
import cmssi.museum.database.api.format.ModelFormat;
import cmssi.museum.database.api.format.SheetFormat;
import cmssi.museum.database.api.format.StringFieldFormat;
import cmssi.museum.database.api.service.SheetService;
import cmssi.museum.database.api.service.UnauthorizedSheetAccessException;

/**
 * the SheetService is an extended CRUDService dedicated to {@link Sheet} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class SqlSheetService extends CRUDService<Sheet> implements SheetService {
	
	private LineService lineService;
	private ArtifactSheetSigner<FieldFormat,ModelFormat,SheetFormat> signer;
	
	/**
	 * Constructor
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	public SqlSheetService() throws GeneralSecurityException, IOException{
		super(Sheet.class);
		this.lineService = new LineService();	
		this.signer = new ArtifactSheetSigner<FieldFormat,ModelFormat,SheetFormat>();
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
	 * Returns the List of {@link FieldSkeleton}s based on the {@link Field}s belonging 
	 * to the {@link Domain} whose Integer identifier is passed as parameter, for the {@link 
	 * Museum} and {@link User} whose Integer identifiers are also passed as parameter
	 * 
	 * @param idMuseum the Integer identifier of the {@link Museum} to which belongs the
	 * {@link Sheet} to retrieve the {@link FieldSkeleton}s of	 
	 * @param idDomain the Integer identifier of the {@link Domain} to which belongs the
	 * {@link Sheet} to retrieve the {@link FieldSkeleton}s of	 
	 * @param idUser the Integer identifier of the {@link User} asking for the {@link 
	 * FieldSkeleton}s list
	 * 
	 * @return the List of {@link FieldSkeleton}s based on the {@link Field}s attached to the
	 * specified  {@link Domain}, for the specified {@link Museum} and {@link User}
	 */
	public List<FieldSkeleton> getSheetFields(Integer idMuseum, Integer idDomain, Integer idUser) {
		var query = getSession().getNamedQuery("SheetFields");
		query.setParameter("idMuseumFk", idMuseum);
		query.setParameter("idDomainFk", idDomain);
		query.setParameter("idUserFk", idUser);
		return (List<FieldSkeleton>) query.list();
	}
	
	@Override
	public SheetFormat createSheet(Integer idMuseum, Integer idDomain, Integer idUser) throws UnauthorizedSheetAccessException {

		var list = getSheetFields(idMuseum, idDomain, idUser);
		var sheet = new SheetFormat();	
		list.forEach(ln -> {	
			ModelFormat mf = new ModelFormat(ln.getLabelModel());
			FieldFormat ff = null;
			String labelField = ln.getLabelField();
			if(ln.isText()) {
			  ff = new StringFieldFormat(labelField);
			} else {
			  ff = new FieldFormat(labelField);
			}
			ff.setConstraints(ln.getConstraints());
			ff.setLabelType(ln.getLabelType());
			ff.setVisibility(ln.getLabelVisibility());			
			mf.append(ff);
			sheet.append(mf);
		});
		try {
			this.signer.sign(sheet);
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	@Override
	public SheetFormat deleteSheet(Integer idSheet, Integer idUser) throws UnauthorizedSheetAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SheetFormat getSheet(Integer idSheet, Integer idUser) throws UnauthorizedSheetAccessException {
		Sheet sh = super.get(idSheet);
		if(sh == null) {
			return null;
		}
		Integer idMuseum = sh.getIdMuseum();
		Integer idDomain = sh.getIdDomain();
		
		final var lines = this.lineService.getLinesMap(idSheet);		
		var list = getSheetFields(idMuseum, idDomain, idUser);
		
		var sheet = new SheetFormat();	
		list.forEach(ln -> {	
			ModelFormat mf = new ModelFormat(ln.getLabelModel());
			FieldFormat ff = null;
			String labelField = ln.getLabelField();
			if(ln.isText()) {
			  ff = new StringFieldFormat(labelField);
			} else {
			  ff = new FieldFormat(labelField);
			}
			var l = lines.get(ln.getIdField()); 
			var line = l==null?null:l.getLine();
			
			ff.append(line);
			ff.setConstraints(ln.getConstraints());
			ff.setLabelType(ln.getLabelType());
			ff.setVisibility(ln.getLabelVisibility());
			
			mf.append(ff);
			sheet.append(mf);
			if(line!=null && "identity".equals(mf.getName()) && "name".equals(ff.getName())){
				sheet.setName(line);
			}			
		});	
		try {
			this.signer.sign(sheet);
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	@Override
	public SheetFormat updateSheet(String sheet, Integer idUser) throws UnauthorizedSheetAccessException {
		return null;
	}

}
