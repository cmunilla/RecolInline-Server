package cmssi.museum.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A Line Entity Wrapper 
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
public class LineInstance implements Serializable
{
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = -7350067258163911929L;

    /**
     * the Integer identifier of the Sheet to which the wrapped Line belongs to
     */
	@Id
    private Integer idSheet; 
    
    /**
     * the Integer identifier of the Model to which the Field whose the wrapped Line is
     * the content of belongs to
     */
    private Integer idModel; 

    /**
     * the Integer identifier of the Field whose the wrapped Line is the content of
     */
    @Id
    private Integer idField; 

    /**
     * the Integer identifier of the Type of the Field whose the wrapped 
     * Line is the content of
     */
    private Integer idType; 
    
    /**
     * the Integer identifier of the Visibility of the Field whose the wrapped 
     * Line is the content of
     */
    private Integer idVisibility; 

    /**
     * the String name of the Model to which the Field whose the wrapped Line is
     * the content of belongs to
     */
    private String labelModel;

    /**
     * the String name of the Field whose the wrapped Line is the content of
     */
    private String labelField; 
    
    /**
     * the String name of the Type of the Field whose the wrapped Line is the content of
     */
    private String labelType; 

    /**
     * the String name of the Visibility applying on the Field whose the wrapped Line 
     * is the content of
     */
    private String labelVisibility; 
    
    /**
     * the JSON formated String of the constraints applying on the Field whose the wrapped 
     * Line is the content of
     */
    private String constraints; 

    /**
     * defines whether the line content of this LineInstance is a chars sequence 
     * (needed when converting into JSON data structure)
     */
    private Boolean isText;
    
    /**
     * the String content of the wrapped Line
     */
    private String line;
    
    /**
     * Constructor
     */
    public LineInstance() {}
     
    /**
     * Constructor
     * 
     * @param idSheet the Integer identifier of the Sheet to which the Line wrapped by the LineInstance 
     * to be instantiated belongs to
     * @param idModel the Integer identifier of the Model to which belong the Field whose the Line wrapped 
     * by the LineInstance to be instantiated is the content of
     * @param idField the Integer identifier of the Field whose the Line wrapped by the LineInstance to 
     * be instantiated is the content of
     * @param idType the Integer identifier of the Type of the Field whose the Line wrapped by the LineInstance 
     * to be instantiated is the content of
     * @param idVisibility the Integer identifier of the Visibility applying on the Field whose the Line 
     * wrapped by the LineInstance to be instantiated is the content of
     * @param labelModel the String name of the Model to which belong the Field whose the Line wrapped by 
     * the LineInstance to be instantiated is the content
     * @param labelField the String name of the Field whose the Line wrapped by the LineInstance to be 
     * instantiated is the content of
     * @param labelType the String name of the Type of the Field whose the Line wrapped by the LineInstance 
     * to be instantiated is the content of
     * @param labelVisibility the String name of the Visibility applying on the Field whose the Line 
     * wrapped by the LineInstance to be instantiated is the content of
     * @param constraints the JSON formated String defining the constraints applying on the Field 
     * whose the Line wrapped by the LineInstance to be instantiated is the content of
     * @param isText defines whether the Line wrapped by the LineInstance to be instantiated is a 
     * chars sequence
     * @param line the String content of the Line wrapped by the LineInstance to be instantiated 
     */
    public LineInstance(Integer idSheet, Integer idModel, Integer idField, 
    	Integer idType, Integer idVisibility, String labelModel, String labelField, 
    	String labelType, String labelVisibility, String constraints, Boolean isText,
    	String line){	
        this.idSheet = idSheet;
        this.idModel = idModel;
        this.idField = idField;
        this.idType = idType;
        this.idVisibility = idVisibility;
        this.labelModel = labelModel;
        this.labelField = labelField;
        this.labelType = labelType;
        this.labelVisibility = labelVisibility;
        this.constraints = constraints;
        this.isText = isText;
        this.line = line;        
    }

	/**
	 * Returns true if the Line wrapped by this LineInstance is a chars sequence ; 
	 * Otherwise returns false
	 * 
	 * @return 
	 * <ul>
	 *     <li>true if the held value is a chars sequence</li>
	 *     <li>false otherwise</li>
	 * </ul>
	 */
	public boolean isText() {
		return this.isText;
	}

	/**
	 * Defines whether the Line wrapped by this LineInstance is a chars sequence ;
	 *  
	 * @param isText 
	 * <ul>
	 *     <li>true if the held value is a chars sequence</li>
	 *     <li>false otherwise</li>
	 * </ul>
	 */
	public void isText(Boolean isText) {
		this.isText = isText;
	}
	
	/**
	 * Returns the Integer identifier of the {@link Sheet} to which the {@link Line} wrapped 
	 * by this LineInstance belongs to
     * 
	 * @return the Integer identifier of the {@link Sheet} to which the {@link Line} belongs 
	 * to
	 */
	public Integer getIdSheet() {
		return idSheet;
	}

	/**
	 * Defines the Integer identifier of the {@link Sheet} to which the {@link Line} wrapped 
	 * by this LineInstance belongs to
     * 
	 * @param idSheet the Integer identifier of the {@link Sheet} to which the {@link Line} 
	 * belongs to
	 */
	public void setIdSheet(Integer idSheet) {
		this.idSheet = idSheet;
	}

	/**
	 * Returns the Integer identifier of the {@link Model} to which belong the {@link Field} whose 
	 * the {@link Line} wrapped by this LineInstance is the content
     * 
	 * @return the Integer identifier of the {@link Model} to which belong the {@link Field} whose 
	 * the {@link Line} is the content of
	 */
	public Integer getIdModel() {
		return idModel;
	}

	/**
	 * Defines the Integer identifier of the {@link Model} to which belong the {@link Field} whose 
	 * the {@link Line} wrapped by this LineInstance is the content
     * 
	 * @param idModel the Integer identifier of the {@link Model} to which belong the {@link Field} whose 
	 * the {@link Line} is the content of
	 */
	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}

	/**
	 * Returns the Integer identifier of the {@link Field} whose the {@link Line} wrapped by this 
	 * LineInstance is the content of
     * 
	 * @return  the Integer identifier of the {@link Field} whose the {@link Line} is the content of
	 */
	public Integer getIdField() {
		return idField;
	}

	/**
	 * Defines the Integer identifier of the {@link Field} whose the {@link Line} wrapped by this 
	 * LineInstance is the content of
     * 
	 * @param idField the Integer identifier of the {@link Field} whose the {@link Line} is the 
	 * content of
	 */
	public void setIdField(Integer idField) {
		this.idField = idField;
	}

	/**
	 * Returns the String name of the {@link Model} to which belong the {@link Field} whose the 
	 * {@link Line} wrapped by this LineInstance is the content of
     * 
	 * @return the String name of the {@link Model} to which belong the {@link Field} whose the 
	 * {@link Line} is the content of
	 */
	public String getLabelModel() {
		return labelModel;
	}

	/**
	 * Defines the String name of the {@link Model} to which belong the {@link Field} whose the 
	 * {@link Line} wrapped by this LineInstance is the content of
     * 
	 * @param labelModel the String name of the {@link Model} to which belong the {@link Field} whose the 
	 * {@link Line} is the content of
	 */
	public void setLabelModel(String labelModel) {
		this.labelModel = labelModel;
	}

	/**
	 * Returns the String name of the {@link Field} whose the {@link Line} wrapped by this 
	 * LineInstance is the content of
     * 
	 * @return the String name of the {@link Field} whose the {@link Line} is the content of
	 */
	public String getLabelField() {
		return labelField;
	}

	/**
	 * Defines the String name of the {@link Field} whose the {@link Line} wrapped by this 
	 * LineInstance is the content of
     * 
	 * @param labelField the String name of the {@link Field} whose the {@link Line} is the 
	 * content of
	 */
	public void setLabelField(String labelField) {
		this.labelField = labelField;
	}

	/**
	 * Returns the String content of the {@link Line} wrapped by this LineInstance
	 * 
	 * @return  the String content of the {@link Line} 
	 */
	public String getLine() {
		return line;
	}

	/**
	 * Defines the String content of the {@link Line} wrapped by this LineInstance
	 * 
	 * @param line the String content of the {@link Line} 
	 */
	public void setLine(String line) {
		this.line = line;
	}
	
	/**
     * Returns the Integer identifier of the Type of the Field whose the Line wrapped 
     * by this LineInstance is the content of
     * 
     * @return the Integer identifier of the Type of the Field
     */
	public Integer getIdType() {
		return idType;
	}

	/**
     * Defines the Integer identifier of the Type of the Field whose the Line 
     * wrapped by this LineInstance is the content of
     * 
     * @param idType the Integer identifier of the Type of the Field
     */
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	
	/**
     * Returns the Integer identifier of the Visibility applying on the Field whose 
     * the Line wrapped by this LineInstance is the content of
     * 
     * @return the Integer identifier of the Visibility applying on the Field
     */	
	public Integer getIdVisibility() {
		return idVisibility;
	}

	/**
     * Defines the Integer identifier of the Visibility applying on the Field whose 
     * the Line wrapped by this LineInstance is the content of
     * 
     * @param idVisibility the Integer identifier of the Visibility applying on 
     * the Field
     */	
	public void setIdVisibility(Integer idVisibility) {
		this.idVisibility = idVisibility;
	}

	/**
     * Returns the String name of the Type of the Field whose the Line 
     * wrapped by this LineInstance is the content of
     * 
     * @return the String name of the Type of the Field
     */
	public String getLabelType() {
		return labelType;
	}

	/**
     * Defines the String name of the Type of the Field whose the Line 
     * wrapped by this LineInstance is the content of
     * 
     * @param labelType the String name of the Type of the Field
     */
	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
	
	/**
     * Returns the String name of the Visibility applying on the Field whose 
     * the Line wrapped by this LineInstance is the content of
     * 
     * @return the String name of the Visibility applying on the Field
     */
	public String getLabelVisibility() {
		return this.labelVisibility;
	}

	/**
     * Defines the String name of the Visibility applying on the Field whose 
     * the Line wrapped by this LineInstance is the content of
     * 
     * @param labelVisibility the String name of the Visibility applying on 
     * the Field
     */
	public void setLabelVisibility(String labelVisibility) {
		this.labelVisibility = labelVisibility;
	}
	
	/**
     * Returns the JSON formated String defining the constraints applying on the Field 
     * whose the Line wrapped by this LineInstance is the content of
     * 
     * @return the JSON formated String defining the constraints applying on the Field 
     */
	public String getConstraints() {
		return constraints;
	}

	/**
     * Defines the JSON formated String defining the constraints applying on the Field 
     * whose the Line wrapped by this LineInstance is the content of
     * 
     * @param constraints the JSON formated String defining the constraints applying on 
     * the Field 
     */
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
}
