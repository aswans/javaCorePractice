package com.aswans.classloader.hotdeploy;

public class Worker {
	public Worker() {
		System.out.println("===========ttttttttttttttttt=========");
		System.out.println("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>【重磅消息】我被构造了");
	}

	public void doit() {
		System.out.println(this.getClass().getClassLoader().toString() + "--->----------------->9999  222");
	}
}
