package tags.stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * 
 * push(x) -- Push element x onto stack. pop() -- Removes the element on top of
 * the stack. top() -- Get the top element. getMin() -- Retrieve the minimum
 * element in the stack.
 * 
 * 
 * Example 1:
 * 
 * Input ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 
 * Output [null,null,null,null,-3,null,0,-2]
 * 
 * Explanation MinStack minStack = new MinStack(); minStack.push(-2);
 * minStack.push(0); minStack.push(-3); minStack.getMin(); // return -3
 * minStack.pop(); minStack.top(); // return 0 minStack.getMin(); // return -2
 */
public class MinStack155 {
	private Stack<int[]> stack = new Stack<>();// [value, cur min]

	public MinStack155() {

	}

	public void push(int x) {
		if (stack.isEmpty()) {
			stack.push(new int[] { x, x });
			return;
		}

		int currentMin = stack.peek()[1];
		stack.push(new int[] { x, Math.min(x, currentMin) });
	}

	public void pop() {
		stack.pop();
	}

	public int top() {
		return stack.peek()[0];
	}

	public int getMin() {
		return stack.peek()[1];
	}
}
