package twoSigma;

import java.util.Arrays;

public class FriendsCircle {

	public int findCircleNum(int[][] M) {
		int n = M.length;
		Union u = new Union(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (M[i][j] == 1 && i != j) {
					u.union(i, j);
				}
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (u.parents[i] == -1) {
				count++;
			}
		}
		return count;
	}

	class Union {
		int[] parents;

		public Union(int n) {
			parents = new int[n];
			Arrays.fill(parents, -1);
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if (rootX != rootY) {
				parents[rootY] = rootX;
			}
		}

		public int find(int index) {
			if (parents[index] == -1) {
				return index;
			}
			return find(parents[index]);
		}
	}
}
