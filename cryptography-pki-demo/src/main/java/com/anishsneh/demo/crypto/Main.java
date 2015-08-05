package com.anishsneh.demo.crypto;

import java.security.KeyPair;
import java.security.cert.Certificate;

import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.crypto.internal.CryptoManager;
import com.anishsneh.demo.crypto.util.CryptoUtil;

/**
 * The Class Main.
 * 
 * Run using:
 * 
 * $ mvn exec:java
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class Main {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		
		CryptoUtil.setup();
		
		final CryptoManager cryptoManager = new CryptoManager();
		
		//File encryption and decryption using RSA key pair
		cryptoManager.generateCryptoKeyPair();		
		final String encryptedFilePath = cryptoManager.encryptFile(CryptoUtil.SAMPLE_INPUT_FILE);
		logger.info("Encrypted file store at: " + encryptedFilePath);
		final String decryptedFilePath = cryptoManager.decryptFile(encryptedFilePath);
		logger.info("Decrypted file store at: " + decryptedFilePath);
		
		//Generate key pairs for CSR and signing 
		final KeyPair subjectKeyPair = cryptoManager.generateKeyPair();
		cryptoManager.generateDemoCACertificateSignedByRootCA();
		final KeyPair signerCAKeyPair = cryptoManager.getKeyPairForCA(CryptoUtil.DEMO_CA_CERT_ALIAS);
		
		//Generate CSR using client/subject keys
		final PKCS10CertificationRequest signingRequest = cryptoManager.generateCsr(subjectKeyPair);
		
		//Sign CSR using CA/signer
		final Certificate signedCertificate = cryptoManager.signCsr(signingRequest, subjectKeyPair, signerCAKeyPair);
		
		//Store signed certificate to keystore
		cryptoManager.storeToKeyStore(signedCertificate);
		
	}

}
