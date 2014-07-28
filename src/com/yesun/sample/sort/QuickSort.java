package com.yesun.sample.sort;

import java.util.Arrays;

/**
 * sample QuickSort.java
 * Description:
 * 1.0 YESUN Aug 9, 2013 5:57:56 PM Create.
 * ChangeLog:
 */
public class QuickSort {
	
	public static int getMiddle(int[] a, int low, int high) {
		int tmp = a[low];//取出中间值，空出一个位置		
		while(low < high) {			
			//移动一个高位，再移动一个低位
			while(low < high && a[high] > tmp) {
				high--;
			}
			//找到高位小于中间值的，小的移动到低端
			a[low] = a[high];
			//移动一个低位
			while(low < high && a[low] < tmp) {
				low++;
			}
			a[high] = a[low];//由于上面移动了一个高位，a[high]必然空缺，小的值移动到高端
		}
		//都移动完了，中间的空缺就是中间值
		a[low] = tmp;
		return low;		
	}
	
	

	//递归形式的分治排序算法
	public static void quickSort(int[] a, int low, int high) {
		if(low < high) {
			System.out.println(low + " --- " + high);
			int middle = getMiddle(a, low, high);//将a排序
			//对低端排序
			quickSort(a, low, middle-1);
			//对高端排序
			quickSort(a, middle+1, high);
		}
		
	}
	
	
	public static void sort(int[] a) {
		if(a.length > 0) {
			quickSort(a, 0, a.length - 1);
		}
		
		
	}

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Aug 9, 2013 5:57:33 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		int[] a = new int[]{73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
		sort(a);
		System.out.println(Arrays.toString(a));
	}

}
