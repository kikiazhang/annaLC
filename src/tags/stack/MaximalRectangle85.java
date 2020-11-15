package tags.stack;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * Input: [ ["1","0","1","0","0"], ["1","0","1","1","1"], ["1","1","1","1","1"],
 * ["1","0","0","1","0"] ] Output: 6
 */
public class MaximalRectangle85 {
	// O(N * NM)
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return 0;

		int max = 0;
		int[] dp = new int[matrix[0].length];// 只记录一row的高度
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '1') {
					int k = j;// 往前遍历，找到相连的，高度大于1的index
					dp[j] = dp[j] + 1;// j高度加一
					int min = dp[j] + 1;// 最小高度
					while (k >= 0 && dp[k] > 0) {
						min = Math.min(min, dp[k]);// 更新最小高度
						max = Math.max(max, min * (j - k + 1));// 更新最大面积
						k--;
					}
				} else {
					dp[j] = 0;// 变回0
				}
			}
		}
		return max;
	}

	// o（nm）
	public int maximalRectangle2(char[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return 0;

		int max = 0;
		int[] dp = new int[matrix[0].length];// 只记录一row的高度

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;// 上一行算出的高度
			}
			max = Math.max(max, helper(dp));
		}
		return max;
	}

	private int helper(int[] dp) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);// 记录每一个index，peek的都是目前max height，当他大于cur height时，说明该算出max height能到的面积，再加入cur
						// height
		int max = 0;
		for (int i = 0; i < dp.length; i++) {
			while (stack.peek() != -1 && dp[stack.peek()] >= dp[i]) {// 不为空而且前一个height大于cur height
				max = Math.max(max, dp[stack.pop()] * (i - stack.peek() - 1));// stack
																				// pop为目前最大height，先算完这个height的面积（height*（i-1
																				// -下一个peek））
			}
			stack.push(i);
		}
		while (stack.peek() != -1) {// 还有height没算过
			max = Math.max(max, dp[stack.pop()] * (dp.length - stack.peek() - 1));
		}
		return max;
	}
}
