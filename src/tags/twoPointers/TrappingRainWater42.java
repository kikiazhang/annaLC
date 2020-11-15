package tags.twoPointers;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1] Output: 6
 */
public class TrappingRainWater42 {
	public int trap(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int leftMax = 0;// 左右的最高处，任何比他低的，说明能积水
		int rightMax = 0;
		int res = 0;
		while (i <= j) {
			leftMax = Math.max(height[i], leftMax);
			rightMax = Math.max(height[j], rightMax);
			if (leftMax < rightMax) {// 永远之比较leftMax rightMax
				res += leftMax - height[i++];
			} else {// 默认相等时动right
				res += rightMax - height[j--];
			}
		}
		return res;
	}

	public int trap2(int[] height) {
		Stack<Integer> stack = new Stack<>();// index stack，放的都是在递减的
		int area = 0;
		int i = 0;
		while (i < height.length) {
			if (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
				// when height i >= stack.peek, continue compare
				// (例如stack存的高度2，1，i高度3，就一直把1，2都比较完)
				int top = stack.pop();// 说明是现在的低点
				int width = !stack.isEmpty() ? i - stack.peek() - 1 : i;
				int h = 0;
				if (!stack.isEmpty()) {
					h = Math.min(height[i], height[stack.peek()]) - height[top];// st，end低的 - 低点
				}
				area += width * h;
			} else {
				// stack放入每一个index（if中之比较不移动i）
				stack.push(i++);
			}
		}
		return area;
	}
}
