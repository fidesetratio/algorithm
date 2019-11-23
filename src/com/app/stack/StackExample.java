package com.app.stack;

import java.util.Stack;

public class StackExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Stack<Integer> stack = new  Stack<Integer>();
			stack.push(1);
			stack.push(100);
			while(!stack.isEmpty()) {
				int v = stack.pop();
				System.out.println(v);
			}
			
	}

}
