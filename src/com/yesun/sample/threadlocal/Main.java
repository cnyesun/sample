package com.yesun.sample.threadlocal;

public class Main {
	

	/**
	 * Description: ThreadLocal主要使用场景
	 * 1、多线程环境下操作相同的对象，可采用ThreadLocal，让每个线程copy出一个对象，操作时互不干扰
	 * 2、同一个线程下，不同的代码中共享数据
	 * @version 1.0 YESUN 2013年11月30日 Create.
	 * @param args
	 */
	public static void main(String[] args) {

		
		//场景一
//		final ThreadLocal<AppConfig> localConfig = new ThreadLocal<AppConfig>(){
//
//			@Override
//			protected AppConfig initialValue() {
//				//有必要可以自己初始化，此场景其实没必要初始化
//				return new AppConfig("NULL");
//			}
//			
//		};
//		
//		
//		AppConfig config = new AppConfig("APP1");
//		localConfig.set(config);//存入对象
//		
//		//启动多个线程
//		for(int i = 0; i < 10; i++){
//			Thread t = new Thread(new Runnable(){
//
//				@Override
//				public void run() {
//
//					//每个线程使用config，并操作，互不干扰
//					AppConfig config = localConfig.get();
//					System.out.println(config);//print
//					config.setAppid(Thread.currentThread().getName());
//				}
//				
//			});
//			t.start();
//		}
//		
//		/* print结果，每个线程中的config都不一样，对config的操作互不影响
//		 	com.dheaven.sample.threadlocal.AppConfig@7d29f3b5
//			com.dheaven.sample.threadlocal.AppConfig@4d3f3045
//			com.dheaven.sample.threadlocal.AppConfig@6f7a29a1
//			com.dheaven.sample.threadlocal.AppConfig@3d434234
//			com.dheaven.sample.threadlocal.AppConfig@aaf8358
//			com.dheaven.sample.threadlocal.AppConfig@8d80be3
//			com.dheaven.sample.threadlocal.AppConfig@1ff4689e
//			com.dheaven.sample.threadlocal.AppConfig@67006d75
//			com.dheaven.sample.threadlocal.AppConfig@4d125127
//			com.dheaven.sample.threadlocal.AppConfig@6d8dfef8
//		 */
		
		
		
		
		//场景二，同一个线程中做数据共享，即实现在线程内的全局变量，而非多线程全局变量
		
		final AppConfig config = new AppConfig("APP1");
		//启动多个线程
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadLocalConfig.setConfig(config);
				new MyService().work();
				//...
			}
		});
		t.start();
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				ThreadLocalConfig.setConfig(config);
				new MyService().work();
				//...
			}
		});
		t2.start();
		
	}

}
