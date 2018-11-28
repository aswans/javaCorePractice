package com.aswans.classloader.hotdeploy1;

public class Hot {
	private int aa = 55;
	
	
    public Hot(){
    	
    }
	public void hot() {
		System.out.println(" version 5 : " + this.getClass().getClassLoader());
	}
	public int getAa() {
		return aa;
	}
	public void setAa(int aa) {
		this.aa = aa;
	}
	
}
