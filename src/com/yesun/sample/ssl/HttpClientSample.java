package com.yesun.sample.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;

public class HttpClientSample {
	
	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-4-6 上午9:18:28 Create.
	 * ChangeLog:
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		
		String url = "https://211.156.194.132/login_noca.jsp";
		String keystoreUrl = "file:c:\\3899482_禹蓓.pfx";
		String keystorePass = "Wq2GQHCLepc=561560";
		
		Protocol myhttps = new Protocol("https", new AuthSSLProtocolSocketFactory(new URL(keystoreUrl), keystorePass, null, null, "PKCS12", "PKCS12"), 443);
		Protocol.registerProtocol("https", myhttps);

		HttpClient httpClient = new HttpClient();
		
		
		GetMethod getHC = new GetMethod(url);
		getHC.getParams().setHttpElementCharset("UTF-8");
		
		try {
			int statusCode = httpClient.executeMethod(getHC);
			System.out.println(getHC.getResponseBodyAsString());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			getHC.abort();
			getHC.releaseConnection();
		}
		
		
	}

}
