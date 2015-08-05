package com.anishsneh.demo.crypto.internal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anishsneh.demo.crypto.util.CryptoUtil;

/**
 * The Class CertificateManager.
 * 
 * @author Anish Sneh
 */
@SuppressWarnings("all")
public class CertificateManager {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CertificateManager.class);

	/**
	 * Gets the key pair for root ca.
	 *
	 * @return the key pair for root ca
	 * @throws Exception the exception
	 */
	public KeyPair getKeyPairForRootCA() throws Exception {
		return getKeyPairForCAByAlias(CryptoUtil.ROOT_CA_CERT_ALIAS);
	}
	
	/**
	 * Gets the key pair for ca by alias.
	 *
	 * @param certificateAlias the certificate alias
	 * @return the key pair for ca by alias
	 * @throws Exception the exception
	 */
	public KeyPair getKeyPairForCAByAlias(final String certificateAlias) throws Exception {
		final FileInputStream is = new FileInputStream(CryptoUtil.ROOT_CA_JKS_FILE);
		final KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(is, CryptoUtil.ROOT_CA_JKS_PASSWORD.toCharArray());
		final Key key = keystore.getKey(certificateAlias, CryptoUtil.ROOT_CA_JKS_PASSWORD.toCharArray());
		logger.info(key.toString());
		if (key instanceof PrivateKey) {
			final Certificate cert = keystore.getCertificate(certificateAlias);
			final PublicKey publicKey = cert.getPublicKey();
			return new KeyPair(publicKey, (PrivateKey) key);
		}
		return null;
	}
	
	/**
	 * Generate csr.
	 *
	 * @param keyPair the key pair
	 * @return the PKC s10 certification request
	 * @throws OperatorCreationException the operator creation exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PKCS10CertificationRequest generateCsr(final KeyPair keyPair) throws OperatorCreationException, IOException {
		ContentSigner signGen = new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate());
		X500Principal subject = new X500Principal("C=IN, ST=Karnataka, L=Bangalore, O=Fake Systems, OU=Innovation, CN=www.fake.com, EMAILADDRESS=anishsneh@fake.com");
		PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(subject, keyPair.getPublic());
		PKCS10CertificationRequest request = builder.build(signGen);
		
		return request;
	}
	
	/**
	 * Sign csr.
	 *
	 * @param request the request
	 * @param subjectCAKeyPair the subject ca key pair
	 * @param signerCAKeyPair the signer ca key pair
	 * @return the java.security.cert. certificate
	 * @throws Exception the exception
	 */
	public Certificate signCsr(final PKCS10CertificationRequest request, final KeyPair subjectCAKeyPair, final KeyPair signerCAKeyPair) throws Exception{
		AlgorithmIdentifier sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find("SHA256withRSA");
		AlgorithmIdentifier digAlgId = new DefaultDigestAlgorithmIdentifierFinder().find(sigAlgId);
		AsymmetricKeyParameter foo = PrivateKeyFactory.createKey(signerCAKeyPair.getPrivate().getEncoded());
		SubjectPublicKeyInfo keyInfo = SubjectPublicKeyInfo.getInstance(subjectCAKeyPair.getPublic().getEncoded());
		
		X509v3CertificateBuilder myCertificateGenerator = new X509v3CertificateBuilder(new X500Name("CN=anishsneh"), new BigInteger("1"), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 30 * 365 * 24 * 60 * 60 * 1000), request.getSubject(), keyInfo);
		ContentSigner sigGen = new BcRSAContentSignerBuilder(sigAlgId, digAlgId).build(foo);
		X509CertificateHolder holder = myCertificateGenerator.build(sigGen);
		org.bouncycastle.asn1.x509.Certificate eeX509CertificateStructure = holder.toASN1Structure();
		CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
		
		InputStream is1 = new ByteArrayInputStream(eeX509CertificateStructure.getEncoded());
		java.security.cert.Certificate theCert = cf.generateCertificate(is1);
		is1.close();
		
		theCert.toString();
		return theCert;
	}	
	
	/**
	 * Store to key store.
	 *
	 * @param certificate the certificate
	 * @param certificateAlias the certificate alias
	 * @param jksPath the jks path
	 * @param password the password
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean storeToKeyStore(final Certificate certificate, final String certificateAlias, final String jksPath, final String password) throws Exception{
		final KeyManager keyManager = new KeyManager();
		keyManager.addCertificateToKeyStore(certificate, certificateAlias, jksPath, password.toCharArray());
		return true;
	}
	
	/**
	 * Generate demo ca certificate.
	 *
	 * @param subject the subject
	 * @param issuer the issuer
	 * @param yearsOfValid the years of valid
	 * @param caAlias the ca alias
	 */
	public void generateDemoCACertificate(final String subject, final String issuer, final long yearsOfValid, final String caAlias) {
		X509Certificate cert = null;
		try {
			final KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
			gen.initialize(2048);
			final KeyPair keypair = gen.generateKeyPair();
			PrivateKey caPrivateKey = keypair.getPrivate();
			PublicKey caPublicKey = keypair.getPublic();
			final Date validityBeginDate = new Date(System.currentTimeMillis() - 24l * 60l * 60l * 1000l);

			final Date validityEndDate = new Date(System.currentTimeMillis() + (yearsOfValid * 365l * 24l * 60l * 60l * 1000L));

			final X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();

			final X500Principal dnName = new X500Principal(subject);
			if (!subject.equals(issuer)) {
				final PrivateKeyEntry pvtKey = getPrivateKey(CryptoUtil.ROOT_CA_JKS_FILE, CryptoUtil.ROOT_CA_JKS_PASSWORD, CryptoUtil.ROOT_CA_CERT_ALIAS);
				certGen.setIssuerDN(new X500Principal(issuer));
				caPrivateKey = pvtKey.getPrivateKey();
				caPublicKey = pvtKey.getCertificate().getPublicKey();

			} else {
				certGen.setIssuerDN(dnName);
			}

			certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
			certGen.setSubjectDN(dnName);
			certGen.setNotBefore(validityBeginDate);
			certGen.setNotAfter(validityEndDate);
			certGen.setPublicKey(keypair.getPublic());
			certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");

			certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false, (new JcaX509ExtensionUtils().createSubjectKeyIdentifier(keypair.getPublic())));
			certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false, (new AuthorityKeyIdentifierStructure(caPublicKey)));
			certGen.addExtension(X509Extensions.BasicConstraints, true, new BasicConstraints(true));
			final KeyUsage keyUsage = new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyCertSign | KeyUsage.cRLSign | KeyUsage.keyEncipherment);
			certGen.addExtension(X509Extensions.KeyUsage, false, keyUsage);

			cert = certGen.generate(caPrivateKey, "BC");
			addEntry(keypair.getPrivate(), cert, caAlias, issuer);
		} catch (final Exception e) {
			logger.error("Failed to generate demo CA certificate for: " + subject + "; "+ e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds the entry.
	 *
	 * @param privateKey the private key
	 * @param cert the cert
	 * @param alias the alias
	 * @param aliasIssuer the alias issuer
	 */
	private void addEntry(final PrivateKey privateKey, final Certificate cert, final String alias, final String aliasIssuer) {
		OutputStream fos = null;
		InputStream fis = null;

		try {

			final KeyStore ks = KeyStore.getInstance("JKS");
			final File file = new File(CryptoUtil.ROOT_CA_JKS_FILE);
			try {

				if (!file.exists()) {
					file.createNewFile();
				} else {
					fis = new FileInputStream(file);

				}
			} 
			catch (final Exception e) {
				logger.error("Failed to add entry: " + e.getMessage(), e);
			}
			final String aliasPrivateKey = alias;
			ks.load(fis, CryptoUtil.ROOT_CA_JKS_PASSWORD.toCharArray());

			PrivateKey privateKeyLoaded = null;
			Certificate[] chain = { cert };
			Certificate[] certificateChain = { cert };
			try {

				chain = ks.getCertificateChain(aliasIssuer);
				certificateChain = new Certificate[chain.length + 1];
				for (int i = 1; i <= chain.length; i++) {
					certificateChain[i] = chain[i - 1];
				}
				certificateChain[0] = cert;
			} catch (final Exception ex) {

			}
			if (privateKeyLoaded == null) {
				privateKeyLoaded = privateKey;
			}
			fos = new FileOutputStream(file);
			ks.setKeyEntry(aliasPrivateKey, privateKeyLoaded, CryptoUtil.ROOT_CA_JKS_PASSWORD.toCharArray(), certificateChain);
			ks.store(fos, CryptoUtil.ROOT_CA_JKS_PASSWORD.toCharArray());
			fos.flush();
			logger.info("Created: " + alias);
		} catch (final Exception ex) {
			logger.error("Failed to add entry: " + ex.getMessage(), ex);
		} finally {

			try {
				if (fos != null) {
					fos.close();
				}

			} catch (final Exception ex) {

			}
			try {
				if (fis != null) {
					fis.close();
				}

			} catch (final Exception ex) {

			}

		}
	}

	/**
	 * Gets the private key.
	 *
	 * @param pathfile the pathfile
	 * @param password the password
	 * @param alias the alias
	 * @return the private key
	 */
	public PrivateKeyEntry getPrivateKey(final String pathfile, final String password, final String alias) {
		PrivateKeyEntry privateKeyEntry = null;
		try {
			final KeyStore ks = KeyStore.getInstance("JKS");
			final File file = new File(pathfile);
			InputStream fis;
			fis = new FileInputStream(file);
			ks.load(fis, password.toCharArray());
			privateKeyEntry = (PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));
			fis.close();
		} 
		catch (final Exception e) {
			logger.error("Failed to get private key for alias: " + alias, e);
		}
		return privateKeyEntry;
	}
}
