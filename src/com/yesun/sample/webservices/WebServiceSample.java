package com.yesun.sample.webservices;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class WebServiceSample {
	
	
	/**
	 * @return
	 */
	public String getArea(){
		return "北京";
	}
	
	
	
	public static void main(String[] args){
		Endpoint.publish("http://localhost:8080/WebServiceSample", new WebServiceSample());
	}

}
