package com.aswans.designmode.staticProxy;

public class ProxyTest {

	/**@功能
	 * @参数 @param args
	 * @作者 zsj add 2017-7-7 下午5:31:24
	 * @返回值类型 void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Subject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.request();
	}

}
