package com.aswans.reflect.demo1;

import com.aswans.bean.Person;

public class PersonDao extends OperationDao{

	@Override
	public <T> void insert(Class<T> ss) {
		// TODO Auto-generated method stub
		Person person = new Person();
		super.insert(person.getClass());
	}

}
