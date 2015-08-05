package com.anishsneh.demo.crypto.internal;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

import com.anishsneh.demo.crypto.util.FixedRandom;

/**
 * The Class KeyManager.
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class KeyManager {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(KeyManager.class);
	
	/**
	 * Genrate keys.
	 *
	 * @param privateKeyFileName the private key file name
	 * @param publicKeyFileName the public key file name
	 * @return the key pair
	 */
	public KeyPair genrateKeys(final String privateKeyFileName, final String publicKeyFileName) {
		logger.info("Generating key pair");
		try {
			final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

			final SecureRandom random = createRandom(false);
			generator.initialize(1024, random);

			KeyPair pair = generator.generateKeyPair();
			Key pubKey = pair.getPublic();
			Key privKey = pair.getPrivate();

			logger.info("=========================== PUBLIC KEY ===========================");
			logger.info(new BASE64Encoder().encode(pubKey.getEncoded()));
			logger.info("==================================================================");
			logger.info("=========================== PRIVATE KEY ===========================");
			logger.info(new BASE64Encoder().encode(privKey.getEncoded()));
			logger.info("==================================================================");

			BufferedWriter out = new BufferedWriter(new FileWriter(publicKeyFileName));
			out.write(new BASE64Encoder().encode(pubKey.getEncoded()));
			out.close();

			out = new BufferedWriter(new FileWriter(privateKeyFileName));
			out.write(new BASE64Encoder().encode(privKey.getEncoded()));
			out.close();
			return pair;

		} 
		catch (final Exception e) {
			logger.error("Failed to generate keys: " + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Adds the certificate to key store.
	 *
	 * @param certificate the certificate
	 * @param certificateAlias the certificate alias
	 * @param jksPath the jks path
	 * @param password the password
	 * @throws Exception the exception
	 */
	public void addCertificateToKeyStore(final Certificate certificate, final String certificateAlias, final String jksPath, final char[] password) throws Exception{
		createEmptyKeyStore(jksPath, password);
		final KeyStore ks = loadKeyStore(jksPath, password);
		ks.setCertificateEntry(certificateAlias, certificate);
		FileOutputStream fos = new FileOutputStream(jksPath);
    	ks.store(fos, password);
    	fos.close();
	}
	
	/**
	 * Creates the empty key store.
	 *
	 * @param jksPath the jks path
	 * @param password the password
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean createEmptyKeyStore(final String jksPath, final char[] password) throws Exception{
		final KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    	ks.load(null, password);
    	FileOutputStream fos = new FileOutputStream(jksPath);
    	ks.store(fos, password);
    	fos.close();    	
    	return true;
	}
	
	/**
	 * Load key store.
	 *
	 * @param jksPath the jks path
	 * @param password the password
	 * @return the key store
	 * @throws Exception the exception
	 */
	public KeyStore loadKeyStore(final String jksPath, final char[] password) throws Exception{
		KeyStore ks  = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream(jksPath), password);
		return ks;
	}

	/**
	 * Creates the random.
	 *
	 * @param isFixed the is fixed
	 * @return the secure random
	 */
	private SecureRandom createRandom(final boolean isFixed) {
		if (isFixed) {
			return new FixedRandom();
		} else {
			return new SecureRandom();
		}
	}
	
}
