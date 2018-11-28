package com.aswans.classloader.hotdeploy1;

import java.lang.reflect.Method;

public class TestHotSwap {
	public static void main(String[] args) throws Exception {
		// 开启线程，如果class文件有修改，就热替换
		Thread t = new Thread(new MonitorHotSwap());
		t.start();
	}
}

class MonitorHotSwap implements Runnable {
	// Hot就是用于修改，用来测试热加载的目标类
	private String className = "com.aswans.classloader.hotdeploy1.Hot";
	private Class hotClazz = null;
	private HotSwapURLClassLoader hotSwapCL = null;

	@Override
	public void run() {
		try {
			while (true) {
				initLoad();
				// 如果Hot类被修改了，那么会重新加载，hotClass也会返回新的
				hotClazz = hotSwapCL.loadClass(className);
				//Object hot = hotClazz.newInstance();
				Object hot = hotClazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
				Method m = hotClazz.getMethod("hot");
				Method getAa = hotClazz.getMethod("getAa");
				m.invoke(hot, null); // 打印出相关信息
				Object obj = getAa.invoke(hot, null); // 
				System.out.println(obj.toString());
				// 每隔10秒重新加载一次
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载class
	 */
	void initLoad() throws Exception {
		hotSwapCL = HotSwapURLClassLoader.getClassLoader();
	}
}
