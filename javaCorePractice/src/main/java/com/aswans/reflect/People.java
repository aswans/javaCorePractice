package com.aswans.reflect;

public class People {
	/**
	  * 姓名
	  */
	 private String name;
	 /**
	  * 性别
	  */
	 private String sex;
	 /**
	  * 年龄
	  */
	 private int age;
	 /**
	  * 地址
	  */
	 private String address;
	 /**
	  * 学校
	  */
	 public String school;
	 /**
	  * 公司
	  */
	 private Company company;
	 /**
	  * 金额
	  */
	 private Double money;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}

}
