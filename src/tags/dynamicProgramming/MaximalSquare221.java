package tags.dynamicProgramming;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * Input:
 * 
 * 1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0
 * 
 * Output: 4
 */
public class MaximalSquare221 {
	// dp o(n^2)
	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;

		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
		int max = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '1') {
					// 上左右的最小+1
					dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i][j + 1], dp[i + 1][j])) + 1;
					max = Math.max(max, dp[i + 1][j + 1] * dp[i + 1][j + 1]);
				}
			}
		}

		return max;
	}
}
