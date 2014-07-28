package com.yesun.sample.datastructure;

public class BinaryTree {
	
	
	class Node {
		
		private Node left;
		private Node right;
		private Comparable data;
		
		public void addNode(Node newNode){

			if(newNode.data.compareTo(this.data) < 0){
				//放左边
				if(this.left == null){
					this.left = newNode;
				}
				else{
					//左边不为空，就放到左边的子节点上，递归
					this.left.addNode(newNode);
				}
			}
			if(newNode.data.compareTo(this.data) >= 0){
				//放右边
				if(this.right == null){
					this.right = newNode;
				}
				else{
					//右边不为空，就放到右边的子节点上，递归
					this.right.addNode(newNode);
				}
			}
		}

		public void printNode(){
			//print left
			if(this.left != null){
				this.left.printNode();				
			}
			
			//print current
			System.out.println(this.data + "\r\n");
			
			//print right
			if(this.right != null){
				this.right.printNode();				
			}
		}
	}
	
	
	private Node root;
	public void addNode(Comparable data){
		Node node = new Node();
		node.data = data;
		if(root == null){
			root = node;
		}
		else{
			root.addNode(node);
		}
	}

	public void printNode(){
		this.root.printNode();		
	}
	

	/**
	 * Description:
	 * @param args
	 * 1.0 YESUN Jul 9, 2013 11:10:02 AM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();
		tree.addNode(6);
		tree.addNode(2);
		tree.addNode(9);
		tree.addNode(5);
		tree.addNode(8);
		tree.printNode();		

	}

}
