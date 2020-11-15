package pualtrics;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls
 * (represented as 1). The ball can go through the empty spaces by rolling up,
 * down, left or right, but it won't stop rolling until hitting a wall. When the
 * ball stops, it could choose the next direction.
 * 
 * Given the maze, the ball's start position and the destination, where start =
 * [startrow, startcol] and destination = [destinationrow, destinationcol],
 * return true if the ball can stop at the destination, otherwise return false.
 * 
 * You may assume that the borders of the maze are all walls (see examples).
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]],
 * start = [0,4], destination = [4,4] Output: true Explanation: One possible way
 * is : left -> down -> left -> down -> right -> down -> right.
 */
public class TheMaze490 {
	// bfs
	public boolean hasPath(int[][] maze, int[] st, int[] dest) {
		int m = maze.length, n = maze[0].length;
		if (st[0] == dest[0] && st[1] == dest[1])
			return true;
		int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		boolean[][] visited = new boolean[m][n];
		Queue<int[]> q = new LinkedList<>();
		visited[st[0]][st[1]] = true;
		q.offer(st);
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			// 四个方向走到底的新位置，没见过就写入queue
			for (int i = 0; i < 4; i++) {
				int x = cur[0], y = cur[1];
				while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
					x += dir[i][0];
					y += dir[i][1];
				}
				// 退回一格，是最后一个0的位置
				x -= dir[i][0];
				y -= dir[i][1];
				if (visited[x][y])
					continue;
				visited[x][y] = true;
				if (x == dest[0] && y == dest[1])
					return true;
				q.offer(new int[] { x, y });
			}
		}
		return false;
	}

	// TheMazeII 505
	public int shortestDistance(int[][] maze, int[] st, int[] dest) {
		int m = maze.length, n = maze[0].length;
		if (st[0] == dest[0] && st[1] == dest[1])
			return 0;
		int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int[][] minStep = new int[m][n];// 到这个点最少的step
		for (int[] l : minStep) {
			Arrays.fill(l, Integer.MAX_VALUE);
		}
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);// 每次拿出最少step的方式
		q.offer(new int[] { st[0], st[1], 0 });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (minStep[cur[0]][cur[1]] <= cur[2])// 有更少的方法，跳过
				continue;
			minStep[cur[0]][cur[1]] = cur[2];
			// 四个方向走到底的新位置，没见过就写入queue
			for (int i = 0; i < 4; i++) {
				int x = cur[0], y = cur[1], l = cur[2];
				while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
					x += dir[i][0];
					y += dir[i][1];
					l++;
				}
				// 退回一格，是最后一个0的位置
				x -= dir[i][0];
				y -= dir[i][1];
				l--;
				q.offer(new int[] { x, y, l });
			}
		}
		return minStep[dest[0]][dest[1]] == 0 ? -1 : minStep[dest[0]][dest[1]];
	}
}
