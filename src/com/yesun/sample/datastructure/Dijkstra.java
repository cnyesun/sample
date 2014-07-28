package com.yesun.sample.datastructure;


/**
 * sample Dijkstra.java
 * Description:采用迪杰斯特拉（Dijkstra）算法, 
 * 算法原理：
 * 	1、依次从小到大遍历源点与所有点的集合，即dist[]
 *  2、步骤一找到一个当下最短点，遍历最短点的边(得到边的对端点)，对比源点到边对端点的距离，与源点到当下最短点距离 + 边长，如果更短，则更新dist
 * 1.0 YESUN Jul 18, 2013 3:37:48 PM Create.
 * ChangeLog:
 */
public class Dijkstra {
	
	
	public static void dijkstra(int[][] arcs, int begin, int end){
		int num = arcs.length;
		//存放源点到个点的最短距离
		int[] dist = new int[num];
		//表示节点状态，是否处理过
		boolean[] finished = new boolean[num];
		//存放源点到个点的路径
		String[] path = new String[num];
		
		//赋值，设置源点到各点的距离，后面需要修正dist[]为到个点的最短距离
		for(int i = 0; i < num; i++) {
			dist[i] = arcs[begin][i];
			path[i] = begin + " -> " + i;
			finished[i] = false;
		}
		
		finished[begin] = true;
		
		for(int i = 0; i < num; i++){ 
			int min = Integer.MAX_VALUE;
			int point = 0;
			for(int j = 0; j < num; j++){
				if(dist[j] < min && dist[j] > 0 && !finished[j]){//注意!finished[j]这个条件，依次选择最短，次短，从而实现对所有节点的遍历，point将指向所有节点
					min = dist[j];
					point = j;
				}			
			}

			//找到最短点，标记
			finished[point] = true;

			//找到最小值，对比最小值的各边到begin的距离，然后更新dist
			for(int j = 0; j < num; j++) {
				if(!finished[j] && arcs[point][j] < Integer.MAX_VALUE && (dist[j] > dist[point] + arcs[point][j])) {
					//dist[j]源点到与最短点存在边的点的距离
					//dist[point]即源点最短点距离
					//当源点 到 目标点的距离，大于源点到最近点 + 最近点到目标点距离时，更新dist[]
					dist[j] = dist[point] + arcs[point][j];
				}
			}
		}
		
		
		//输出begin到个点的距离
		for(int i = 0; i < num; i++){
			System.out.println(path[i] + " : "+ dist[i]);
		}
	}
	

	static int MAX = Integer.MAX_VALUE;

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Jul 18, 2013 3:37:38 PM Create.
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
		dijkstra(map, 5, 2);

	}

}
