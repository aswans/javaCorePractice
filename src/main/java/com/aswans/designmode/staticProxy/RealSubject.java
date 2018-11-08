package com.aswans.designmode.staticProxy;

public class RealSubject implements Subject {

	@Override
	public void request() {
		// TODO Auto-generated method stub
        System.out.println("realSubject is run");
	}

}
