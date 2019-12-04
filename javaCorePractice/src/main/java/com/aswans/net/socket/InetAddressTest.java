package com.aswans.net.socket;

import java.net.InetAddress;
import java.net.URL;

public class InetAddressTest {

	/**@throws UnknownHostException 
	 * @throws MalformedURLException 
	 * @功能
	 * @参数 @param args
	 * @作者 zhangsanjie add 2018-10-15 上午11:14:02
	 * @返回值类型 void
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//获取本机的InetAddress实例
		InetAddress address =InetAddress.getLocalHost();
		address.getHostName();//获取计算机名
		address.getHostAddress();//获取IP地址
		byte[] bytes = address.getAddress();//获取字节数组形式的IP地址,以点分隔的四部分

		//获取其他主机的InetAddress实例
		//InetAddress address2 =InetAddress.getByName("其他主机名");
		//InetAddress address3 =InetAddress.getByName("IP地址");
		
		//创建一个URL的实例
		URL baidu =new URL("http://www.baidu.com");
		URL url =new URL(baidu,"/index.html?username=tom#test");//？表示参数，#表示锚点
		System.out.println(url.getProtocol());//获取协议
		System.out.println(url.getHost());//获取主机
		System.out.println(url.getPort());//如果没有指定端口号，根据协议不同使用默认端口。此时getPort()方法的返回值为 -1
		System.out.println(url.getPath());//获取文件路径
		System.out.println(url.getFile());//文件名，包括文件路径+参数
		System.out.println(url.getRef());//相对路径，就是锚点，即#号后面的内容
		System.out.println(url.getQuery());//查询字符串，即参数
	}

}
