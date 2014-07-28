package com.yesun.sample.jetty.server;

import java.io.IOException;

import com.yesun.sample.jetty.util.component.LifeCycle;


public interface Connector extends LifeCycle {
	
	String getName();
	
	void open() throws IOException;
	
	void close() throws IOException;

	Object getConnection();
	
	void accept(int acceptorID) throws IOException;
	
	void setPort(int port);
	void setServer(Server server);
}
