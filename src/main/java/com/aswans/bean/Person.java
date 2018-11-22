package com.aswans.bean;

public class Person {
	private String name;
	private String age;
	private int count;
	private String shenGao;
	
	
	public String getShenGao() {
		return shenGao;
	}
	public void setShenGao(String shenGao) {
		this.shenGao = shenGao;
	}
	public Person(String personName,String personAge,int count,String shenGao){
        this.name = personName;
        this.age = personAge;
        this.count = count;
        this.shenGao = shenGao;
	}
	public Person(){
		
	}
    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
