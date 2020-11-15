package tags.unionFind;

import java.util.Arrays;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to find the number of connected
 * components in an undirected graph.
 * 
 * Example 1:
 * 
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * 
 * 0 		3 
 * |	 	| 
 * 1 --- 2  4
 * 
 * Output: 2 Example 2:
 * 
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 * 
 * 0 			4 
 * |			| 
 * 1 --- 2 --- 3
 * 
 * Output: 1
 */
public class NumberofConnectedComponentsinanUndirectedGraph323 {
	public int countComponents(int n, int[][] edges) {
		Union union = new Union(n);
		for (int i = 0; i < edges.length; i++) {
			union.union(edges[i][0], edges[i][1]);
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (union.parent[i] == -1) {
				count++;
			}
		}
		return count;
	}

	class Union {
		int[] parent;

		public Union(int n) {
			parent = new int[n];
			Arrays.fill(parent, -1);
		}

		public int find(int i) {
			if (parent[i] == -1)
				return i;
			return find(parent[i]);
		}

		public void union(int a, int b) {
			int pa = find(a);
			int pb = find(b);
			if (pa != pb) {
				parent[pb] = pa;
			}
		}
	}
}
