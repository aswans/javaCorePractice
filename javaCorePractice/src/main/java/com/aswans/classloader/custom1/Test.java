package com.aswans.classloader.custom1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		CustomClassLoader classL = new CustomClassLoader();
		Class class_Stu = classL.loadClass("com.aswans.classloader.custom1.Student");
		Method method = class_Stu.getMethod("getAge", null);
		Method setmethod = class_Stu.getMethod("setAge",String.class);
		Object obj = class_Stu.newInstance();
		setmethod.invoke(obj, "22");
		Object obj1  = method.invoke(obj, null);
		System.out.println(obj1.toString());
		System.out.println(class_Stu.newInstance().getClass().getClassLoader());
	}

}
