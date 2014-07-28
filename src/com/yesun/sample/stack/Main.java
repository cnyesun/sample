package com.yesun.sample.stack;

import java.util.Stack;

public class Main {

	/**
	 * Description:
	 * @param args
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN Jun 21, 2013 11:06:23 AM Create.
	 * ChangeLog:
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String expression = "9+(3-1)*3+10/2";
		System.out.println(expression);	
		String value = changeExpression(expression);
		System.out.println(value);

	}
	
	
	/**
	 * Description:四则运算表达式转中缀表达式
	 * @param expression
	 * @return
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN Jun 21, 2013 2:02:51 PM Create.
	 * ChangeLog:
	 */
	public static String changeExpression(String expression) {
		
		Stack<Character> stack = new Stack<Character>();
		StringBuffer sb =new StringBuffer();
		//依次遍历每个char
		for(int i=0;i<expression.length();i++){
			char c = expression.charAt(i);
			//数字直接输出
			if(c >= '0' && c <='9'){
				sb.append(c);
			}
			else
			{
				//左括号，直接压栈
				if(c == '('){
					stack.push(c);
				}
				else{
					//右括号，将左括号之后（上面）的全部出栈，输出
					if(c == ')'){
						while(stack.peek() != '('){
							sb.append(stack.pop());
						}
						//左括号出栈，抛弃
						stack.pop();
					}
					else{
						//栈为空时，直接压栈
						if(stack.isEmpty()){
							stack.push(c);
						}
						else{
							//当前char与栈顶对比优先级，* / 优先级高于+-，如果当前char优先级高，压栈
							if(prior(c) > prior(stack.peek())){
								stack.push(c);
							}
							else{
								//当前char优先级低，出栈，输出，注意，需要依次向下对比，一直碰到比当前char优先级低的才停止
								while(!stack.isEmpty() && prior(c)<=prior(stack.peek())){
									sb.append(stack.pop());								
								}
								//当前字符压栈
								stack.push(c);
							}
						}
					}
				}			
			}
		}
		
		while(!stack.isEmpty()){
			sb.append(stack.pop());			
		}
		
		return sb.toString();
		
	}
	
	static int prior(char op){
		if(op=='+' || op=='-')
		   return 1;
		if(op=='*' || op=='/')
		   return 2;
		return 0;
	}

	

}
