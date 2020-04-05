/*
 * MIT License
 *
 * Copyright (c) 2019 Christophe Munilla
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

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.criterion.Restrictions;

/**
 * the ReadService allows to read the set of persistent instances 
 * of a specific Entity type (i.e. @Entity annotated)
 * 
 * @param T the handled Entity Type 
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@SuppressWarnings("unchecked")
abstract class ReadService<T> extends PersistenceService
{	
	private Class<T> entityType;
	private String identityFieldName;
	
	/**
	 * Constructor
	 * 
	 * @param entityType the handled &lt;T&gt; entity Type
	 * of the ReadService to be instantiated
	 */
	public ReadService(Class<T> entityType){
		this.entityType = entityType; 
		
		Field[] fields = this.entityType.getDeclaredFields();
		for(Field field : fields) {
			//field.setAccessible(true);
			Id idAnnotation = field.getAnnotation(Id.class);
			if(idAnnotation != null) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column!= null?column.name():null;
				this.identityFieldName = columnName==null?field.getName():columnName;
				break;
			}
		}
		if(this.identityFieldName == null) {
			throw new NullPointerException("No @Id annotated field found");
		}
	}
	
	/**
	 * Returns the handled &lt;T&gt; entity Type
	 * 
	 * @return the handled &lt;T&gt; entity Type
	 */
	protected Class<T> getEntityType(){
		return this.entityType;
	}
	
	/**
	 * Returns the String name of the field holding the
	 * identity of the &lt;T&gt; handled type
	 * 
	 * @return the handled &lt;T&gt; type identity field name
	 */
	protected String getIdentityFieldName() {
		return this.identityFieldName;
	}
	
	/**
	 * Returns the List of all registered &lt;T&gt; typed entities
	 * 
	 * @return the registered &lt;T&gt; typed entities List
	 */
	public List<T> getAll() {
		return getSession().createCriteria(getEntityType()).list();
	}
	
	/**
	 * Returns the &lt;T&gt; typed entity whose identifier is passed
	 * as parameter 
	 * 
	 * @param <K> the {@link Serializable} identifier type
	 * 
	 * @param k the &lt;K&gt; typed identifier 
	 * 
	 * @return the &lt;T&gt; typed entity for the specified &lt;K&gt; 
	 * typed identifier
	 */
	public <K extends Serializable>  T get(K k){
		return (T) getSession().createCriteria(getEntityType()
			).add(Restrictions.eq(getIdentityFieldName(), k)
			).uniqueResult();
	}
}
