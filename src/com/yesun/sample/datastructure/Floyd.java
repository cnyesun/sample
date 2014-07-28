package com.yesun.sample.datastructure;



/**
 * sample Floyd.java
 * Description:Floyd算法，求所有点之间的最短路径
 * 1.0 YESUN Jul 20, 2013 4:29:35 PM Create.
 * ChangeLog:
 */
public class Floyd {
	
	static int MAX = 65535;
	
	public static void floyd(int[][] arcs){

		int num = arcs.length;
		
		for(int k = 0; k < num; k++) {
			for(int i = 0; i < num; i++) {
				for(int j = i + 1; j < num; j++) {
					if(i != j){
						System.out.println(i + " -> " + j + " " + arcs[i][j]);
						System.out.println(i + " -> " + k + " " + arcs[i][k]);
						System.out.println(k + " -> " + j + " " + arcs[k][j]);
						
						if(arcs[i][j] > (arcs[i][k] + arcs[k][j])) {
							arcs[i][j] = arcs[i][k] + arcs[k][j];
						}
					}
				}
			}
		}

		System.out.println("result:");
		
		for(int i = 0; i < num; i++) {
			for(int j = i + 1; j < num; j++) {
				System.out.println("v"+i + " -> v" + j + " " + arcs[i][j]);
			}
		}
		
	}
	
	
	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Jul 17, 2013 9:37:36 AM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		

		int[][] map = new int[][]{
				{0,10,MAX,MAX,MAX,11,MAX,MAX,MAX},
				{10,0,18,MAX,MAX,MAX,16,MAX,12},
				{MAX,MAX,0,22,MAX,MAX,MAX,MAX,8},
				{MAX,MAX,22,0,20,MAX,MAX,16,21},
				{MAX,MAX,MAX,20,0,26,MAX,7,MAX},
				{11,MAX,MAX,MAX,26,0,17,MAX,MAX},
				{MAX,16,MAX,24,MAX,17,0,19,MAX},
				{MAX,MAX,MAX,16,7,MAX,19,0,MAX},
				{MAX,12,8,21,MAX,MAX,MAX,MAX,0}
		};
		floyd(map);

	}

}
