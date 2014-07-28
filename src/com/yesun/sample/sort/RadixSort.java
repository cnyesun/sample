package com.yesun.sample.sort;

import java.util.Arrays;

public class RadixSort {
	
	
	/**
	 * Description:原理：先对数据进行低位（个位）排序，存放到桶里tmp[][]，然后顺序从桶里拿出，更新到a[]；然后高位排序(x/10) % 10，做法一样，debug跟断点可看清楚本质
	 * @param a
	 * @param d
	 * 1.0 YESUN Aug 7, 2013 4:54:13 PM Create.
	 * ChangeLog:
	 */
	public static void sort(int[] a, int d) {
		int num = a.length;
		int k = 0, n = 1, m = 1;
		int[][] tmp = new int[num][num];
		int[] order = new int[num];
		
		while(m <= d) {
			for(int i = 0; i < num; i++) {
				int lsd = ((a[i] / n) % 10);
				tmp[lsd][order[lsd]] = a[i];
				order[lsd]++;//记录某个桶内存放的数据个数
			}

			
			for(int i = 0; i < order.length; i++) {
				if(order[i] != 0)   
					for(int j = 0; j < order[i]; j++) {   
						a[k] = tmp[i][j];   
						k++;   
				}   
				order[i] = 0;   
			}   
			n *= 10;
			k = 0;
			m++; 
		}
	}

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Aug 3, 2013 4:03:52 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		int[] a = new int[]{73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
		sort(a, 3);//a为要排序的数组，3为数据位数，3表示百位
		System.out.println(Arrays.toString(a));
	}

}
