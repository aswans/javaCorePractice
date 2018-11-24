package com.aswans.classloader.custom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader {
	public MyClassLoader() {

	}

	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File classFile = new File("D:\\People.class");
		try {
			byte[] bytes = getClassBytes(classFile);
			return defineClass(name, bytes, 0, bytes.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}

	private byte[] getClassBytes(File file) throws Exception {
		// 这里要读入.class的字节，因此要使用字节流
		/*
		 * FileInputStream fis = new FileInputStream(file); FileChannel fc =
		 * fis.getChannel(); ByteArrayOutputStream baos = new
		 * ByteArrayOutputStream(); WritableByteChannel wbc =
		 * Channels.newChannel(baos); ByteBuffer by = ByteBuffer.allocate(1024);
		 * 
		 * while (true) { int i = fc.read(by); if (i == 0 || i == -1) break;
		 * by.flip(); wbc.write(by); by.clear(); } fis.close(); return
		 * baos.toByteArray();
		 */
		/***
		 * 非nio读取方法
		 */

		FileInputStream fis1 = new FileInputStream(file);
		byte[] bytes = new byte[fis1.available()];
		/*
		 * byte[] bytes = new byte[1024]; 
		 * int tempbyte;
		 * System.out.println("以字节为单位读取文件内容，一次读一个字节："); 
		 * while((tempbyte = fis1.read())!=-1){
		 *    System.out.write(tempbyte); 
		 * }
		 */
		fis1.read(bytes, 0, bytes.length);
		fis1.close();
		return bytes;

	}

}
