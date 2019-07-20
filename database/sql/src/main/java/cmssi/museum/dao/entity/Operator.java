package cmssi.museum.dao.entity;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * In the data model an Operator is used to defined
 * a Constraint applying on the value of a Field: 
 * 
 *   - EQUAL: the value (Comparable) is equals to the Constraint's operand 
 *   	(Comparable)
 *   - GREATER: the value (Comparable) is greater than the Constraint's operand
 *   	(Comparable)
 *   - GREATER OR EQUAL: the value (Comparable) is equal or greater than the 
 *   	Constraint's operand (Comparable)
 *   - LESSER: the value (Comparable) is lesser than the Constraint's operand 
 *   	(Comparable)
 *   - LESSER OR EQUAL: the value (Comparable) is lesser or equal to the 
 *   	Constraint's operand (Comparable)
 *   - IN: the value (Comparable) exists in the Constraint's  list operand
 *   	 (Comparable)
 *   - ALL: all elements (Comparable) of the list value exist in the Constraint's 
 *   	list operand
 *   - REGEXP: the value (Text) matches the the Constraint's  regular 
 *   	expression operand
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@Entity
@Table(name = "Operator")
public class Operator implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6476792129215060697L;

	/**
     * the Operator's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idOperator; 
    
    /**
     * the Operator's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Operator() {}
     
    /**
     * Constructor
     * 
     * @param idOperator the Integer identifier of the Operator 
     * to be instantiated
     * @param label the String name of the Operator to be
     * instantiated
     */
    public Operator(Integer idOperator, String label)
    {
        this.idOperator = idOperator;
        this.label = label;
    }

    /**
	 * Returns the Integer identifier of this Operator
	 * 
	 * @return this Operator's the Integer identifier 
	 */
	public Integer getIdOperator() 
	{
		return idOperator;
	}

	/**
	 * Defines the Integer identifier of this Operator
	 
	 * @param idOperator this Operator's the Integer 
	 * identifier 
	 */
	public void setIdOperator(Integer idOperator) 
	{
		this.idOperator = idOperator;
	}
    
    /**
     * Defines the String name of this Operator
     * 
     * @param label the String name to be set
     */
    public void setLabel(String label)
    {
    	this.label = label;
    }
    
    /**
     * Returns the String name of this Operator
     * 
     * @return this Operator's String name
     */
    public String getLabel()
    {
    	return this.label;
    }
}