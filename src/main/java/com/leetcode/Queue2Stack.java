package com.leetcode;

import java.util.LinkedList;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/4/12 14:55
 * Created by: wei.chen
 */
public class Queue2Stack {

    private final LinkedList<Integer> stack1 = new LinkedList<>();
    private final LinkedList<Integer> stack2 = new LinkedList<>();

    public void push(int val) {
        stack1.push(val);
    }

    public int pop() {
        if(!stack2.isEmpty()){
            return stack2.pop();
        }
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        Queue2Stack que = new Queue2Stack();
        for(int i=1;i<=10;i++){
            que.push(i);
        }

        for(int i=1;i<=5;i++){
            System.out.println(que.pop());
        }

        for(int i=11;i<=20;i++){
            que.push(i);
        }

        for(int i=1;i<=15;i++){
            System.out.println(que.pop());
        }
    }
}
