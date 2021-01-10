/*
 * MIT License
 *
 * Copyright (c) 2019 - 2020  Christophe Munilla
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
package cmssi.museum.database.api.format.builder;

import cmssi.museum.controler.api.format.JsonStringFormat;
import cmssi.museum.database.api.format.FieldFormat;
import cmssi.museum.database.api.format.ModelFormat;

import java.util.List;

import cmssi.lyson.annotation.LysonMapping;
import cmssi.lyson.handler.mapping.MappingConfiguration;

/**
 * Extended {@link JsonStringFormat} dedicated to model data structure format
 *  
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@LysonMapping(implicit=true)
public class ModelFormatBuilder {

	@LysonMapping(mapping=MappingConfiguration.IDENTITY_MAPPING)
	protected String name;

	protected List<FieldFormatBuilder> fields;

	/**
	 * Constructor
	 */
	public ModelFormatBuilder() {}

	/**
	 * Defines the String name of the related {@link ModelFormat} described by this ModelFormatBuilder
	 * 
	 * @param name the String name of the described {@link ModelFormat}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Defines the {@link FieldFormatBuilder}s List allowing to build the {@link FieldFormat}s of the related 
	 * {@link ModelFormat} described by this ModelFormatBuilder
	 * 
	 * @param name the {@link FieldFormatBuilder}s List allowing to build the {@link FieldFormat}s of the 
	 * described {@link ModelFormat}
	 */
	public void setFields(List<FieldFormatBuilder> fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns the {@link ModelFormat} described by this ModelFormatBuilder 
	 * 
	 * @return the {@link ModelFormat} from this ModelFormatBuilder
	 */
	public ModelFormat build() {
		ModelFormat modelFormat  = new ModelFormat(this.name);
		fields.stream().forEach(f->modelFormat.append(f.build()));
		return modelFormat;
	}
}
