package com.yesun.sample.sort;

import java.util.Arrays;

/**
 * sample MergeSort.java
 * Description:稳定的排序，但是消耗内存
 * 1.0 YESUN Aug 3, 2013 4:03:28 PM Create.
 * ChangeLog:
 */
public class MergeSort {
	
	
	public static final int CUTOFF = 11;
	
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr ) {
		T[] tmpArr = (T[]) new Comparable[arr.length];
		mergeSort(arr, tmpArr, 0, arr.length - 1);
	}
	
	private static <T extends Comparable<? super T>> void mergeSort( T[] arr, T[] tmpArr, int left, int right ) {
		System.out.println("left :" + left + "right: " + right);
            //recursive way
            if ( left < right ) {
                    int center = ( left + right ) / 2;
                    mergeSort(arr, tmpArr, left, center);
                    mergeSort(arr, tmpArr, center + 1, right);


            		System.out.println("move > " + (right - center - 1));
//            		System.out.println("merge > lpos:" + left + "rpos: " + (center +1) +" rend:" + right);
                    merge(arr, tmpArr, left, center + 1, right);
            }
    } 

	static int k = 1 ;
    private static <T extends Comparable<? super T>> void merge( T[] arr, T[] tmpArr, int lPos, int rPos, int rEnd ) {
    	
//    	System.out.println(Arrays.toString(tmpArr));
            int lEnd = rPos - 1;
            int tPos = lPos;
            int leftTmp = lPos;

            while ( lPos <= lEnd && rPos <= rEnd  ) {
            	System.out.println("第"+k+"次比较:" +arr[lPos]  + " & " + arr[rPos]);
            	
                    if ( arr[lPos].compareTo( arr[rPos] ) <= 0 )
                            tmpArr[ tPos++ ] = arr[ lPos++ ];
                    else 
                            tmpArr[ tPos++ ] = arr[ rPos++ ];
            }

            //copy the rest element of the left half subarray.
            while ( lPos <= lEnd ) 
                    tmpArr[ tPos++ ] = arr[ lPos++ ];
            //copy the rest elements of the right half subarray. (only one loop will be execute)
            while ( rPos <= rEnd ) 
                    tmpArr[ tPos++ ] = arr[ rPos++ ];

            //copy the tmpArr back cause we need to change the arr array items.
            for ( ; rEnd >= leftTmp; rEnd-- )
                    arr[rEnd] = tmpArr[rEnd];
            
            k++;
            

//        	System.out.println("arr:"+Arrays.toString(arr));
    }

	

	
	
	
	
	
	
	
	
	

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Aug 3, 2013 2:29:04 PM Create.
	 * ChangeLog:
	 * 
	 	move > 0
		第1次比较:8 & 6
		move > 0
		第2次比较:6 & 3
		move > 0
		第3次比较:2 & 9
		move > 1
		第4次比较:3 & 2
		第4次比较:3 & 9
		第4次比较:6 & 9
		第4次比较:8 & 9
		move > 0
		第5次比较:5 & 4
		move > 0
		第6次比较:1 & 7
		move > 1
		第7次比较:4 & 1
		第7次比较:4 & 7
		第7次比较:5 & 7
		move > 3
		第8次比较:2 & 1
		第8次比较:2 & 4
		第8次比较:3 & 4
		第8次比较:6 & 4
		第8次比较:6 & 5
		第8次比较:6 & 7
		第8次比较:8 & 7
		[1, 2, 3, 4, 5, 6, 7, 8, 9]
	 * 
	 */
	public static void main(String[] args) {
		Integer[] a = new Integer[]{8,6,3,2,9,5,4,1,7};
		mergeSort(a);
		System.out.println(Arrays.toString(a));

	}

}
