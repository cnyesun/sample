package com.yesun.sample.sort;

public class InsertSort {

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Jul 30, 2013 4:59:53 PM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		int[] a = new int[]{9,3,7,5,4,8,1,2,6};
		sort(a);
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}
	
	
	
	public static void sort(int a[]){
		int length=a.length; //数组长度
		int j;				 //当前值的位置
		int i;				 //指向j前的位置
		int key;			 //当前要进行插入排序的值
		//从数组的第二个位置开始遍历值
		for(j=1;j<length;j++){
			key=a[j];
			i=j-1;
			//a[i]比当前值大时，a[i]后移一位,空出i的位置，好让下一次循环的值后移
			while(i>=0 && a[i]>key){
				a[i+1]=a[i]; //将a[i]值后移
				i--;   		 //i前移
			}//跳出循环(找到要插入的中间位置或已遍历到0下标)
			a[i+1]=key;    //将当前值插入
		}
	}

}
