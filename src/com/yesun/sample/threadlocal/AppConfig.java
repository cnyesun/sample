package com.yesun.sample.threadlocal;

import org.dom4j.Document;

/**
 * sample Config.java
 * Description: App配置文件，每一个应用对应一个配置文件
 * Copyright (c) EDZH.COM All Rights Reserved.
 * @version 1.0 YESUN 2013年11月30日 Create.
 * ChangeLog:
 */
public class AppConfig {
	
	private String appid;
	private Document document;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	
	public AppConfig(String appid) {
		this.appid = appid;
	}
}
