package com.yesun.sample.nio;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket socket = null;
		try {
			 socket = new Socket("10.2.10.214", 8888);
			 Writer out = new OutputStreamWriter(socket.getOutputStream());
			 Date now = new Date();
			 out.write(now.toString() + "\r\n");
			 out.flush();	
		}
		catch (UnknownHostException ex) {
			System.err.println(ex);
		}
		catch (IOException ex) {
			System.err.println(ex);
		}
		finally{
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}			
		}
		
	}

}
