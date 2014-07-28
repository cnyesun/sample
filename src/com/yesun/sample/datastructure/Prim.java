package com.yesun.sample.datastructure;


/**
 * sample Prim.java
 * Description:prim算法的经典之处在于利用一个一维数组巧妙的实现了查找两组数据之间的最短距离，时间复杂度O(n^2)
 * 1.0 YESUN Jul 18, 2013 8:47:15 AM Create.
 * ChangeLog:
 */
public class Prim {
	
	static int MAX = 65535;
	
	public static void prim(int[][] graph, int n){
		
		char[] c = new char[]{'A','B','C','D','E','F','G','E','F'};
		/* lowcost[i]记录以i为终点的边的最小权值，当lowcost[i]=0时表示终点i加入生成树 */
		int[] lowcost = new int[n];
		/* mst[i]记录对应lowcost[i]的起点，当mst[i]=0时表示起点i加入生成树 */
		int[] mst = new int[n];
		int i, j, min, point, sum = 0;
		
		for(i = 1; i < n; i++){
			lowcost[i] = graph[0][i];//赋值，得到v0的横向数组
			mst[i] = 0;			
		}
		
		for(i = 1; i < n; i++){
			
			min = MAX;
			point = 0;
			for(j = 1; j < n; j++){
				if (lowcost[j] < min && lowcost[j] != 0) {
					min = lowcost[j];
					point = j;
				}
			}
			//输出生成树边的信息:起点，终点，权值
			System.out.println(c[mst[point]] + "到" + c[point] + " 权值：" + min);
			
			//得到与v0的最短距离点point
			sum += min;
			lowcost[point] = 0;
			
			/* 更新当前节点minid到其他节点的权值 */
			for (j = 1; j < n; j++) {
				/* 发现更小的权值 */
				if (graph[point][j] < lowcost[j]) {
					/* 更新权值信息 */
					lowcost[j] = graph[point][j];
					//lowcost[i]，这数组存放的是已加入树节点中所有节点与未加入树节点的最短距离（最小值），非常的巧妙
	 
					/* 更新最小权值边的起点 */
					mst[j] = point;
				}
			}
		}
		
		System.out.println("sum: " + sum);
		
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
		prim(map, map.length);

	}

}
