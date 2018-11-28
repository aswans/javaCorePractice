package com.aswans.classloader.jarloader.proxy;

import java.lang.reflect.Method;

import com.aswans.classloader.jarloader.common.Executor;

public class ExecutorProxy implements Executor {

	private String version;
	private StandardExecutorClassLoader classLoader;

	public ExecutorProxy(String version) {
		this.version = version;
		classLoader = new StandardExecutorClassLoader(version);
	}

	@Override
	public void execute(String name) {
		try {
			// Load ExecutorProxy class
			Class<?> executorClazz = classLoader.loadClass("com.aswans.classloader.jarloader.impl.ExecutorImp");

			Object executorInstance = executorClazz.newInstance();
			System.out.println(executorInstance.getClass().getClassLoader().toString());
			Method method = executorClazz.getMethod("execute", String.class);

			method.invoke(executorInstance, name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
