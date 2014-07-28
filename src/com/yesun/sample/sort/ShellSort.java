package com.yesun.sample.sort;

import java.util.Arrays;

/**
 * sample ShellSort.java
 * Description: 希尔排序，又称为增量递减排序，第一次增增量为a.lenght/2，以后每次除2，直到大于0
 * 1.0 YESUN Aug 9, 2013 3:52:46 PM Create.
 * ChangeLog:
 */
public class ShellSort {
	
	public static void sort(int[] a) {
		int length = a.length;

		int d  = length / 2;
		int i, j;
		int tmp;
		while(d >= 1) {
			System.out.println("d = " + d);
			System.out.println("before sort:" + Arrays.toString(a));
			for(i = d; i < length; i++) {	
				j = i - d;//分组1中的对应元素下标
				tmp = a[i];//分组2中的元素
				while(j >= 0 && a[j] > tmp){////当前元素a[i]除了要和a[i-d]比较，还需要和a[i-d-d]比较，即和前面所有每个间隔d的元素比较，目的是要将最小元素tmp放到最前面
					a[j + d] = a[j];
					j = j - d;
				}
				a[j + d] = tmp;
			}

			System.out.println("after sort:"+Arrays.toString(a));
			d = d/2;			
		}
	}

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Aug 9, 2013 3:30:30 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		int[] a = new int[]{73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
		sort(a);
		System.out.println(Arrays.toString(a));
	}

}
