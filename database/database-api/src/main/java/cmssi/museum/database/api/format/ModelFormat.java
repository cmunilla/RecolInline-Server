/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.database.api.format;

import java.util.concurrent.atomic.AtomicInteger;

import cmssi.museum.controler.api.format.JsonStringFormat;
import cmssi.museum.controler.api.ArtifactModel;

/**
 * Extended {@link JsonStringFormat} dedicated to model data structure format 
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public class ModelFormat extends JsonStringFormat<FieldFormat> 
implements ArtifactModel<FieldFormat> {
		
	/**
	 * Construtor 
	 * 
	 * @param name the String name of the ModelFormat to be instantiated
	 */
	public ModelFormat(String name){
		super(name);
	}

	@Override
	public String format() {
		var chars = new char[] { ' ' , ',' };
		var counter = new AtomicInteger(0);
		var builder = new StringBuilder();
		builder.append('"').append(this.getName()).append("\" : {");
		super.elements.forEach(e -> {
			String eformat = e.format();
			if(eformat != null) {
				builder.append(chars[0]);
				builder.append(eformat);
				chars[0] = chars[1];
				counter.incrementAndGet();
			}
		});
		if(counter.get() == 0) {
			return null;
		}
		builder.append(" }");
		return builder.toString();
	}
}
