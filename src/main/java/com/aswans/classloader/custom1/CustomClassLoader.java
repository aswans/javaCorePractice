package com.aswans.classloader.custom1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
    /**
     * @desc 这个方法有问题 
     * @author zsj add 2018年11月27日 上午9:54:32
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
	//@Override
	public Class<?> loadClass1(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		// name = name.substring(name.lastIndexOf(".")+1)+".class";
		File classFile = new File("D:\\Student.class");
		try {
			byte[] bytes = getClassBytes(classFile);
			Class<?> clas = defineClass(name, bytes, 0, bytes.length);
			return clas;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	/**
	 * 直接重写loadClass方法，破坏双亲委派模式
	 */
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		try{
			String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
			InputStream is = getClass().getResourceAsStream(fileName);
			if(is==null){
				return super.loadClass(name);
			}
			byte[] bytes = new byte[is.available()];
			is.read(bytes);
			return defineClass(name, bytes, 0, bytes.length);
		}catch(Exception ex){
			//ex.printStackTrace();
			throw new ClassNotFoundException(name);
		}
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
		 * byte[] bytes = new byte[1024]; int tempbyte;
		 * System.out.println("以字节为单位读取文件内容，一次读一个字节："); while((tempbyte =
		 * fis1.read())!=-1){ System.out.write(tempbyte); }
		 */
		fis1.read(bytes, 0, bytes.length);
		fis1.close();
		return bytes;

	}
}
