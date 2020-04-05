package cmssi.museum.dao.service;

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
import java.util.Base64;

public class SheetSigner extends JsonStringFormatVisitor {

	private static final String DIGEST_ALGORITHM 	   = "SHA-256";
	private static final String SIGNATURE_ALGORITHM    = "SHA256withRSA";
	
	public static final String KEYSTORE_PROP           = "cmssi.recolinline.keystore";
	public static final String KEYSTORE_TYPE_PROP      = "cmssi.recolinline.keystore.type";
	public static final String KEYSTORE_STOREPASS_PROP = "cmssi.recolinline.keystore.storepass";
	public static final String KEYSTORE_KEYPASS_PROP   = "cmssi.recolinline.keystore.keypass";
	public static final String KEYSTORE_KEYALIAS_PROP  = "cmssi.recolinline.keystore.alias";
	
	private KeyStore keystore;
	private Signature signature;

	private MessageDigest digest;
	private String privateKeyPassword;
	private String alias;
	
	protected SheetSigner() throws GeneralSecurityException,IOException {		
		String keystoreFile = System.getProperty(KEYSTORE_PROP, "cert/keystore.jks");
		String keystoreType = System.getProperty(KEYSTORE_TYPE_PROP, "pkcs12");
		String keyStorePassword = System.getProperty(KEYSTORE_STOREPASS_PROP,"cmssi.recolinline!");

		this.digest = MessageDigest.getInstance(DIGEST_ALGORITHM);
		
		this.privateKeyPassword = System.getProperty(KEYSTORE_KEYPASS_PROP,"cmssi.recolinline!");
		this.alias = System.getProperty(KEYSTORE_KEYALIAS_PROP, "cmssi.selfsigned");
		
		this.keystore = KeyStore.getInstance(keystoreType);
		this.keystore.load(new FileInputStream(keystoreFile), keyStorePassword==null?null:keyStorePassword.toCharArray());
	}
	
	@Override
    protected boolean propagate() {
		return true;
    }
	
    @Override
	protected void visitSheetFormat(SheetFormat sheetFormat) {
    	try {
			this.signature.update((sheetFormat.getName()).getBytes());
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void visitModelFormat(ModelFormat modelFormat)  {
		try {
			this.signature.update((modelFormat.getName()).getBytes());
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void visitFieldFormat(FieldFormat fieldFormat) {
		try {
			this.signature.update((fieldFormat.getName()).getBytes());
			this.signature.update(fieldFormat.getLabelVisibility().getBytes());
//			String visibility = fieldFormat.getLabelVisibility();
//			switch(visibility) {	
//				case Visibility.DISABLED:
//					this.signature.update(fieldFormat.doFormat().getBytes());
//				case Visibility.ENABLED:
//				case Visibility.HIDDEN :
//					//If enabled the field content may change and if
//					//disabled it is not visible  - So in both cases
//					//we do not need to update the signature
//				default:
//					break;
//			}
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}	

	protected void sign(SheetFormat sheetFormat) throws SignatureException {
		try {
			this.signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			Key key = keystore.getKey(alias, privateKeyPassword==null?null:privateKeyPassword.toCharArray());
	        if(key instanceof PrivateKey) {
				this.signature.initSign((PrivateKey)key);
				super.visit(sheetFormat);
				byte[] signatureBytes = this.signature.sign();
				this.signature = null;
		        byte[] encryptedByteValue = Base64.getEncoder().encode(signatureBytes);
		        String signedString = new String(encryptedByteValue, "UTF-8");
		        
		        sheetFormat.setSignature(signedString);
	        }
		} catch (GeneralSecurityException | UnsupportedEncodingException | NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	protected boolean verify(String sign) throws SignatureException {
		Key key = null;
		try {
			Signature signature = Signature.getInstance("SHA256withRSA");
		    key =  keystore.getKey(alias, privateKeyPassword==null?null:privateKeyPassword.toCharArray());
	        if(key != null && key instanceof PrivateKey) {
	            Certificate cert = keystore.getCertificate(alias);
	            PublicKey publicKey = cert.getPublicKey();
				signature.initVerify(publicKey);
				return signature.verify(sign.getBytes());
			}
	        throw new UnrecoverableKeyException("Unable to find the appropriate key in the referenced keystore");
        } catch(NoSuchAlgorithmException | InvalidKeyException |UnrecoverableKeyException | KeyStoreException e) {
        	throw new SignatureException(e);
		}
	}
}
