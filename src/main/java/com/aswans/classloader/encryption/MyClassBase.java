package com.aswans.classloader.encryption;

public class MyClassBase implements MyClassInterface {
	@Override
	public void say() {
		System.out.append("Hello World!");
	}
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
}
