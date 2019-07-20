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
 * <T> the handled Entity Type 
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
	 */
	public ReadService(Class<T> entityType){
		this.entityType = entityType; 
		
		Field[] fields = this.entityType.getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
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
	
	protected Class<T> getEntityType(){
		return this.entityType;
	}


	protected String getIdentityFieldName() {
		return this.identityFieldName;
	}
	
	public List<T> getAll() {
		return getSession().createCriteria(getEntityType()).list();
	}
	
	public <K extends Serializable>  T get(K k){
		return (T) getSession().createCriteria(getEntityType()
			).add(Restrictions.eq(getIdentityFieldName(), k)
			).uniqueResult();
	}
}
