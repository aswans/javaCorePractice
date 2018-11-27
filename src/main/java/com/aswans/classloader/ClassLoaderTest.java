package com.aswans.classloader;

import java.io.InputStream;

public class ClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		/**
		 * 直接覆盖loadClass方法的做法，不推荐使用，会破坏双亲委派模型
		 * 导致同名类（类的全路径）可以多次加载
		 */
		ClassLoader myclassLoader = new ClassLoader() {
			public Class <?> loadClass(String name) throws  ClassNotFoundException{
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
		};
		
		Class classz = myclassLoader.loadClass("com.aswans.classloader.ClassLoaderTest");
		Object obj = classz.newInstance();
		System.out.println(obj.getClass().getClassLoader());
		ClassLoaderTest obj1 = new ClassLoaderTest();
		System.out.println(obj1.getClass().getClassLoader());
		//ClassLoaderTest默认由应用程序类加载器加载，而obj是由自定义加载器加载，所以obj不是应用程序类加载器加载的这个类的实例
		System.out.println(obj instanceof com.aswans.classloader.ClassLoaderTest);
		System.out.println(obj1 instanceof com.aswans.classloader.ClassLoaderTest);
		
	}

}
