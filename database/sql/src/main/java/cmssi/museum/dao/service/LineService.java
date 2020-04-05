/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.dao.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;

import org.hibernate.Query;

import cmssi.museum.dao.entity.Line;
import cmssi.museum.dao.entity.Sheet;

/**
 * An extended CRUDService dedicated to {@link Line} entities
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Stateless
public class LineService extends CRUDService<Line> {
	
	/**
	 * Constructor
	 */
	public LineService(){
		super(Line.class);
	}

	/**
	 * Returns the List of {@link Line}s attached to the {@link Sheet}
	 * whose Integer identifier is passed as parameter
	 * 
	 * @param idSheet the Integer identifier of the {@link Sheet}
	 * 
	 * @return the List of {@link Line}s for the specified {@link Sheet}
	 */
	public List<Line> getLines(Integer idSheet){
		Query query = getSession().getNamedQuery("SheetLines");
		query.setParameter("idSheetFk", idSheet);
		return (List<Line>) query.list();
	}

	/**
	 * Returns a Map where the key field is the Integer identifier of the 
	 * {@link Field} whose the {@link Line} value field of the Map is the 
	 * content of
	 * 
	 * @param idSheet the Integer identifier of the {@link Sheet}
	 * 
	 * @return the Map of {@link Line}s mapped to their related {@link Field} 
	 * identifier for the specified {@link Sheet}
	 */
	public Map<Integer,Line> getLinesMap(Integer idSheet){
		return getLines(idSheet).stream().collect(Collectors.toMap(Line::getIdField, l -> l));
	}
}
