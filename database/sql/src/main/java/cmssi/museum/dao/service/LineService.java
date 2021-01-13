/*
 * MIT License
 *
 * Copyright (c) 2019 - 2020  Christophe Munilla
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cmssi.museum.dao.service;

import java.util.ArrayList;
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

	/**
	 * 
	 * @param idSheet
	 * @param idField
	 * @param value
	 */
	public void updateLine(Integer idSheet, Integer idField, String value){
		Line line = getLinesMap(idSheet).get(idField);
		if(line==null)
			return;
		line.setLine(value);	
		super.update(line);
	}
}
