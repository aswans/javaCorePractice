package com.aswans.classloader;

public class Static_Block {
	private static String name = null;
	private static int age=50;
	static {
		System.out.println(age);
		name = "zhangsj";
		age = 30;
		System.out.println(age);
	}

	/**
	 * @param args
	 * @author zhangsj add 2015-4-20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testBlock();
	}

	public static void testBlock() {
		System.out.println(name);
		System.out.println(age);
	}
}
