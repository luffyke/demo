package bx.basic;

import java.util.Stack;

/**
 * 两个栈实现一个队列
 * 
 * @author kxt
 */
public class Queue {
    
    private Stack<Object> stack1;
    private Stack<Object> stack2;
    
    public Queue() {
        stack1 = new Stack<Object>();
        stack2 = new Stack<Object>();
    }
    
    public boolean offer(Object data) {
        stack1.push(data);
        return true;
    }
    
    public Object poll() {
        if (!stack2.empty()) {
            return stack2.pop();
        } else {
            while(!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.empty() ? null : stack2.pop();
    }
 
}