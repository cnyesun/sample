package com.yesun.sample.loop;

public class LoopSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		long startMem = Runtime.getRuntime().freeMemory();
		System.out.println("开始剩余内存：" + startMem);
		
		int[] array1 = new int[200000];
		for (int i=0; i<array1.length; i++){
			array1[i] = i;
		}
		int[] array2 = new int[200000];
		for (int i=0; i<array2.length; i++){
			array2[i] = array1[i];
		}
		System.arraycopy(array1, 0, array2, 0, 200000);
		
		int usernaem=33333333;
		
		long endTime = System.currentTimeMillis();
		long endMem = Runtime.getRuntime().freeMemory();
		System.out.println("用时：" + (endTime - startTime) + "ms");
		System.out.println("结束剩余内存：" + endMem);
		System.out.println("使用内存：" + (startMem - endMem));
	}

}
