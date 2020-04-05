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
package cmssi.museum.controler.api.format.sign;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;

import cmssi.museum.controler.api.ArtifactField;
import cmssi.museum.controler.api.ArtifactModel;
import cmssi.museum.controler.api.ArtifactSheet;
import cmssi.museum.controler.api.format.JsonStringFormat;

/**
 * Held the security material allowing it to sign and to verify an {@link ArtifactSheet} 
 *  
 * @param <F> the extended {@link ArtifactField} type
 * @param <M> the extended {@link ArtifactModel} type
 * @param <S>  the extended {@link ArtifactSheet} type
 * 
 * @author cmunilla@cmssi.fr 
 * @version 0.3
 */
public class ArtifactSheetSigner<
	F extends JsonStringFormat<String> & ArtifactField, 
	M extends JsonStringFormat<F> & ArtifactModel<F>, 
	S extends JsonStringFormat<M> & ArtifactSheet<F,M>> {

	private static final String DIGEST_ALGORITHM 	   = "SHA-256";
	private static final String SIGNATURE_ALGORITHM    = "SHA256withRSA";
	
	public static final String KEYSTORE_PROP           = "cmssi.museum.recolinline.keystore";
	public static final String KEYSTORE_TYPE_PROP      = "cmssi.museum.recolinline.keystore.type";
	public static final String KEYSTORE_STOREPASS_PROP = "cmssi.museum.recolinline.keystore.storepass";
	public static final String KEYSTORE_KEYPASS_PROP   = "cmssi.museum.recolinline.keystore.keypass";
	public static final String KEYSTORE_KEYALIAS_PROP  = "cmssi.museum.recolinline.keystore.alias";
	
	private KeyStore keystore;

	private String privateKeyPassword;
	private String alias;
	
	protected MessageDigest digest;
	
	/**
	 * Constructor
	 * 
	 * @throws GeneralSecurityException if an error occurred while building security material
	 */
	public ArtifactSheetSigner() throws GeneralSecurityException  {		
		String keystoreFile = System.getProperty(KEYSTORE_PROP, "cert/keystore.jks");
		String keystoreType = System.getProperty(KEYSTORE_TYPE_PROP, "pkcs12");
		String keyStorePassword = System.getProperty(KEYSTORE_STOREPASS_PROP,"cmssi.recolinline!");

		this.digest = MessageDigest.getInstance(DIGEST_ALGORITHM);
		
		this.privateKeyPassword = System.getProperty(KEYSTORE_KEYPASS_PROP,"cmssi.recolinline!");
		this.alias = System.getProperty(KEYSTORE_KEYALIAS_PROP, "cmssi.selfsigned");
		
		this.keystore = KeyStore.getInstance(keystoreType);
		try {
			this.keystore.load(new FileInputStream(keystoreFile), keyStorePassword==null?null:keyStorePassword.toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			throw new GeneralSecurityException(e);
		}
	}

	/**
	 * Signs the {@link ArtifactSheet} passed as parameter
	 * 
	 * @param sheet the {@link ArtifactSheet} to be signed
	 * 
	 * @throws SignatureException if an error occurred while signing the specified {@link ArtifactSheet}
	 */
	public void sign(S sheet) throws SignatureException {
		if(sheet == null) {
			throw new SignatureException("Unable to sign the sheet", new NullPointerException("Null sheet"));
		}
		try {
			final Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			Key key = keystore.getKey(alias, privateKeyPassword==null?null:privateKeyPassword.toCharArray());
	        if(!(key instanceof PrivateKey)) {
	        	throw new UnrecoverableKeyException("Unable to find the appropriate key in the referenced keystore");
	        }
	        Base64.Encoder encoder = Base64.getEncoder();
	        
			signature.initSign((PrivateKey)key);
			
	        StringBuilder builder = new StringBuilder();
	        builder.append(hashable(sheet,null));
			
			signature.update(builder.toString().getBytes());
			byte[] bytes = signature.sign();			
	        byte[] encrypted = encoder.encode(bytes);
	        
	        String generated = new String(encrypted, "UTF-8");	        
	        sheet.setSignature(generated);
	        
			builder.append("SIGN:");
			builder.append(generated);
			
			digest.reset();
			digest.update(builder.toString().getBytes());			
			bytes = digest.digest();
			
			encrypted = encoder.encode(bytes);
			generated = new String(encrypted, "UTF-8");	
			
	        sheet.setHash(generated);
	        
		} catch (GeneralSecurityException | UnsupportedEncodingException | NullPointerException e) {
			throw new SignatureException(e);
		}
	}

	/**
	 * Verifies that the {@link ArtifactSheet} passed as parameter is valid by validating both its signature and hash according 
	 * to the internal certificate in use - Returns true if it is the case ; returns false otherwise 
	 *  
	 * @param sheet the {@link ArtifactSheet} to be verified
	 * @return
	 * <ul>
	 * 	<li>true if the signature of the specified {@link ArtifactSheet} is valid</li>
	 * 	<li>false otherwise</li>
	 * </ul>
	 * @throws SignatureException if the internal certificate cannot be found
	 * @throws UnsupportedEncodingException if the hash of the specified {@link ArtifactSheet} cannot be calculated
	 */
	public boolean verify(S sheet) throws SignatureException, UnsupportedEncodingException {
		if(sheet == null) {
			throw new SignatureException("Unable to verify the sheet", new NullPointerException("Null sheet"));
		}
		String signature = sheet.getSignature();
		if(!verifySignature(signature)) {
			return false;
		}        
		String hash = hash(sheet,signature);
		String sheetHash = sheet.getHash();
		if(sheetHash == null) {
			return false;
		}
		return hash.contentEquals(sheetHash);
	}
	
	/**
	 * Checks whether the String signature passed as parameter is valid according to the internal certificate in 
	 * use - Returns true if it is the case ; returns false otherwise 
	 *  
	 * @param sign the String signature to be validated
	 * @return
	 * <ul>
	 * 	<li>true if the specified signature if valid according to internal certificate in use</li>
	 * 	<li>false otherwise</li>
	 * </ul>
	 * @throws SignatureException if the internal certificate cannot be found
	 */
	protected boolean verifySignature(String sign) throws SignatureException {
		if(sign == null) {
			return false;
		}
		Key key = null;
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		    key =  keystore.getKey(alias, privateKeyPassword==null?null:privateKeyPassword.toCharArray());
	        if(key == null || !(key instanceof PrivateKey)){
		        throw new UnrecoverableKeyException("Unable to find the appropriate key in the referenced keystore");
	        }
            Certificate cert = keystore.getCertificate(alias);
            PublicKey publicKey = cert.getPublicKey();
			signature.initVerify(publicKey);
			return signature.verify(sign.getBytes());
        } catch(NoSuchAlgorithmException | InvalidKeyException |UnrecoverableKeyException | KeyStoreException e) {
        	throw new SignatureException(e);
		}
	}
	
	/*
	 * (non-javadoc)
	 * 
	 * Calculates and returns the String hash of the {@link ArtifactSheet} passed as parameter
	 *  
	 * @param sheet the extended {@link ArtifactSheet} to  calculate the String hash of
	 * 
	 * @return the String hash of the specified {@link ArtifactSheet}
	 * 
	 * @throws UnsupportedEncodingException if an error occurred while 
	 */
	private String hash(S sheet, String signature) throws UnsupportedEncodingException {
		if(sheet==null || signature==null) {
			throw new NullPointerException();
		}
		digest.reset();
		digest.update(hashable(sheet,signature).getBytes());
		
		byte[] bytes = digest.digest();
		byte[] encrypted = Base64.getEncoder().encode(bytes);
		
		String hash = new String(encrypted, "UTF-8");
		return hash;
	}
	
	/*
	 * (non-javadoc)
	 * 
	 * Builds and returns the String representation of the {@link ArtifactSheet} passed as parameter, 
	 * including the String signature argument if provided, from which the String hash of the {@link 
	 * ArtifactSheet} is calculated
	 *  
	 * @param sheet the extended {@link ArtifactSheet} to build the hashable String representation of
	 * @param signature the String signature to be included in the hashable String representation to
	 * be returned
	 * 
	 * @return the String hashable representation of the specified {@link ArtifactSheet} and signature
	 */
	private String hashable(S sheet, String signature) {
		if(sheet==null) {
			throw new NullPointerException();
		}
        StringBuilder builder = new StringBuilder();
		builder.append("S#");
		builder.append(sheet.getName());
		sheet.stream().forEach(m -> {
			builder.append("M#");
			builder.append(m.getName());
			m.stream().forEach(f -> {
				builder.append("F#");
				builder.append(f.getName());
				switch(f.getVisibility()) {
					case DISABLED:
						digest.reset();
						digest.update(f.format().getBytes());			
						byte[] bytes = digest.digest();			
						byte[] encrypted = Base64.getEncoder().encode(bytes);
						builder.append(encrypted); 
					case ENABLED:
					case HIDDEN:
						builder.append(f.getVisibility().name().charAt(0));
					default:
						break;					
				}
			});
		});		
		if(signature != null) {
			builder.append("SIGN:");
			builder.append(signature);
		}
		return builder.toString();
	}
}
