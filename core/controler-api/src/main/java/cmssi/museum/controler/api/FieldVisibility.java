/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.controler.api;
 
import java.io.Serializable;

 
/**
 * Possible field's visibility policy: 
 * 
 * 		-HIDDEN : no access
 * 		-DISABLED : read access
 * 		-ENABLED : read/write access
 * 
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
public enum FieldVisibility implements Serializable
{
	HIDDEN,
	DISABLED,
	ENABLED;
	
    /**
     * Constructor
     */
    private FieldVisibility() {}
     
}