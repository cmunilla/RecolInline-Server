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