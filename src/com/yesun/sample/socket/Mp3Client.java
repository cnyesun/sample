package com.yesun.sample.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Mp3Client {

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2012-3-27 上午8:59:54 Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		for(int i=0;i<3;i++){
			Client client = new Client();
			client.i = i;
			client.start();
		}

	}

}


class Client extends Thread{
	
	int i=0;
	public void run(){
		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 9999);
			OutputStream os = socket.getOutputStream();
			os.write("c:\\1.mp3".getBytes("UTF-8"));
			String filename = "c://download_"+ i +".mp3";
			FileOutputStream foStream = new FileOutputStream(filename);
			InputStream is = socket.getInputStream();
			byte[] buffer = new byte[1024];
			while(is.read(buffer) > 0){
				foStream.write(buffer);
			}
			os.close();
			is.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}



