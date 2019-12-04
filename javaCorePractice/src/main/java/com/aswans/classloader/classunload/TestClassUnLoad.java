package com.aswans.classloader.classunload;
/**
 * @desc  
 * 在Java中class也是可以unload。JVM中class和Meta信息存放在PermGen space区域。
 * 如果加载的class文件很多，那么可能导致PermGen space区域空间溢出。
 * 引起：java.lang.OutOfMemoryErrorPermGen space.  
 * 对于有些Class我们可能只需要使用一次，就不再需要了，也可能我们修改了class文件，
 * 我们需要重新加载 newclass，那么oldclass就不再需要了。那么JVM怎么样才能卸载Class呢。
   JVM中的Class只有满足以下三个条件，才能被GC回收，也就是该Class被卸载（unload）：
   - 该类所有的实例都已经被GC。
   - 加载该类的ClassLoader实例已经被GC。
   - 该类的java.lang.Class对象没有在任何地方被引用。

    GC的时机我们是不可控的，那么同样的我们对于Class的卸载也是不可控的。 
 
 * @zsj zsj add 2018年11月27日 下午3:45:23
 */
public class TestClassUnLoad {
    /**
     * @desc  在IDE的RUN Configurations中设置VM参数-verbose:class，
     * 可以查看类的加载和卸载信息
     * @author zsj add 2018年11月27日 下午4:06:28
     * @param args
     * @throws Exception
     */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SimpleURLClassLoader loader = new SimpleURLClassLoader();
		// 用自定义的加载器加载A
		Class clazzDog = loader.load("com.aswans.classloader.classunload.Dog");
		Object dog = clazzDog.newInstance();
		// 清除相关引用
		dog = null;
		clazzDog = null;
		loader = null;
		//执行一次gc垃圾回收
		System.gc();
		System.out.println("GC over");
	}

}
