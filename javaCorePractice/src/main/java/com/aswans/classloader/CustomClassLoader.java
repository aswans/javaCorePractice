package com.aswans.classloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;

public class CustomClassLoader extends ClassLoader{
	public static void main(String[] args) {
		System.out.println(File.separatorChar);
		System.out.println(File.separator);
	}
}


class CustomClassLoader1 extends SecureClassLoader{
	
}


class CustomClassLoader2 extends URLClassLoader{

	public CustomClassLoader2(URL[] urls) {
		super(urls);
		// TODO Auto-generated constructor stub
	}

}

