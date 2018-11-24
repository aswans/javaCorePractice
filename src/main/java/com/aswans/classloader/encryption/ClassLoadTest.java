package com.aswans.classloader.encryption;

import java.io.File;

/**
 * @desc用classloader做类文件的加密
 * @author guoyahao add 2018年11月23日 下午1:18:44
 */
public class ClassLoadTest {
	public static void main(String[] args) throws Exception {
		ClassLoader loader = new ClassLoader() {
			@Override
			public Class<?> findClass(String name) {

				System.out.println("findClass() name = " + name);

				String baseName = name.substring(name.lastIndexOf('.') + 1);

				//String[] fileNameElements = { System.getProperty("user.dir"), "cipher",
						//"Cipher" + baseName + ".class" };
				String filePath = System.getProperty("user.dir") + File.separator + "cipher"+
						File.separator + "Cipher" + baseName + ".class";
				//jdk1.8的api
				//String.join(File.separator, fileNameElements) ;  
				byte[] data = MyCipher.deCihperClass(filePath);
				Class<?> clz = defineClass(name, data, 0, data.length);
				return clz;
			}
		};
		Class<?> clz = loader.loadClass("com.aswans.classloader.encryption.MyClassBase");
		System.out.println("loaded class:" + clz.getName() + " by " + clz.getClassLoader());
		MyClassInterface obj = (MyClassInterface) clz.newInstance();
		obj.say();
	}
}
