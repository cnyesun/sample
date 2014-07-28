package com.yesun.sample.datastructure;


/**
 * sample Kruskal.java Description:
 * kruskal算法的思想是找最小边，且每次找到的边不会和以找出来的边形成环路，利用一个一维数组group存放当前顶点所在连通图标示（每条最小边，属于一个连通图），直到顶点都找完 
 * 1.0 YESUN Jul 18,
 * 2013 8:48:28 AM Create. ChangeLog:
 */
public class Kruskal {
	

	public static void kruskal(int[][] arcs) {
		
		int num = arcs.length;
		int sum = 0;

		// 使用一数组记录节点的连通情况
		// 如 group[0]＝0 表示节点未和任何节点连通
		// group[0]=group[3]=group[5]=2 表示节点0、3、5为同一连通图内，该连通图的标识为 2
		int[] group = new int[num];
		boolean finish = false;

		int temp = 0, min, n1 = 0, n2 = 0, groupNum = 0;
		// 到全部group[n]等于同一数值，也就是处于同一连通图才结束
		while (!finish) {

			// 遍历所有路径，无向图，仅遍历矩阵上三角
			// 找出最短的且不在同一连通图的（group[n1]!=group[n2]），或两节点都未加入连通图的
			min = Integer.MAX_VALUE;
			//找到矩阵中最小值
			for (int i = 0; i < num; i++) {
				for (int j = i + 1; j < num; j++) {
					if (arcs[i][j] < min && arcs[i][j] != -1) {
						if (group[i] != group[j] || (group[i] == 0 && group[j] == 0)) {
							min = arcs[i][j];
							n1 = i;
							n2 = j;
						}
					}
				}
			}
			// 无路了
			if (min == Integer.MAX_VALUE) {
				break;
			}

			System.out.println("V"+n1 + " --- V" + n2 + " len:" + arcs[n1][n2]); 
			sum += arcs[n1][n2];
			
			//加入连通图组，这里是关键，这里其实就是判断是否闭合
			//两个点都没有出现过，则增加连通图标识
			if (group[n1] == 0 && group[n2] == 0) {
				groupNum++;
				group[n1] = groupNum;
				group[n2] = groupNum;
			} else if (group[n1] != 0 && group[n2] != 0) {
				//两个点都出现过了，那么需要合并两个连通标识为1个
				//连同两个原本不相同的树
				temp = group[n2];
				for (int i = 0; i < num; i++) {
					if (group[i] == temp) {
						group[i] = group[n1];
					}
				}
			} else {
				//其中一个没出现，那么没出现的设置为已出现的连通标识
				if (group[n1] == 0) {
					group[n1] = group[n2];
				} else {
					group[n2] = group[n1];
				}
			}

			// 到全部group[n]等于同一数值且不为0，也就是处于同一连通图才结束
			temp = 1;
			while (temp < num && group[temp] == group[0]) {
				temp++;
			}
			if (group[0] != 0 && temp == num) {
				finish = true;
			}
		}
		if (!finish) {
			System.out.println("图为非强连通图");
		}
		
		System.out.println("sum:"+sum);
	}

	/**
	 * Description:
	 * 
	 * @param args
	 *            1.0 YESUN Jul 18, 2013 8:47:10 AM Create. ChangeLog:
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
		kruskal(map);
	}
	
	static int MAX = Integer.MAX_VALUE;
	
	/**
	 * Description: by yesun
	 * @param arcs
	 * 1.0 YESUN Jul 18, 2013 1:42:42 PM Create.
	 * ChangeLog:
	 */
	public static void kruskal2(int[][] arcs) {
		//顶点个数
		int num = arcs.length;
		//存放对应顶点所在连通图标识
		int[] group = new int[num];
		
		int sum = 0, n1 = 0, n2 = 0;
		boolean finished = false;
		int groupNum = 1;
		
		while(!finished) {
			int min = Integer.MAX_VALUE;
			//找出所有边中最小值
			for(int i = 0; i < num; i++) {
				for(int j = i+1; j < num; j++) {
					if(arcs[i][j] > 0 && arcs[i][j] < min){
						//如果group相同，则表示处理过，不相同或都为0都表示没处理过
						if (group[i] != group[j] || (group[i] == 0 && group[j] == 0)) {
							min = arcs[i][j];
							n1 = i;
							n2 = j;	
						}											
					}
				}
			}
			
			if(min == Integer.MAX_VALUE){
				continue;
			}
			
			System.out.println(n1 + " ---> " + n2 + " " + min);
			sum += min;
			
			//找到了最小值，设置连通标记
			if(group[n1] == 0 && group[n2] == 0){
				group[n1] = groupNum;
				group[n2] = groupNum;
				groupNum++;
			}
			else if(group[n1] > 0 && group[n2] > 0) {
				int tmp = group[n2];
				for(int m = 0; m < group.length; m++){
					if(group[m] == tmp){
						group[m] = group[n1];
					}
				}
			}
			else{
				if(group[n1] == 0){
					group[n1] = group[n2];
				}
				else{
					group[n2] = group[n1];
				}
			}
			
			for(int i = 0; i < group.length; i++) {
				if(group[i] != group[0]){
					finished = false;
					break;
				}
				else{
					finished = true;
				}
			}
			
			if(finished) {
				break;
			}
		}
		
		System.out.println(" sum:"+sum);
		
	}

}
