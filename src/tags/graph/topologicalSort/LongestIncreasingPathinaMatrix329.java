package tags.graph.topologicalSort;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * 
 * From each cell, you can either move to four directions: left, right, up or
 * down. You may NOT move diagonally or move outside of the boundary (i.e.
 * wrap-around is not allowed).
 * 
 * Example 1:
 * 
 * Input: nums = [ [9,9,4], [6,6,8], [2,1,1] ] Output: 4 Explanation: The
 * longest increasing path is [1, 2, 6, 9]. Example 2:
 * 
 * Input: nums = [ [3,4,5], [3,2,6], [2,2,1] ] Output: 4 Explanation: The
 * longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathinaMatrix329 {
	// dfs with cache o(mn)
	public int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int[][] cache = new int[matrix.length][matrix[0].length];
		int res = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				res = Math.max(res, dfs(matrix, i, j, cache));
			}
		}
		return res;
	}

	int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private int dfs(int[][] matrix, int i, int j, int[][] cache) {
		if (cache[i][j] != 0)// 计算过了
			return cache[i][j];
		for (int[] d : dirs) {// 四个方向
			int x = d[0] + i, y = d[1] + j;
			if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
				cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache) + 1);// 可以+1
			} else {
				cache[i][j] = Math.max(cache[i][j], 1);// 初始值是1
			}
		}
		return cache[i][j];
	}
}
