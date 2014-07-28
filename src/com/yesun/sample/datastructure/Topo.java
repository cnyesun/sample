package com.yesun.sample.datastructure;

import java.util.Stack;

public class Topo {
	

	static int MAX = 65535;
	
	public static void topo(int[][] arcs){
		
		int num = arcs.length;
		Stack stack = new Stack();
		int[] list = new int[num];
		
		for(int i = 0; i < num; i++){
			if(arcs[i][0] == 0) {
				stack.push(i);//找到顶点
			}
		}
		
		
		int i = 0;
		while(!stack.isEmpty()) {
			list[i] = (Integer)stack.pop();
			
			for ( int  j =  1 ; j < arcs[list[i]].length; j++){   
				int k = arcs[list[i]][j];   //找到与list[i]的后继结点

				System.out.println("next > " + k  + " 入度" + arcs[k][0]);
				if ( --arcs[k][0] == 0) //入度减1，更新到数组，度为1的点入栈，因为度为0的顶点删除后，度为1的顶点自然就为0了，以此来实现逐步遍历所有的度为0的顶点，直到结束
					stack.push(k);
			}
			i++;
		}
		
		if(i < num){
			System.out.println("存在环");
		}
		
		for(int j = 0; j < num; j++){
			System.out.print(list[j] + " > ");
		}
		
	}

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Jul 17, 2013 9:37:36 AM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		

		//邻接表示法，[父节点入度，子（兄，弟，弟，弟）],填的是序号，不是距离了，和之前的矩阵表示法不一样,注意map[i][0]为入度，入度就是可进入该节点的路径数，即指向该节点的箭头的个数
		int[][] map = new int[][]{
				{0,11,5,4},
				{0,8,4,2},
				{2,9,6,5},
				{0,13,2},
				{2,7},
				{3,12,8},
				{1,5},
				{2},
				{2,7},
				{1,11,10},
				{1,13},
				{2},
				{1,9},
				{2}
		};
		topo(map);

	}

}
