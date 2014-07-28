package com.yesun.sample.sort;
//package com.dheaven.sample.sort;
//
//import java.util.Arrays;
//
///**
// * sample HeapSort.java
// * Description: 堆排序：将线性表转换为堆（二叉树），然后再对堆排序。
// * 某个元素i的子节点为2*i+1，右节点为2*i+2,没调通
// * 1.0 YESUN Aug 9, 2013 4:37:01 PM Create.
// * ChangeLog:
// */
//public class HeapSort {
//	
//	public static int left(int i){
//		return 2*i + 1;
//	}
//	
//	public static int right(int i){
//		return 2*i + 2;
//	}
//	
//	public static void adjustHeap(int[] a, int i, int len) {
//		int left = left(i);
//		int right = right(i);
//		int largest;
//		int tmp;
//		
//		//找到当前堆中最大值largest
//		if(left < len && a[left] > a[i]) {
//			largest = left;
//		}
//		else {
//			largest = i;
//		}
//		if(right < len && a[right] > a[largest]){
//			largest = right;
//		}
//		
//		if(largest != i) {
//			//如果堆顶不是最大值，则交换
//			tmp = a[i];
//			a[i] = a[largest];
//			a[largest] = tmp;
//			adjustHeap(a, largest, a.length);//递归修正堆		
//		}
//	}
//
//
//	public static void buildHeap(int[] a) {
//		int len = a.length;
//		int i;
//		int begin = len / 2 - 1;//最后一个非叶子节点
//		for(i = begin; i >= 0; i--) {
//			adjustHeap(a, i, a.length);
//		}
//	}
//	
//
//	public static void sort(int[] a) {
//		
//		int len = a.length;
//		int tmp;
//		
//		buildHeap(a);//建堆
//		
//		while(len > 1) {
//			tmp = a[len-1];//交换堆的第一个元素和最后一个元素
//			a[len-1] = a[0];
//			a[0] = tmp;
//			len--;//堆大小减一，然后修正堆
//			adjustHeap(a,0, a.length);
//		}
//		
//		
//	}
//
//	/**
//	 * Description:
//	 * @param args
//	 * 1.0 YESUN Aug 9, 2013 4:36:31 PM Create.
//	 * ChangeLog:
//	 */
//	public static void main(String[] args) {
//		int[] a = new int[]{73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
//		sort(a);
//		System.out.println(Arrays.toString(a));
//
//	}
//
//}
