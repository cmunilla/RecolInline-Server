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
