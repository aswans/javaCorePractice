package com.aswans.reflect.demo1;

public abstract class OperationDao {
	public <T> void insert(Class<T> ss){
    	System.out.println(ss.getName());
    	String aa ="ddd";
    	byte[] bb = aa.getBytes();
    }
}
