package com.yesun.sample.datastructure;

/**
 * <p>Title: 图的遍历、最小生成树、最短路径</p>
 *
 *
 * <p>Description:
 *
 * 采用邻接矩阵做为图存储结构，有权无向图，不相连的值为 -1
 *
 * 图的遍历中深度遍历采用递归方法，广度遍历使用辅助队列
 *
 * 最小生成树采用克鲁斯卡尔（Kruskal）算法，使用一数组记录节点的连通情况
 *
 * 图的最短路径采用迪杰斯特拉（Dijkstra）算法，使用队列记录依次途经的路径
 *
 * 修改：
 * 深度、广度遍历中 在列出第n节点相邻的节点并一一入栈/队时，相邻最近的优先入栈/队
 *
 * </p>
 *
 * <p>Website: www.hartech.cn </p>
 * <p>Page: http://www.hartech.cn/blog/blogview.asp?logID=88 </p>
 *
 * <p>Date: 2006-09-08 </p>
 */
public class Graph {

	// 图邻接矩阵
	private static int[][] arcs;
	// 节点数
	private static int num;
	// 记录是否访问过
	private static boolean[] hasVisit;

	// 记录访问过的前一个节点，用于统计线路总长度
	static int pre;

	//   // 深度优先遍历，给出图邻接矩阵和开始遍历的节点
	//   public static void traverse_DFS(int[][] arcs_in, int begin) {
	//       pre = begin;
	//       if (arcs_in == null || arcs_in.length == 0 ||
	//               arcs_in.length != arcs_in[0].length || begin < 0) {
	//           System.err.println("wrong arcs[][] or begin!");
	//           return;
	//       }
	//       arcs = arcs_in;
	//       num = arcs.length;
	//       hasVisit = new boolean[num];
	//
	//       DFS(begin);
	//   }

	//   private static void DFS(int begin) {
	//       hasVisit[begin] = true;
	//       Main.Q_DFS.enQueue(begin);
	//       Main.length_DFS += Main.arcs[pre][begin];
	//       pre = begin;
	//
	//       int min, n = 0;
	//       for (int i = 1; i < num; i++) {
	//           // 距离最短的优先入栈
	//           min = Integer.MAX_VALUE;
	//           for (int j = 0; j < num; j++) {
	//               if (!hasVisit[j] && arcs[begin][j] != -1 && arcs[begin][j] < min) {
	//                   min = arcs[begin][j];
	//                   n = j;
	//               }
	//           }
	//           if (min == Integer.MAX_VALUE) {
	//               break;
	//           }
	//           else {
	//               DFS(n);
	//           }
	//       }
	//   }

	// 广度优先遍历，给出图邻接矩阵和开始遍历的节点
	//   public static void traverse_BFS(int[][] arcs_in, int begin) {
	//       pre = begin;
	//       if (arcs_in == null || arcs_in.length == 0 ||
	//               arcs_in.length != arcs_in[0].length || begin < 0) {
	//           System.err.println("wrong arcs[][] or begin!");
	//           return;
	//       }
	//       arcs = arcs_in;
	//       num = arcs.length;
	//       hasVisit = new boolean[num];
	//       Queue queue = new Queue();
	//
	//       hasVisit[begin] = true;
	//       queue.enQueue(begin);
	//       int temp, min, n = 0;
	//       while (!queue.isEmpty()) {
	//           temp = ( (Integer) queue.deQueue()).intValue();
	//           for (int i = 1; i < num; i++) {
	//               // 距离最短的优先入队
	//               min = Integer.MAX_VALUE;
	//               for (int j = 0; j < num; j++) {
	//                   if (!hasVisit[j] && arcs[temp][j] != -1 && arcs[temp][j] < min) {
	//                       min = arcs[temp][j];
	//                       n = j;
	//                   }
	//               }
	//               if (min == Integer.MAX_VALUE) {
	//                   break;
	//               }
	//               else {
	//                   hasVisit[n] = true;
	//                   queue.enQueue(n);
	//                   Main.Q_BFS.enQueue(n);
	//                   Main.length_BFS += Main.arcs[pre][n];
	//                   pre = n;
	//               }
	//           }
	//       }
	//   }

	// 构造最小生成树，采用克鲁斯卡尔（Kruskal）算法
	// 使用一数组记录节点的连通情况
	public static void miniSpanTree(int[][] arcs_in) {
		if (arcs_in == null || arcs_in.length == 0
				|| arcs_in.length != arcs_in[0].length) {
			System.err.println("wrong arcs[][]!");
			return;
		}
		arcs = arcs_in;
		num = arcs.length;
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
			for (int i = 0; i < num; i++) {
				for (int j = i + 1; j < num; j++) {
					if (arcs[i][j] < min && arcs[i][j] != -1) {
						if (group[i] != group[j]
								|| (group[i] == 0 && group[j] == 0)) {
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
			
//			Main.Q_tree.enQueue(new Dimension(n1, n2));
//			Main.length_tree += Main.arcs[n1][n2];

			// 加入连通图组
			if (group[n1] == 0 && group[n2] == 0) {
				groupNum++;
				group[n1] = groupNum;
				group[n2] = groupNum;
			} else if (group[n1] != 0 && group[n2] != 0) {
				temp = group[n2];
				for (int i = 0; i < num; i++) {
					if (group[i] == temp) {
						group[i] = group[n1];
					}
				}
			} else {
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
	//
	//   // 图的最短路径，给出图邻接矩阵，起点，终点，打印出途经的节点
	//   // 采用迪杰斯特拉（Dijkstra）算法
	//   public static void shortestPath_DIJ(int[][] arcs_in, int begin, int end) {
	//       if (arcs_in == null || arcs_in.length == 0 ||
	//               arcs_in.length != arcs_in[0].length) {
	//           System.err.println("wrong arcs[][]!");
	//           return;
	//       }
	//       arcs = arcs_in;
	//       num = arcs.length;
	//       // 标识节点是否已找到最短路径，从begin到n 为finish[n]=true;
	//       boolean[] finish = new boolean[num];
	//       // 记录从 begin 到 n 的最短路径为 min[n]
	//       int[] D = new int[num];
	//       // 使用队列记录路径途经节点
	//       Queue[] queue = new Queue[num];
	//
	//       // 初始化
	//       for (int i = 0; i < num; i++) {
	//           D[i] = arcs[begin][i];
	//           finish[i] = false;
	//           queue[i] = new Queue();
	//       }
	//       finish[begin] = true;
	//       D[begin] = -1;
	//
	//       int v = 0, min = 0;
	//       // 一个一个循环找出最短距离（共num－1个）
	//       for (int i = 1; i < num; i++) {
	//           min = Integer.MAX_VALUE;
	//           // 扫描找出非final集中最小的D[]
	//           for (int w = 0; w < num; w++) {
	//               if (!finish[w] && D[w] < min && D[w] != -1) {
	//                   v = w;
	//                   min = D[w];
	//               }
	//           }
	//
	//           finish[v] = true;
	//           // 已找到目标，退出循环
	//           if (v == end) {
	//               queue[v].enQueue(v);
	//               Main.Q_shortest = queue[v].clone();
	//               Main.length_short = D[v];
	//               break;
	//           }
	//
	//           // 更新各D[]数据
	//           for (int w = 0; w < num; w++) {
	//               if (!finish[w] && arcs[v][w] != -1) {
	//                   if ( (arcs[v][w] + min) < D[w] || D[w] == -1) {
	//                       D[w] = arcs[v][w] + min;
	//                       queue[w] = queue[v].clone();
	//                       queue[w].enQueue(v);
	//                   }
	//               }
	//           }
	//           queue[v].enQueue(v);
	//       }
	//   }
	
	
	
	
	
	
	
	
	
	
	static int MAX = Integer.MAX_VALUE;
	public static void main(String[] args){
		
		int[][] map = new int[][]{
				{0,10,MAX,MAX,MAX,11,MAX,MAX,MAX},
				{10,0,18,MAX,MAX,MAX,16,MAX,12},
				{MAX,MAX,0,22,MAX,MAX,MAX,MAX,8},
				{MAX,MAX,22,0,20,MAX,MAX,16,21},
				{MAX,MAX,MAX,20,0,26,MAX,7,MAX},
				{11,MAX,MAX,MAX,26,0,17,MAX,MAX},
				{MAX,16,MAX,MAX,MAX,17,0,19,MAX},
				{MAX,MAX,MAX,16,7,MAX,19,0,MAX},
				{MAX,12,8,21,MAX,MAX,MAX,MAX,0}
		};
		
		miniSpanTree(map);
	}
}