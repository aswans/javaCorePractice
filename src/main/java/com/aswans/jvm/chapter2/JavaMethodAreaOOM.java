package com.aswans.jvm.chapter2;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * -verbose:gc -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails
 * @author marker
 *
 */
public class JavaMethodAreaOOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}

	}

	static class OOMObject {

	}

}
