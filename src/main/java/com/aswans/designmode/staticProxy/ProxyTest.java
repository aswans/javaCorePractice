package com.aswans.designmode.staticProxy;

public class ProxyTest {

	/**
	 * @desc 测试主类
	 * @author zsj add 2018年11月8日 下午1:05:34
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Subject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.request();
	}

}
