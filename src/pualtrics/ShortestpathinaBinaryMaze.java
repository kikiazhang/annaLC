package pualtrics;

import java.util.LinkedList;
import java.util.Queue;

//能否走1从左上到右下，有一个机会变0到1
public class ShortestpathinaBinaryMaze {
	public boolean isValid(int[][] matrix) {
		int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		int m = matrix.length, n = matrix[0].length;

		Queue<Path> q = new LinkedList<>();
		if (matrix[0][0] == 1) {
			q.offer(new Path(0, 0));
		} else {
			Path p = new Path(0, 0);
			p.hasZero = true;
			q.offer(p);
		}
		boolean[][] visited = new boolean[m][n];
		visited[0][0] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Path cur = q.poll();
				if (cur.x == m - 1 && cur.y == n - 1)
					return true;
				for (int[] d : dir) {
					int newX = cur.x + d[0];
					int newY = cur.y + d[1];
					if (newX < 0 || newX >= m || newY < 0 || newY >= n || (cur.hasZero && matrix[newX][newY] == 0)
							|| visited[newX][newY])
						continue;
					visited[newX][newY] = true;
					Path newP = new Path(newX, newY);
					if (matrix[newX][newY] == 0 || cur.hasZero)
						newP.hasZero = true;
					q.offer(newP);
				}
			}
		}

		return false;
	}

	class Path {
		boolean hasZero;
		int x, y;

		public Path(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		ShortestpathinaBinaryMaze s = new ShortestpathinaBinaryMaze();
		int[][] m = { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		System.out.println(s.isValid(m));
	}
}
