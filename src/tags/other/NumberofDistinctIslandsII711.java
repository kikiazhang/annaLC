package tags.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 * 
 * Count the number of distinct islands. An island is considered to be the same
 * as another if they have the same shape, or have the same shape after rotation
 * (90, 180, or 270 degrees only) or reflection (left/right direction or up/down
 * direction).
 * 
 * Example 1: 11000 10000 00001 00011 Given the above grid map, return 1.
 * 
 * Notice that: 11 1 and 1 11 are considered same island shapes. Because if we
 * make a 180 degrees clockwise rotation on the first island, then two islands
 * will have the same shapes. Example 2: 11100 10001 01001 01110 Given the above
 * grid map, return 2.
 * 
 * Here are the two distinct islands: 111 1 and 1 1
 * 
 * Notice that: 111 1 and 1 111 are considered same island shapes. Because if we
 * flip the first array in the up/down direction, then they have the same
 * shapes.
 */
public class NumberofDistinctIslandsII711 {
	// 难点： 输入一个矩阵， 1表示陆地，0表示水，求不同形状的岛屿的个数.
	// 这里，岛屿的形状可以旋转90度，180度，270度； 或者也可以按照x轴对称反转或者按照y轴对称反转
	// 正确的做法是， 遍历后，根据dx和dy， 把旋转和反转的坐标都计算出来（这里计算的时候仍然可以用dx和dy），
	// 然后把所有的坐标都取出来以后，再对坐标进行排序， 然后在把每个坐标换算成key， 进行去重判断。
	// 去重的时候有一个细节，就是如果两个岛屿形状一样，
	// 那么，并不是意味着两个岛屿的所有转换的key都是一样的，而是说如果把之前遇到的key加到一个set里面的话，新来的岛屿，只要有一个key是一样的，那么就当这个岛屿是一样的。
	// o（mn）
	public int numDistinctIslands2(int[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];

		Set<String> set = new HashSet<>();// 出现过的岛的种类
		int count = 0;
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[i].length; ++j) {
				if (grid[i][j] == 1 && !visited[i][j]) {// 开始写入岛
					List<List<int[]>> allList = new ArrayList<>();// 所有的旋转和reflection结合起来，就是6种情况
					for (int t = 0; t < 6; ++t) {
						allList.add(new ArrayList<>());
					}

					dfs(grid, i, j, visited, i, j, allList);

					boolean exist = false;
					List<String> nset = new ArrayList<>();// 排序所有的变形，变成string
					for (List<int[]> list : allList) {
						if (list.size() == 0)
							continue;

						// sort after finishing all operations
						Collections.sort(list, new Comparator<int[]>() {
							public int compare(int[] a, int[] b) {
								return (a[0] != b[0]) ? (a[0] - b[0]) : (a[1] - b[1]);// 只比较一个（dx，dy）
							}
						});

						String s = encode(list);

						if (set.contains(s)) {
							exist = true;
						}
						nset.add(s);
					}
					// 独特的岛
					if (!exist) {
						count++;
						set.addAll(nset);
					}
				}
			}
		}
		return count;
	}

	// 默认最小的是开始，计算下面的点，写入s
	String encode(List<int[]> list) {
		int ox = list.get(0)[0];
		int oy = list.get(0)[1];

		StringBuilder sb = new StringBuilder();
		sb.append("0,0;");

		for (int k = 1; k < list.size(); ++k) {
			int dx = list.get(k)[0] - ox;
			int dy = list.get(k)[1] - oy;
			sb.append(dx).append(",").append(dy).append(";");
		}

		String s = sb.toString();
		return s;
	}

	void addAll(List<List<int[]>> allList, int dx, int dy) {
		allList.get(0).add(new int[] { dx, dy });

		// rotation
		allList.get(1).add(new int[] { -dy, dx });// 左转90
		allList.get(2).add(new int[] { -dx, -dy });// 180
		allList.get(3).add(new int[] { dy, -dx });// 右转90

		// mirror
		allList.get(4).add(new int[] { -dx, dy });// 左右翻转
		allList.get(5).add(new int[] { dx, -dy });// 上下翻转
	}

	// ox，oy一直是matrix中的i，j
	void dfs(int[][] grid, int x, int y, boolean[][] visited, int ox, int oy, List<List<int[]>> allList) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
			return;
		if (visited[x][y] || grid[x][y] == 0)
			return;
		visited[x][y] = true;

		addAll(allList, x - ox, y - oy);

		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		for (int i = 0; i < dirs.length; ++i) {
			int nextx = x + dirs[i][0];
			int nexty = y + dirs[i][1];
			dfs(grid, nextx, nexty, visited, ox, oy, allList);
		}
	}
}
