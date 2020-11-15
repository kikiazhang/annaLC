package tags.stack;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day
 * in the input, tells you how many days you would have to wait until a warmer
 * temperature. If there is no future day for which this is possible, put 0
 * instead.
 * 
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76,
 * 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * 
 * Note: The length of temperatures will be in the range [1, 30000]. Each
 * temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures739 {
	public int[] dailyTemperatures(int[] T) {
		int[] res = new int[T.length];
		Stack<Integer> stack = new Stack<>();// 放index
		for (int i = T.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && T[i] >= T[stack.peek()])
				stack.pop();// 找到比cur大的
			res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
			stack.push(i);// 放入cur，保证cur是比下一个大的
		}
		return res;
	}
}
