package cmssi.museum.dao.entity;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
 
/**
 * A Sheet is an instance of a Domain. It is so dedicated to 
 * the description of an artefact
 * 
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "SheetsFromMuseum",
	query = "CALL GetSheetsFromMuseum(:idMuseumFk,:fstIndex,:lstIndex)",
	resultClass = Sheet.class
	),
	@NamedNativeQuery(
	name = "SheetsFromDomainFromMuseum",
	query = "CALL GetSheetsFromDomainFromMuseum(:idMuseumFk,:idDomainFk,:fstIndex,:lstIndex)",
	resultClass = Sheet.class
	),
	@NamedNativeQuery(
	name = "SheetsCountFromMuseum",
	query = "CALL GetSheetsCountFromMuseum(:idMuseumFk)",
	resultClass = int.class
	),
	@NamedNativeQuery(
	name = "SheetsCountFromDomainFromMuseum",
	query = "CALL GetSheetsCountFromMuseum(:idMuseumFk,:idDomainFk)",
	resultClass = int.class
	)
})
@Entity
@Table(name = "Sheet")
public class Sheet implements Serializable {
	
	/**
	 * Generated long ID
	 */
	private static final long serialVersionUID = -9120946162721048799L;

	/**
     * the Sheet's Integer identifier
     */
    @Id
    @GeneratedValue
    @Column
    private Integer idSheet; 
    
    /**
     * the Integer identifier of the Domain this sheet is an instance of
     */
    @Column
    private Integer idDomain; 
    
    /**
     * the Integer identifier of the Museum this sheet belongs to
     */
    @Column
    private Integer idMuseum; 
    
    /**
     * the Model's String name
     */
    @Column
    private String label;
     
    /**
     * Constructor
     */
    public Sheet() {}
     
    /**
     * Constructor
     * 
     * @param idSheet the Integer identifier of the Sheet to be instantiated
     * @param idDomain the Integer identifier of the Domain the Sheet
     * to be instantiated is an instance of
     * @param idMuseum the Integer identifier of the Museum the artifact 
     * described by the Sheet to be instantiated belongs to
     * @param label the String label of the Sheet to be instantiated
     */
    public Sheet(Integer idSheet, Integer idDomain,Integer idMuseum, String label)
    {
        this.idSheet = idSheet;
        this.idDomain = idDomain;
        this.idMuseum = idMuseum;
        this.label = label;
    }
    
	/**
	 * @return the idSheet
	 */
	public Integer getIdSheet() {
		return idSheet;
	}

	/**
	 * @param idSheet the idSheet to set
	 */
	public void setIdSheet(Integer idSheet) {
		this.idSheet = idSheet;
	}
	
	/**
	 * @return the idDomain
	 */
	public Integer getIdDomain(){
		return idDomain;
	}

	/**
	 * @param idDomain the idDomain to set
	 */
	public void setIdDomain(Integer idDomain) {
		this.idDomain = idDomain;
	}

	/**
	 * @return the idDomain
	 */
	public Integer getIdMuseum(){
		return idMuseum;
	}

	/**
	 * @param idMuseum the idMiuseum to set
	 */
	public void setIdMuseum(Integer idMuseum) {
		this.idMuseum = idMuseum;
	}
    
    /**
     * Defines the String name of this Sheet
     * 
     * @param label the String name to be set
     */
    public void setLabel(String label){
    	this.label = label;
    }
    
    /**
     * Returns the String name of this Sheet
     * 
     * @return this Sheet's String name
     */
    public String getLabel() {
    	return this.label;
    }
}