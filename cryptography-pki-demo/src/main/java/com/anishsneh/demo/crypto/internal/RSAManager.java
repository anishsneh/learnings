package com.anishsneh.demo.crypto.internal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

/**
 * The Class RSAManager.
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class RSAManager {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(RSAManager.class);
	
	/**
	 * Decrypt.
	 *
	 * @param privateKeyFilename the private key filename
	 * @param encryptedFilename the encrypted filename
	 * @param outputFilename the output filename
	 */
	public void decrypt(final String privateKeyFilename, final String encryptedFilename, final String outputFilename) {

		try {
			String value = "";
			String key = readFileAsString(privateKeyFilename);
			BASE64Decoder b64 = new BASE64Decoder();
			AsymmetricKeyParameter privateKey = (AsymmetricKeyParameter) PrivateKeyFactory.createKey(b64.decodeBuffer(key));
			AsymmetricBlockCipher e = new RSAEngine();
			e = new org.bouncycastle.crypto.encodings.PKCS1Encoding(e);
			e.init(false, privateKey);

			String inputdata = readFileAsString(encryptedFilename);
			byte[] messageBytes = hexStringToByteArray(inputdata);

			int i = 0;
			int len = e.getInputBlockSize();
			while (i < messageBytes.length) {
				if (i + len > messageBytes.length)
					len = messageBytes.length - i;

				byte[] hexEncodedCipher = e.processBlock(messageBytes, i, len);
				value = value + new String(hexEncodedCipher);
				i += e.getInputBlockSize();
			}

			logger.info("============================ DEC VALUE ===========================");
			logger.info(value);
			logger.info("==================================================================");

			BufferedWriter out = new BufferedWriter(new FileWriter(outputFilename));
			out.write(value);
			out.close();

		} 
		catch (final Exception e) {
			logger.error("Failed to decrypt file: " + e.getMessage(), e);
		}
	}

	/**
	 * Hex string to byte array.
	 *
	 * @param s the s
	 * @return the byte[]
	 */
	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	/**
	 * Encrypt.
	 *
	 * @param publicKeyFilename the public key filename
	 * @param inputFilename the input filename
	 * @param encryptedFilename the encrypted filename
	 */
	public void encrypt(final String publicKeyFilename, final String inputFilename, final String encryptedFilename) {

		try {
			String value = "";
			String key = readFileAsString(publicKeyFilename);
			BASE64Decoder b64 = new BASE64Decoder();
			AsymmetricKeyParameter publicKey = (AsymmetricKeyParameter) PublicKeyFactory.createKey(b64.decodeBuffer(key));
			AsymmetricBlockCipher e = new RSAEngine();
			e = new org.bouncycastle.crypto.encodings.PKCS1Encoding(e);
			e.init(true, publicKey);

			String inputdata = readFileAsString(inputFilename);
			byte[] messageBytes = inputdata.getBytes();
			int i = 0;
			int len = e.getInputBlockSize();
			while (i < messageBytes.length) {
				if (i + len > messageBytes.length)
					len = messageBytes.length - i;

				byte[] hexEncodedCipher = e.processBlock(messageBytes, i, len);
				value = value + getHexString(hexEncodedCipher);
				i += e.getInputBlockSize();
			}

			logger.info("============================ ENC VALUE ===========================");
			logger.info(value);
			logger.info("==================================================================");
			BufferedWriter out = new BufferedWriter(new FileWriter(encryptedFilename));
			out.write(value);
			out.close();

		} catch (final Exception e) {
			logger.error("Failed to encrypt file: " + e.getMessage(), e);
		}
	}
		
	/**
	 * Gets the hex string.
	 *
	 * @param b the b
	 * @return the hex string
	 * @throws Exception the exception
	 */
	private static String getHexString(final byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}	
	
	/**
	 * Read file as string.
	 *
	 * @param filePath the file path
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String readFileAsString(final String filePath) throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}
}
