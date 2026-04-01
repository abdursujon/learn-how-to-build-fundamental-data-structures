package Stack;

import Interfaces.Stack;
import java.util.EmptyStackException;
import java.util.*;
public class ArrayStack implements Stack {

  protected int top;
  protected Object[] stack;

  public ArrayStack(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new EmptyStackException();
    }
    stack = new Object[initialCapacity];
    top = -1;
  }

  public ArrayStack() {
    this(10);
  }

  public boolean isEmpty(){
        return top == -1; 
  }

  public void push(Object o){
        if(top == stack.length - 1){
            Object[] newArray = new Object[stack.length * 2]; 
            System.arraycopy(stack, 0, newArray, 0, stack.length); 
            stack = newArray; 
        }
        stack[++top] = o;
  }

  public Object peek(){
    if(isEmpty()){
      throw new EmptyStackException();
    }
    return stack[top];
  }

  public Object pop(){
    if(isEmpty()){
      throw new EmptyStackException();
    }
    Object o = stack[top];
    stack[top--] = null;
    return o;
  }

  public Stack addItem(int[] nums){
    Stack stack = new ArrayStack();
    for(int i = nums.length -1; i >= 0; i--){
      this.push(nums[i]);
    }
    return stack;
  }

  public String printStack(){
    StringBuilder sb = new StringBuilder("[");
    for(int i = 0; i <= top; i++){
       sb.append(stack[i]);
       if(i < top){
         sb.append(" || "); // We chose how we print right? Must follow java boring convention? No need.
       }
    }
    sb.append("]");
    return sb.toString();
  }

  public static void main(String[] args) {
    ArrayStack stack = new ArrayStack();
    stack.push(5);
    System.out.println(stack.peek());
    stack.pop();
    System.out.println(stack.isEmpty());
    stack.addItem(new int[] {1, 3, 4, 6, 5, 5, 5,55, 5,555, 55, 55, 55, 5, 3, 33,3});
    System.out.println(stack.printStack());
  }
}
