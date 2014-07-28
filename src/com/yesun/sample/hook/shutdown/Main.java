package com.yesun.sample.hook.shutdown;

public class Main {

	public static void main(String[] args) {

		System.out.println("start...");
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		System.out.println("close JVM..");
		System.exit(0);
	}

}
