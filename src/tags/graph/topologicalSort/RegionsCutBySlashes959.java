package tags.graph.topologicalSort;

/**
 * In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /,
 * \, or blank space. These characters divide the square into contiguous
 * regions.
 * 
 * (Note that backslash characters are escaped, so a \ is represented as "\\".)
 * 
 * Return the number of regions.
 */
public class RegionsCutBySlashes959 {
	// union find o（n*n*n）
	// 把一格分成四份
	//  1
	// 3 2
	//  4
	public int regionsBySlashes(String[] grid) {
		int N = grid.length;
		DSU dsu = new DSU(4 * N * N);
		for (int r = 0; r < N; ++r)
			for (int c = 0; c < N; ++c) {
				int root = 4 * (r * N + c);
				char val = grid[r].charAt(c);
				if (val != '\\') {
					dsu.union(root + 0, root + 1);
					dsu.union(root + 2, root + 3);
				}
				if (val != '/') {
					dsu.union(root + 0, root + 2);
					dsu.union(root + 1, root + 3);
				}

				if (r + 1 < N)// 不是最下一行，上一行4和下一行0union
					dsu.union(root + 3, (root + 4 * N) + 0);
				if (r - 1 >= 0)// 不是最上一行，上一行4和下一行0union
					dsu.union(root + 0, (root - 4 * N) + 3);
				if (c + 1 < N)// 不是最右，左边2和后边3union
					dsu.union(root + 2, (root + 4) + 1);
				if (c - 1 >= 0)// 不是最左，左边2和后边3union
					dsu.union(root + 1, (root - 4) + 2);
			}

		int ans = 0;
		for (int x = 0; x < 4 * N * N; ++x) {
			if (dsu.find(x) == x)
				ans++;
		}

		return ans;
	}

	class DSU {
		int[] parent;

		public DSU(int N) {
			parent = new int[N];
			for (int i = 0; i < N; ++i)
				parent[i] = i;
		}

		public int find(int x) {
			if (parent[x] != x)
				parent[x] = find(parent[x]);
			return parent[x];
		}

		public void union(int x, int y) {
			parent[find(x)] = find(y);
		}
	}
}
