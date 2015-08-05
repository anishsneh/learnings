package com.anishsneh.demo.crypto.internal;

import java.security.KeyPair;
import java.security.cert.Certificate;

import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.crypto.util.CryptoUtil;

/**
 * The Class CryptoManager.
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class CryptoManager {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CryptoManager.class);
	
	/** The certificate manager. */
	final CertificateManager certificateManager = new CertificateManager();
	
	/** The key manager. */
	final KeyManager keyManager = new KeyManager();
	
	/** The rsa manager. */
	final RSAManager rsaManager = new RSAManager();
	
	/**
	 * Generate crypto key pair.
	 */
	public void generateCryptoKeyPair(){	
		generateKeyPair();
	}

	/**
	 * Generate key pair.
	 *
	 * @return the key pair
	 */
	public KeyPair generateKeyPair(){		
		final KeyPair keyPair = keyManager.genrateKeys(CryptoUtil.PRIVATE_KEY_PATH, CryptoUtil.PUBLIC_KEY_PATH);
		return keyPair;
	}
	
	/**
	 * Encrypt file.
	 *
	 * @param inputFilePath the input file path
	 * @return the string
	 */
	public String encryptFile(final String inputFilePath){
		final String encryptedFilePath = inputFilePath + ".enc";
		rsaManager.encrypt(CryptoUtil.PUBLIC_KEY_PATH, inputFilePath, encryptedFilePath);
		return encryptedFilePath;
	}
	
	/**
	 * Decrypt file.
	 *
	 * @param encryptedFilePath the encrypted file path
	 * @return the string
	 */
	public String decryptFile(final String encryptedFilePath){
		final String decryptedFilePath = encryptedFilePath + ".dec";
		rsaManager.decrypt(CryptoUtil.PRIVATE_KEY_PATH, encryptedFilePath, decryptedFilePath);
		return decryptedFilePath;
	}
	
	/**
	 * Gets the key pair for ca.
	 *
	 * @param certificateAlias the certificate alias
	 * @return the key pair for ca
	 */
	public KeyPair getKeyPairForCA(final String certificateAlias){
		KeyPair signerCAKeyPair = null;
		try {
			signerCAKeyPair = certificateManager.getKeyPairForCAByAlias(certificateAlias);
		} 
		catch (final Exception e) {
			logger.error("Failed to get key pair for CA: " + e.getMessage(), e);
		}
		return signerCAKeyPair;
	}
	
	/**
	 * Generate demo ca certificate signed by root ca.
	 */
	public void generateDemoCACertificateSignedByRootCA(){		
		certificateManager.generateDemoCACertificate("CN=ca.demo.com, OU=Finance, O=Demo Systems, L=Chandigarh, ST=Punjab, C=IN", "CN=Anish Sneh, OU=Fraud Systems, O=LifeRocks, L=Gurgaon, ST=NCR, C=IN", 1, CryptoUtil.DEMO_CA_CERT_ALIAS);
	}
	
	/**
	 * Generate csr.
	 *
	 * @param subjectKeyPair the subject key pair
	 * @return the PKC s10 certification request
	 */
	public PKCS10CertificationRequest generateCsr(final KeyPair subjectKeyPair){
		PKCS10CertificationRequest csr = null;
		try {
			csr = certificateManager.generateCsr(subjectKeyPair);
		} 
		catch (final Exception e) {
			logger.error("Failed to generate CSR: " + e.getMessage(), e);
		}
		return csr;
	}
	
	/**
	 * Sign csr.
	 *
	 * @param request the request
	 * @param subjectCAKeyPair the subject ca key pair
	 * @param signerCAKeyPair the signer ca key pair
	 * @return the certificate
	 */
	public Certificate signCsr(final PKCS10CertificationRequest request, final KeyPair subjectCAKeyPair, final KeyPair signerCAKeyPair){
		Certificate certificate = null;
		try {
			certificate = certificateManager.signCsr(request, subjectCAKeyPair, signerCAKeyPair);
		} 
		catch (final Exception e) {
			logger.error("Failed to sign CSR: " + e.getMessage(), e);
		}
		return certificate;
	}
	
	/**
	 * Store to key store.
	 *
	 * @param certificate the certificate
	 */
	public void storeToKeyStore(final Certificate certificate){
		try {
			certificateManager.storeToKeyStore(certificate, CryptoUtil.DEMO_KEY_ALIAS, CryptoUtil.DEMO_JKS_FILE, CryptoUtil.DEMO_JKS_PASSWORD);
		} 
		catch (final Exception e) {
			logger.error("Failed to store certificate to JKS keystore: " + e.getMessage(), e);
		}
	}
}
