package com.anishsneh.demo.crypto.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * The Class CryptoUtil.
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class CryptoUtil {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CryptoUtil.class);

	/** The Constant BASE_PATH. */
	public static final String BASE_PATH = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "/crypto_demo";
	
	/** The Constant PRIVATE_KEY_PATH. */
	public static final String PRIVATE_KEY_PATH = BASE_PATH + System.getProperty("file.separator") + "private.key";
	
	/** The Constant PUBLIC_KEY_PATH. */
	public static final String PUBLIC_KEY_PATH = BASE_PATH + System.getProperty("file.separator") + "public.key";
	
	/** The Constant SAMPLE_INPUT_FILE. */
	public static final String SAMPLE_INPUT_FILE = BASE_PATH + System.getProperty("file.separator") + "input.txt";
	
	/*
	 * ROOT_CA_JKS_FILE is a valid self signed root CA created by:
	 * 
	 * $ keytool -genkey -alias rootCACertAlias -keyalg RSA -keystore rootCAKeystore.jks -keysize 4096 -ext BasicConstraints:"critical=ca:true" -ext KeyUsage="digitalSignature,keyCertSign,cRLSign,keyEncipherment" -dname "CN=Anish Sneh, OU=Fraud Systems, O=LifeRocks, L=Gurgaon, ST=NCR, C=IN"
	 * 
	 * Using key and keystore password: Welcome1 
	 */
	public static final String ROOT_CA_JKS_FILE_NAME = "rootCAKeystore.jks";
	
	/** The Constant ROOT_CA_JKS_FILE. */
	public static final String ROOT_CA_JKS_FILE = BASE_PATH + System.getProperty("file.separator") + ROOT_CA_JKS_FILE_NAME;
    
    /** The Constant ROOT_CA_JKS_PASSWORD. */
    public static final String ROOT_CA_JKS_PASSWORD = "Welcome1";
    
    /** The Constant ROOT_CA_CERT_ALIAS. */
    public static final String ROOT_CA_CERT_ALIAS = "rootcacertalias";
    
    /** The Constant DEMO_CA_CERT_ALIAS. */
    public static final String DEMO_CA_CERT_ALIAS = "democacertalias";
    
    /** The Constant DEMO_JKS_FILE. */
    public static final String DEMO_JKS_FILE = BASE_PATH + System.getProperty("file.separator") + "demoKeyStore.jks";
    
    /** The Constant DEMO_JKS_PASSWORD. */
    public static final String DEMO_JKS_PASSWORD = "Welcome1";
    
    /** The Constant DEMO_KEY_ALIAS. */
    public static final String DEMO_KEY_ALIAS = "demoCertAlias";
	
	/**
	 * Setup.
	 */
	public static void setup(){
		logger.info("Setting up environment");
		Security.addProvider(new BouncyCastleProvider());
		cleanup();
		init();
		createSampleFile();
		cloneOriginalKeystore();
	}
	
	/**
	 * Cleanup.
	 */
	private static void cleanup(){
		logger.info("Cleaning up directories");
		deleteFileOrFolder(new File(BASE_PATH));
	}
	
	/**
	 * Clone original keystore.
	 */
	private static void cloneOriginalKeystore(){
		logger.info("Cloning original (external) root CA keystore");
		try {
			final URL url = CryptoUtil.class.getResource("/keys/" + ROOT_CA_JKS_FILE_NAME);
			Files.copy(new File(url.toURI()), new File(ROOT_CA_JKS_FILE));
		} catch (final Exception e) {
			logger.error("Failed to clone keystore: " + e.getMessage(), e);
		}
	}
	
	/**
	 * Inits the.
	 */
	private static void init(){
		logger.info("Initializing directories");
		final File file = new File(BASE_PATH);
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	/**
	 * Delete file or folder.
	 *
	 * @param file the file
	 */
	private static void deleteFileOrFolder(final File file) {
		logger.info("Deleting files");
		if (!file.exists()) {
			return;
		}
		try {
			for (final File f : file.listFiles()) {
				f.delete();
				deleteFileOrFolder(f);
			}
		} catch (final Exception e) {
			logger.error("Failed to delete files: " + e.getMessage(), e);
		}
	}
	
	/**
	 * Creates the sample file.
	 */
	private static void createSampleFile() {
		final String sampleText = "Public-key cryptography, also known as asymmetric cryptography, is a class of cryptographic protocols based on algorithms that require two separate keys, one of which is secret (or private) and one of which is public. Although different, the two parts of this key pair are mathematically linked. The public key is used, for example, to encrypt plaintext or to verify a digital signature; whereas the private key is used for the opposite operation, in these examples to decrypt ciphertext or to create a digital signature. The term asymmetric stems from the use of different keys to perform these opposite functions, each the inverse of the other – as contrasted with conventional (symmetric) cryptography which relies on the same key to perform both.";
		try {
			Files.write(sampleText, new File(SAMPLE_INPUT_FILE), Charsets.UTF_8);
		} 
		catch (final IOException e) {
			logger.error("Failed to create sample file: " + e.getMessage(), e);
		}
	}
	
}
