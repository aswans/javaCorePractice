package com.aswans.classloader.encryption;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyCipher {
	public static void main(String[] args) {
		 
		String srcFilePath =  System.getProperty("user.dir")+File.separator
				+"target\\classes\\com\\aswans\\classloader\\encryption\\MyClassBase.class";
		//enCipherClass(String.join(File.separator, srcFileElement));
		enCipherClass(srcFilePath);
	}
    /**
     * @desc 异或加密 
     * @author zsj add 2018年11月24日 下午6:55:24
     * @param path
     * @return
     */
	public static String enCipherClass(String path) {
		File classFile = new File(path);
		if (!classFile.exists()) {
			System.out.println("File does not exist!");
			return null;
		}
 
		String cipheredClass = classFile.getParent() + File.separator + "Cipher" + classFile.getName();
		System.out.println("enCipherClass() cipheredClass=" + cipheredClass);
 
		try {
		    BufferedInputStream is = new BufferedInputStream(new FileInputStream(classFile));
		    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(cipheredClass));
 
			int data = 0;
			while ((data = is.read()) != -1) {
				out.write(data ^ 0xFF);
			}
 
			out.flush();
			is.close();
			out.close();
		} catch (IOException e) {
 
			e.printStackTrace();
		}
		return cipheredClass;
	}
    /**
     * @desc 异或解密 
     * @author zsj add 2018年11月24日 下午6:56:03
     * @param path
     * @return
     */
	public static byte[] deCihperClass(String path) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("deCihperClass() File:" + path + " not found!");
			return null;
		}
 
		System.out.println("deCihperClass() path=" + path);
 
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int data = 0;
			while ((data = in.read()) != -1) {
				out.write(data ^ 0xFF);
			}
			in.close();
			out.flush();
			out.close();
 
			return out.toByteArray();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		return null;
	}
}
