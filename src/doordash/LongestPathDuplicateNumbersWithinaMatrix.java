package doordash;

/**
 * Given an integer matrix, find the length of the longest path that have same
 * values. The matrix has no boundary limits.
 * 
 * From each cell, you can either move to two directions: horizontal or
 * vertical. You may NOT move diagonally or move outside of the boundary.
 * 
 * nums = [ [1,1], [5,5], [5,5] ]
 * 
 * Return 4 ( Four 5's).
 * 
 * 
 */
public class LongestPathDuplicateNumbersWithinaMatrix {
	int longestPath = 0;

	public int getLongestPath(int[][] arr) {
		boolean[][] visited = new boolean[arr.length][arr[0].length];

		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[0].length; col++) {
				dfs(arr, visited, row, col, arr[row][col], 0);
			}
		}

		return longestPath;
	}

	public void dfs(int[][] arr, boolean[][] visited, int row, int col, int target, int path) {
		if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || visited[row][col]
				|| arr[row][col] != target) {
			longestPath = Math.max(path, longestPath);
			return;
		}

		if (arr[row][col] == target) {
			path++;
		}

		visited[row][col] = true;
		dfs(arr, visited, row + 1, col, target, path);
		dfs(arr, visited, row, col + 1, target, path);
		dfs(arr, visited, row - 1, col, target, path);
		dfs(arr, visited, row, col - 1, target, path);
		visited[row][col] = false;
	}

	public static void main(String[] args) {
		LongestPathDuplicateNumbersWithinaMatrix m = new LongestPathDuplicateNumbersWithinaMatrix();
		int[][] arr = { { 1, 1 }, { 5, 5 }, { 5, 5 } };
		int[][] arr2 = { { 1, 1, 1, 3, 2 }, { 2, 1, 1, 4, 2 }, { 3, 3, 1, 1, 1 }, { 4, 4, 4, 3, 4 } };
		System.out.println(m.getLongestPath(arr));
		System.out.println(m.getLongestPath(arr2));
	}
}
