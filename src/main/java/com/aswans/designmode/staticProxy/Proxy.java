package com.aswans.designmode.staticProxy;

public class Proxy implements Subject {
    private Subject subject;
    public Proxy(Subject subject){
    	this.subject = subject;
    }
	@Override
	public void request() {
		// TODO Auto-generated method stub
		subject.request();
	}

}
