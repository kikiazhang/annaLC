package pualtrics;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumKnightMoves1197 {
	public int minKnightMoves(int x, int y) {
		if (x == 0 && y == 0)
			return 0;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0 });
		int count = 0;
		Set<String> visited = new HashSet<>();
		visited.add("0,0");
		// If we can reach x,y in one quadrant then we can do it for all others in the
		// same number of moves too.
		x = Math.abs(x);
		y = Math.abs(y);
		int[][] dir = { { -2, -1 }, { 2, 1 }, { -2, 1 }, { 2, -1 }, { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 } };
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				if (cur[0] == x && cur[1] == y)
					return count;
				for (int[] d : dir) {
					int newX = cur[0] + d[0], newY = cur[1] + d[1];
					// Special case for (1,1) because there is a more efficient way to reach it if
					// we allow negative positions.so need >=-1
					if (!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
						visited.add(newX + "," + newY);
						q.offer(new int[] { newX, newY });
					}
				}
			}
			count++;
		}
		return -1;
	}

	public static void main(String[] args) {
		MinimumKnightMoves1197 m = new MinimumKnightMoves1197();
		System.out.println(m.minKnightMoves(2, 1));
	}
}
