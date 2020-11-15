package tags.dfs.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 * 
 * the value 0 representing an empty cell; the value 1 representing a fresh
 * orange; the value 2 representing a rotten orange. Every minute, any fresh
 * orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a
 * fresh orange. If this is impossible, return -1 instead. Input:
 * [[2,1,1],[1,1,0],[0,1,1]] Output: 4 Example 2:
 * 
 * Input: [[2,1,1],[0,1,1],[1,0,1]] Output: -1 Explanation: The orange in the
 * bottom left corner (row 2, column 0) is never rotten, because rotting only
 * happens 4-directionally. Example 3:
 * 
 * Input: [[0,2]] Output: 0 Explanation: Since there are already no fresh
 * oranges at minute 0, the answer is just 0.
 */
public class RottingOranges994 {
	public int orangesRotting(int[][] grid) {
		Queue<Integer> queue = new LinkedList<>();
		// 找到所有2作为开始
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2) {
					queue.offer(i * grid[0].length + j);
				}
			}
		}
		// bfs
		int step = -1;
		int[] dr = new int[] { -1, 0, 1, 0 };
		int[] dc = new int[] { 0, -1, 0, 1 };
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				// 遍历cur size所有的index
				int index = queue.poll();
				// 每一个2可以向四个方向的1扩展
				for (int i = 0; i < 4; i++) {
					int row = index / grid[0].length + dr[i];
					int col = index % grid[0].length + dc[i];
					if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1) {
						// 1变成2，加入queue
						grid[row][col] = 2;
						queue.offer(row * grid[0].length + col);
					}
				}
			}
			step++;
		}
		// 如果还有1，那么永远到不了
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}
		return step == -1 ? 0 : step;
	}
}
