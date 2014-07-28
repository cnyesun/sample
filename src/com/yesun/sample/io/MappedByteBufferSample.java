package com.yesun.sample.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * sample MappedByteBufferSample.java
 * Description: 1.5G的需要81秒
 * Copyright (c) EDZH.COM All Rights Reserved.
 * @version 1.0 YESUN 2014年5月27日 Create.
 * ChangeLog:
 */
public class MappedByteBufferSample {

	public static void main(String[] args) throws IOException {
		
		long start = System.currentTimeMillis();
		
		FileChannel reader = new FileInputStream("D:\\soft\\develop\\VSProEdition.zip").getChannel();
		FileChannel writer = new RandomAccessFile("D:\\soft\\develop\\VSProEdition.copy.zip","rw").getChannel();

		ByteBuffer readerBuffer, writerBuffer = null;
		long i = 0;
		long size = reader.size() / 30;
		while(i < reader.size() && (reader.size() - i) > size) {
			readerBuffer = reader.map(MapMode.READ_ONLY, i, size);
			writerBuffer = writer.map(MapMode.READ_WRITE, i, size);
			writerBuffer.put(readerBuffer);
			i+=size;
			
			readerBuffer.clear();
			writerBuffer.clear();
		}
		
		readerBuffer = reader.map(MapMode.READ_ONLY, i, reader.size()-i);
		writerBuffer.put(readerBuffer);
		readerBuffer.clear();
		writerBuffer.clear();
		reader.close();
		writer.close();
		
		System.out.println((System.currentTimeMillis() - start) + "ms");
		
		final MappedByteBuffer buffer  = (MappedByteBuffer) readerBuffer;
		AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                try {
                    Method clean = buffer.getClass().getMethod("cleaner", new Class[0]);

                    if (clean == null) {
                        return null;
                    }

                    clean.setAccessible(true);

                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) clean.invoke(buffer, new Object[0]);

                    cleaner.clean();
                } catch (Throwable ex) {
                }

                return null;
            }
        });

	}

}
