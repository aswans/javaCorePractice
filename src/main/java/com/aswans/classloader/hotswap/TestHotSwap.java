package com.aswans.classloader.hotswap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
 /**
  * @desc  HotSwapClassLoader加载器的作用是重新加载同名的类。
  * 为了实现hot swap，一个类在加载过后，若重新再加载一次，则新的Class object的状态会改变，
  * 老的状态数据需要通过其他方式拷贝到重新加载过的类生成的全新Class object实例中来。
  * 上面A类引用了B类，加载A时也会加载B（如果B已经加载，则直接从缓存中取出）。
  * 在重新加载A后，其Class object中的成员b会重置，因此要重新调用setB(b)拷贝一次。
  * 你可以注释掉这行代码，再运行会抛出java.lang.NullPointerException，指示A.b为null。
　  * 注意新的A Class object实例所依赖的B类Class object，
  * 如果它与老的B Class object实例不是同一个类加载器加载的， 将会抛出类型转换异常(ClassCastException)，表示两种不同的类。
  * 因此在重新加载A后，要特别注意给它的B类成员b传入外部值时，它们是否由同一个类加载器加载。
  * 为了解决这种问题， HotSwapClassLoader自定义的l/oad方法中，当前类（类A）是由自身classLoader加载的，
  * 而内部依赖的类（类B）还是老对象的classLoader加载的。
  * @zsj zsj add 2018年11月27日 下午4:09:00
  */
public class TestHotSwap {
 
    public static void main(String args[]) throws MalformedURLException {
    	//获取系统类加载器
    	ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
    	TestHotSwap.class.getResource("/");
    	TestHotSwap.class.getClassLoader().getResource("");
    	Thread.currentThread().getContextClassLoader();
    	Thread.currentThread().setContextClassLoader(null);
    	
        A a = new A();  // 加载类A
        B b = new B();  // 加载类B
        a.setB(b);  // A引用了B，把b对象拷贝到A.b
        System.out.printf("A classLoader is %s\n", a.getClass().getClassLoader());
        System.out.printf("B classLoader is %s\n", b.getClass().getClassLoader());
        System.out.printf("A.b classLoader is %s\n", a.getB().getClass().getClassLoader());
 
        try {
            URL[] urls = new URL[]{ new URL("file:///D:/workspace-mars/javaCorePractice/target/classes/") };
            HotSwapClassLoader c1 = new HotSwapClassLoader(urls, a.getClass().getClassLoader());
            Class clazz = c1.load("com.aswans.classloader.hotswap.A");  // 用hot swap重新加载类A
            Object aInstance = clazz.newInstance();  // 创建A类对象
            Method method1 = clazz.getMethod("setB", B.class);  // 获取setB(B b)方法
            method1.invoke(aInstance, b);    // 调用setB(b)方法，重新把b对象拷贝到A.b
            Method method2 = clazz.getMethod("getB");  // 获取getB()方法
            Object bInstance = method2.invoke(aInstance);  // 调用getB()方法
            System.out.printf("Reloaded A classLoader is %s\n", aInstance.getClass().getClassLoader());
            System.out.printf("Reloaded A.b classLoader is %s\n", bInstance.getClass().getClassLoader());
            
        } catch (MalformedURLException | ClassNotFoundException | 
                InstantiationException | IllegalAccessException | 
                NoSuchMethodException | SecurityException | 
                IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
