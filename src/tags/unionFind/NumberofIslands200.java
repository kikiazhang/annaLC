package tags.unionFind;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: grid = [ ["1","1","1","1","0"], ["1","1","0","1","0"],
 * ["1","1","0","0","0"], ["0","0","0","0","0"] ] Output: 1 Example 2:
 * 
 * Input: grid = [ ["1","1","0","0","0"], ["1","1","0","0","0"],
 * ["0","0","1","0","0"], ["0","0","0","1","1"] ] Output: 3
 */
public class NumberofIslands200 {
	// union find
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;

		int[][] distance = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		UnionFind u = new UnionFind(grid);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					// 四个方向找1，如果有，就union
					for (int[] d : distance) {
						int x = i + d[0];
						int y = j + d[1];
						if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1') {
							int id1 = i * grid[0].length + j;
							int id2 = x * grid[0].length + y;
							u.union(id1, id2);
						}
					}
				}
			}
		}
		// union get count
		return u.getCount();
	}

	class UnionFind {
		int count;
		int[] parent;// 爸爸是谁
		int[] rank;// rank是几，越高越厉害

		public UnionFind(char[][] grid) {
			count = 0;
			parent = new int[grid.length * grid[0].length];// 一维表示数组
			rank = new int[grid.length * grid[0].length];
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == '1') {
						parent[i * grid[0].length + j] = i * grid[0].length + j;// 一维，先是自己
						count++;
					}
					rank[i * grid[0].length + j] = 0;// rank都为0
				}
			}
		}

		public int find(int i) {// 找祖先
			if (parent[i] != i)
				parent[i] = find(parent[i]);
			return parent[i];
		}

		public void union(int x, int y) {
			int rootx = find(x);
			int rooty = find(y);
			if (rootx != rooty) {// 祖先不同才能union，不然就已经在一起了
				if (rank[rootx] > rank[rooty]) {// rank大的厉害，rank小的并入（parent变成大的）
					parent[rooty] = rootx;
				} else if (rank[rootx] < rank[rooty]) {
					parent[rootx] = rooty;
				} else {
					// 相同时总并入x，并且自动提升x rank
					parent[rooty] = rootx;
					rank[rootx] += 1;
				}
				count--;
			}
		}

		public int getCount() {
			return count;
		}
	}

	// dfs
	public int numIslands2(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;

		int sum = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					sum++;
					countIsland(grid, i, j);
				}
			}
		}
		return sum;
	}

	private void countIsland(char[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
			return;

		grid[i][j] = '0';
		countIsland(grid, i + 1, j);
		countIsland(grid, i - 1, j);
		countIsland(grid, i, j + 1);
		countIsland(grid, i, j - 1);
	}
}
