package com.aswans.classloader.hotdeploy;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		HotClassLoader hostClassLoader = new HotClassLoader();
		//new Worker().doit();
		/**
		 * 由于自定义的类加载器没有重写loadClass方法，所以还会采用双亲委托模式去加载Worker类
		 */
		//Class hotClass = Class.forName("com.aswans.classloader.hotdeploy.Worker", true, hostClassLoader);
		Class hotClass = hostClassLoader.loadClass("com.aswans.classloader.hotdeploy.Worker");
		System.out.println(hotClass.newInstance().getClass().getClassLoader());
	}
}
