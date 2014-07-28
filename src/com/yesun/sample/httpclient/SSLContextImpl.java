package com.yesun.sample.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ControllerThreadSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SSLContextImpl{

	/** Log object for this class. */
	private static final Log LOG = LogFactory
			.getLog(SSLContextImpl.class);

	private URL keystoreUrl = null;

	private String keystorePassword = null;

	private URL truststoreUrl = null;

	private String truststorePassword = null;
	
	private static String keystoreType = "PKCS12";
	private static String trustsotreType = "PKCS12";

	private SSLContext sslcontext = null;

	/**
	 * Constructor for AuthSSLProtocolSocketFactory. Either a keystore or
	 * truststore file must be given. Otherwise SSL context initialization error
	 * will result.
	 * 
	 * @param keystoreUrl
	 *            URL of the keystore file. May be <tt>null</tt> if HTTPS
	 *            client authentication is not to be used.
	 * @param keystorePassword
	 *            Password to unlock the keystore. IMPORTANT: this
	 *            implementation assumes that the same password is used to
	 *            protect the key and the keystore itself.
	 * @param truststoreUrl
	 *            URL of the truststore file. May be <tt>null</tt> if HTTPS
	 *            server authentication is not to be used.
	 * @param truststorePassword
	 *            Password to unlock the truststore.
	 */
	public SSLContextImpl(final URL keystoreUrl,
			final String keystorePassword, final URL truststoreUrl,
			final String truststorePassword, final String keystoreType, final String trustsotreType) {
		super();
		this.keystoreUrl = keystoreUrl;
		this.keystorePassword = keystorePassword;
		this.truststoreUrl = truststoreUrl;
		this.truststorePassword = truststorePassword;
		this.keystoreType = keystoreType;
		this.trustsotreType = trustsotreType;
	}

	private static KeyStore createKeyStore(final URL url, final String password, final String storetype)
			throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException {
		if (url == null) {
			throw new IllegalArgumentException("Keystore url may not be null");
		}
		LOG.debug("Initializing key store");
		KeyStore keystore = KeyStore.getInstance(storetype);// 读取p12证书
		keystore.load(url.openStream(), password != null ? password.toCharArray() : null);
//		try
//		{
//			
//		}
//		catch(Exception ex){
//			System.out.println(ex.getMessage());
//			System.out.println("自动更换证书格式");
//			if(storeType.equalsIgnoreCase("JKS")){
//				storeType = "PKCS12";
//			}
//			else{
//				storeType = "JKS";
//			}
//			keystore = KeyStore.getInstance(storeType);
//			keystore.load(url.openStream(), password != null ? password.toCharArray() : null);
//		}
		return keystore;
	}

	private static KeyManager[] createKeyManagers(final KeyStore keystore,
			final String password) throws KeyStoreException,
			NoSuchAlgorithmException, UnrecoverableKeyException {
		if (keystore == null) {
			throw new IllegalArgumentException("Keystore may not be null");
		}
		LOG.debug("Initializing key manager");
		KeyManagerFactory kmfactory = KeyManagerFactory
				.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmfactory.init(keystore, password != null ? password.toCharArray()
				: null);
		return kmfactory.getKeyManagers();
	}

	private static TrustManager[] createTrustManagers(final KeyStore keystore)
			throws KeyStoreException, NoSuchAlgorithmException {
		if (keystore == null) {
			throw new IllegalArgumentException("Keystore may not be null");
		}
		LOG.debug("Initializing trust manager");
		TrustManagerFactory tmfactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmfactory.init(keystore);
		TrustManager[] trustmanagers = tmfactory.getTrustManagers();
		for (int i = 0; i < trustmanagers.length; i++) {
			if (trustmanagers[i] instanceof X509TrustManager) {
				trustmanagers[i] = new AuthSSLX509TrustManager((X509TrustManager) trustmanagers[i]);
			}
		}
		return trustmanagers;
	}

	public SSLContext createSSLContext() {
		try {
			KeyManager[] keymanagers = null;
			TrustManager[] trustmanagers = null;
			if (this.keystoreUrl != null) {
				KeyStore keystore = createKeyStore(this.keystoreUrl,
						this.keystorePassword, this.keystoreType);
				if (LOG.isDebugEnabled()) {
					Enumeration aliases = keystore.aliases();
					while (aliases.hasMoreElements()) {
						String alias = (String) aliases.nextElement();
						Certificate[] certs = keystore
								.getCertificateChain(alias);
						if (certs != null) {
							LOG.debug("Certificate chain '" + alias + "':");
							for (int c = 0; c < certs.length; c++) {
								if (certs[c] instanceof X509Certificate) {
									X509Certificate cert = (X509Certificate) certs[c];
									LOG.debug(" Certificate " + (c + 1) + ":");
									LOG.debug("  Subject DN: "
											+ cert.getSubjectDN());
									LOG.debug("  Signature Algorithm: "
											+ cert.getSigAlgName());
									LOG.debug("  Valid from: "
											+ cert.getNotBefore());
									LOG.debug("  Valid until: "
											+ cert.getNotAfter());
									LOG
											.debug("  Issuer: "
													+ cert.getIssuerDN());
								}
							}
						}
					}
				}
				keymanagers = createKeyManagers(keystore, this.keystorePassword);
			}
			if (this.truststoreUrl != null) {
				KeyStore keystore = createKeyStore(this.truststoreUrl,
						this.truststorePassword, this.trustsotreType);
				if (LOG.isDebugEnabled()) {
					Enumeration aliases = keystore.aliases();
					while (aliases.hasMoreElements()) {
						String alias = (String) aliases.nextElement();
						LOG.debug("Trusted certificate '" + alias + "':");
						Certificate trustedcert = keystore
								.getCertificate(alias);
						if (trustedcert != null
								&& trustedcert instanceof X509Certificate) {
							X509Certificate cert = (X509Certificate) trustedcert;
							LOG.debug("  Subject DN: " + cert.getSubjectDN());
							LOG.debug("  Signature Algorithm: "
									+ cert.getSigAlgName());
							LOG.debug("  Valid from: " + cert.getNotBefore());
							LOG.debug("  Valid until: " + cert.getNotAfter());
							LOG.debug("  Issuer: " + cert.getIssuerDN());
						}
					}
				}
				trustmanagers = createTrustManagers(keystore);
			}
			SSLContext sslcontext = SSLContext.getInstance("SSL");
			sslcontext.init(keymanagers, trustmanagers, null);
			return sslcontext;
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage(), e);
			throw new IllegalArgumentException(
					"Unsupported algorithm exception: " + e.getMessage());
		} catch (KeyStoreException e) {
			LOG.error(e.getMessage(), e);
			throw new IllegalArgumentException("Keystore exception: "
					+ e.getMessage());
		} catch (GeneralSecurityException e) {
			LOG.error(e.getMessage(), e);
			throw new IllegalArgumentException("Key management exception: "
					+ e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new IllegalArgumentException(
					"I/O error reading keystore/truststore file: "
							+ e.getMessage());
		}
	}

	private SSLContext getSSLContext() {
		if (this.sslcontext == null) {
			this.sslcontext = createSSLContext();
		}
		return this.sslcontext;
	}

}
