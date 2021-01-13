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
import cmssi.museum.database.api.format.SheetFormat;

import java.util.List;

import cmssi.lyson.annotation.LysonMapping;
import cmssi.lyson.handler.mapping.MappingConfiguration;

/**
 * Extended {@link JsonStringFormat} dedicated to sheet data structure format
 *  
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
@LysonMapping(implicit=true)
public class SheetFormatBuilder {

	@LysonMapping(mapping=MappingConfiguration.IDENTITY_MAPPING)
	protected String name;

	@LysonMapping(mapping="id")
	protected int identifier;
	
	protected List<ModelFormatBuilder> models;

	protected String signature;
	
	protected String hash;
	
	/**
	 * Constructor
	 */
	public SheetFormatBuilder() {
	}

	/**
	 * Defines the String name of the related {@link SheetFormat} described by this SheetFormatBuilder
	 * 
	 * @param name the String name of the described {@link SheetFormat}
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Defines the Integer identifier of the related {@link SheetFormat} described by this SheetFormatBuilder
	 * 
	 * @param identifier the Integer identifier of the described {@link SheetFormat}
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	/**
	 * Defines the String signature of the related {@link SheetFormat} described by this SheetFormatBuilder
	 * 
	 * @param name the String signature of the described {@link SheetFormat}
	 */
	public void setSignature(String name) {
		this.name = name;
	}

	/**
	 * Defines the String hash of the related {@link SheetFormat} described by this SheetFormatBuilder
	 * 
	 * @param name the String hash of the described {@link SheetFormat}
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Defines the {@link ModelFormatBuilder}s List allowing to build the {@link ModelFormat}s of the related 
	 * {@link SheetFormat} described by this SheetFormatBuilder
	 * 
	 * @param name the {@link MdelFormatBuilder}s List allowing to build the {@link ModelFormat}s of the 
	 * described {@link SheetFormat}
	 */
	public void setModels(List<ModelFormatBuilder> models) {
		this.models = models;
	}
	
	/**
	 * Returns the {@link SheetFormat} built on this SheetFormatBuilder 
	 * 
	 * @return the {@link SheetFormat} from this SheetFormatBuilder
	 */
	public SheetFormat build() {
		SheetFormat sheetFormat  = new SheetFormat();
		sheetFormat.setName(this.name);
		sheetFormat.setIdentifier(this.identifier);
		sheetFormat.setSignature(this.signature);
		sheetFormat.setHash(this.hash);
		models.stream().forEach(m->sheetFormat.append(m.build()));
		return sheetFormat;
	}
}
