/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
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
