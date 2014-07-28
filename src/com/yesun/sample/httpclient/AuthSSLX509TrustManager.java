package com.yesun.sample.httpclient;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate; 

import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 

/**
 * mipPlugin-mdp AuthSSLX509TrustManager.java
 * Description:
 * Copyright (c) Digital Heaven. All Rights Reserved.
 * @version 1.0
 * ChangeLog:
 * 1.0 YESUN Aug 13, 2010 11:37:27 AM Create.
 */
public class AuthSSLX509TrustManager implements X509TrustManager
{
    private X509TrustManager defaultTrustManager = null; 

    /** Log object for this class. */
    private static final Log LOG = LogFactory.getLog(AuthSSLX509TrustManager.class); 

    /**
     * Constructor for AuthSSLX509TrustManager.
     */
    public AuthSSLX509TrustManager(final X509TrustManager defaultTrustManager) {
        super();
        if (defaultTrustManager == null) {
            throw new IllegalArgumentException("Trust manager may not be null");
        }
        this.defaultTrustManager = defaultTrustManager;
    } 

    /**
     * @see com.sun.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public X509Certificate[] getAcceptedIssuers() {
        return this.defaultTrustManager.getAcceptedIssuers();
    }

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		 if (LOG.isInfoEnabled() && chain != null) {
	            for (int c = 0; c < chain.length; c++) {
	                X509Certificate cert = chain[c];
	                LOG.info(" Client certificate " + (c + 1) + ":");
	                LOG.info("  Subject DN: " + cert.getSubjectDN());
	                LOG.info("  Signature Algorithm: " + cert.getSigAlgName());
	                LOG.info("  Valid from: " + cert.getNotBefore() );
	                LOG.info("  Valid until: " + cert.getNotAfter());
	                LOG.info("  Issuer: " + cert.getIssuerDN());
	            }
	        }
		 defaultTrustManager.checkClientTrusted(chain, authType);		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {

		if (LOG.isInfoEnabled() && chain != null) {
            for (int c = 0; c < chain.length; c++) {
                X509Certificate cert = chain[c];
                LOG.info(" Server certificate " + (c + 1) + ":");
                LOG.info("  Subject DN: " + cert.getSubjectDN());
                LOG.info("  Signature Algorithm: " + cert.getSigAlgName());
                LOG.info("  Valid from: " + cert.getNotBefore() );
                LOG.info("  Valid until: " + cert.getNotAfter());
                LOG.info("  Issuer: " + cert.getIssuerDN());
            }
        }
		defaultTrustManager.checkClientTrusted(chain, authType);		
	}
} 


