package com.aswans.classloader.jarloader.impl;

import com.aswans.classloader.jarloader.common.AbstractExecutor;

public class ExecutorImp extends AbstractExecutor {

	@Override
	public void execute(final String name) {
		// TODO Auto-generated method stub
		this.handle(new Handler() {
			@Override
			public void handle() {
				//区分不同版本jar，打印不同的信息
				//System.out.println("V1:" + name);
				System.out.println("V2:" + name);
			}
		});
	}

}
