package com.yesun.sample.sort;

/**
 * sample BubbleSort.java
 * Description:冒泡
 * 1.0 YESUN Jul 30, 2013 4:40:10 PM Create.
 * ChangeLog:
 */
public class BubbleSort {

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Jul 30, 2013 4:20:25 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		
		int[] a = new int[]{9,3,7,5,4,8,1,2,6};
		sort(a);
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}
	
	
	public static void sort(int[] a) {
		for(int i = a.length - 1; i > 0; i--){
			for(int j = 0; j < i; j++){//共循环36次
				if(a[j] > a[j+1]){
					int swap = a[j];
					a[j] = a[j+1];
					a[j+1] = swap;				
				}
			}
		}
	}

}
