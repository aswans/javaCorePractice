package com.aswans.classloader.jarloader.common;

public abstract class AbstractExecutor implements Executor {

	@Override
	public void execute(final String name) {
		// TODO Auto-generated method stub
		this.handle(new Handler() {
			@Override
			public void handle() {
				System.out.println("V:" + name);
			}
		});
	}

	protected void handle(Handler handler) {
		handler.call();
	}

	protected abstract class Handler {
		public void call() {
			ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
			System.out.println(oldClassLoader.toString());
			System.out.println(AbstractExecutor.class.getClassLoader().toString());
			// 临时更改 ClassLoader
			Thread.currentThread().setContextClassLoader(AbstractExecutor.class.getClassLoader());

			handle();

			// 还原为之前的 ClassLoader
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		public abstract void handle();
	}
}
