package cmssi.museum.dao.entity;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * In the data model a CategoryEntry wraps a 
 * String value associated to a Category
 *   
 * @author cmunilla@cmssi.fr
 * @version 0.2
 */
@Entity
@Table(name = "CategoryEntry")
public class CategoryEntry implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6839048157409306750L;

	/**
     * the CategoryEntry's Integer identifier
     */
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idCategoryEntry; 
    
	/**
     * the Integer identifier of the 
     * Category to which belongs this CategoryEntry
     */
    @Column
    private Integer idCategory; 
    
    /**
     * the CategoryEntry's String value
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public CategoryEntry() {}
     
    /**
     * Constructor
     * 
     * @param idCategoryEntry the Integer identifier of the 
     * CategoryEntry to be instantiated
     * @param idCategory the Integer identifier of the Category 
     * to which belongs the CategoryEntry to be instantiated
     * @param label the String value of the CategoryEntry to be
     * instantiated
     */
    public CategoryEntry(Integer idCategoryEntry, Integer idCategory, String label)
    {
        this.idCategoryEntry = idCategoryEntry;
        this.idCategory = idCategory;
        this.label = label;
    }

    /**
	 * Returns the Integer identifier of this CategoryEntry
	 * 
	 * @return this CategoryEntry's the Integer identifier 
	 */
	public Integer getIdCategoryEntry() 
	{
		return idCategory;
	}

	/**
	 * Defines the Integer identifier of this CategoryEntry
	 
	 * @param idCategory this CategoryEntry's the Integer 
	 * identifier 
	 */
	public void setIdCategoryEntry(Integer idCategoryEntry) 
	{
		this.idCategoryEntry = idCategoryEntry;
	}
	
    /**
	 * Returns the Integer identifier of the Category to 
	 * which this CategoryEntry belongs
	 * 
	 * @return the Category's the Integer identifier for
	 * this CategoryEntry
	 */
	public Integer getIdCategory() 
	{
		return idCategory;
	}

	/**
	 * Defines the Integer identifier of the Category to which
	 * this CategoryEntry belongs
	 
	 * @param idCategory the Category's the Integer identifier 
	 * for this CategoryEntry
	 */
	public void setIdCategory(Integer idCategory) 
	{
		this.idCategory = idCategory;
	}

    /**
     * Defines the String value of this CategoryEntry
     * 
     * @param label the String value to be set
     */
    public void setLabel(String label)
    {
    	this.label = label;
    }
    
    /**
     * Returns the String value of this CategoryEntry
     * 
     * @return this CategoryEntry's String value
     */
    public String getLabel()
    {
    	return this.label;
    }
}