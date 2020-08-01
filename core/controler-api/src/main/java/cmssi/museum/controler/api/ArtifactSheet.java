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
package cmssi.museum.controler.api;

import cmssi.museum.controler.api.format.JsonStringFormat;

/**
 * An ArtifactSheet held the fields of the set of models of the domain 
 * to which it belongs
 *   
 * @param <F> the extended  {@link ArtifactField}
 * @param <M> the extended {@link ArtifactModel}
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
