package com.yesun.sample.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStore.LoadStoreParameter;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientSample {
	
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException{
		
		//https://mail.d-heaven.com/owa
		//https://172.30.0.103:8445/
		
		HttpClientSample httpclient = new HttpClientSample("https://172.30.0.103:8445/");
		httpclient.Request();
	}
	
	String url = "";
	boolean isDoubleAuth = true;
	public HttpClientSample(String url){
		this.url = url;
	}
	
	DefaultHttpClient httpclient;
	
	public void Request(){
		
		httpclient = new DefaultHttpClient();
		HttpGet method = new HttpGet(url);
		//HTTPS
		if(url.startsWith("https://")){
			enableSSL();
		}
		
		HttpResponse response;
		try {
			response = httpclient.execute(method);
			System.out.println(response.getStatusLine());
			response.getEntity().writeTo(System.out);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			//TODO释放资源
		}
	}
	
	
	public void enableSSL(){
		
		if(isDoubleAuth == true){
			//双向认证
			try {
				//这里完全可以做的更完善，可支持其他格式证书；另外要支持每个用户一套证书
				AuthSSLProtocolSocketFactory sslContextImpl = new AuthSSLProtocolSocketFactory(new URL("file:d:\\client.p12"), "000000", new URL("file:d:\\ca.p12"), "000000", "PKCS12", "PKCS12");
				SSLContext sslContext = sslContextImpl.createSSLContext();
				SSLSocketFactory factory = new SSLSocketFactory(sslContext);
				Scheme https = new Scheme("https", factory , 443);             
				httpclient.getConnectionManager().getSchemeRegistry().register(https); 
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else{
			//单向认证
			SSLContext sslcontext;
			try {
				sslcontext = SSLContext.getInstance("SSL");//TLS     
				sslcontext.init(null, new TrustManager[] { truseAllManager }, null);             
				SSLSocketFactory factory = new SSLSocketFactory(sslcontext);             
				factory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);   
				Scheme https = new Scheme("https", factory, 443);             
				httpclient.getConnectionManager().getSchemeRegistry().register(https); 
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
		}
	}


	private static TrustManager truseAllManager = new X509TrustManager() {

		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[]{};
		}};
		
		
		private static KeyManager[] createKeyManagers(final KeyStore keystore,
				final String password) throws KeyStoreException,
				NoSuchAlgorithmException, UnrecoverableKeyException {
			if (keystore == null) {
				throw new IllegalArgumentException("Keystore may not be null");
			}
			System.out.println("Initializing key manager");
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
			System.out.println("Initializing trust manager");
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
}
