/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.controler.api;

import cmssi.museum.controler.api.format.JsonStringFormat;

/**
 * 
 * @param <F>
 * @param <M>
 * 
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
public interface ArtifactSheet<
	F extends JsonStringFormat<?> & ArtifactField, 
	M extends JsonStringFormat<?> & ArtifactModel<F>> {
	
	void setSignature(String signature);

	String getSignature();
	
	void setHash(String hash);
	
	String getHash();
}
