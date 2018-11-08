package com.aswans.reflect.demo1;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.aswans.bean.Person;

public class IntrospectorTest {

	/**
	 * @param args
	 * @author zhangsj add 2015-6-9
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// java的内省机制
		try{
			Class cls = Class.forName("com.zsj.domain.Person");
			BeanInfo beanInfo = Introspector.getBeanInfo(cls, cls.getSuperclass());
			// 停用的类
			Person o = (Person) cls.newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (propertyDescriptors != null && propertyDescriptors.length > 0) {
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					String propertyName = propertyDescriptor.getName();
					Method writeMethod = propertyDescriptor.getWriteMethod();
					Method readMethod = propertyDescriptor.getReadMethod();
					if ("name".equalsIgnoreCase(propertyName)) {
						writeMethod.invoke(o, "zhangsj");
					}
					if ("age".equalsIgnoreCase(propertyName)) {
						writeMethod.invoke(o, "12");
					}
					if("shenGao".equalsIgnoreCase(propertyName)){
						writeMethod.invoke(o, "15");
					}
					System.out.println(readMethod.invoke(o).toString());
				}
			}

			// java的反射机制
			Field[] fields = cls.getFields();
			Field[] declaredFields = cls.getDeclaredFields();
			Person person = (Person) cls.newInstance();
			person.setName("zhangsj");
			person.setAge("23");
			person.setShenGao("56");
			for (Field field : declaredFields) {
				Method method = cls.getMethod("get"+ field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1));
				System.out.println(method.invoke(person).toString());
			}
			for (Field field : fields) {
				Method method = cls.getMethod("get"+ field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1));
				System.out.println(method.invoke(person).toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//per person指向同一块内存地址
		/*Person person = new Person();
		person.setName("zhang");
		Person per = person;
		System.out.println(per.getName());
		person.setName("li");
		System.out.println(per.getName());*/

	}

}
