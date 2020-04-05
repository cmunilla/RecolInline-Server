/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.controler.api;
 
import java.io.Serializable;
 
/**
 * Possible access policies for a domain:
 * 
 *   - ADMINISTRATOR : allowed to create Models
 *   - VALIDATOR: allowed to validate Sheets
 *   - WRITER: allowed to write a Field
 *   - READER: allowed to read a Field
 *   - NONE: no access rights
 *   
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
public enum DomainRole implements Serializable
{
	 ADMINISTRATOR,
	 VALIDATOR,
	 WRITER,
	 READER,
	 NONE;
     
    /**
     * Constructor
     */
    private DomainRole() {}
     
}