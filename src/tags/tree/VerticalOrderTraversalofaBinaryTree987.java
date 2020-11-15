package tags.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import tags.TreeNode;

/**
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * 
 * For each node at position (X, Y), its left and right children respectively
 * will be at positions (X-1, Y-1) and (X+1, Y-1).
 * 
 * Running a vertical line from X = -infinity to X = +infinity, whenever the
 * vertical line touches some nodes, we report the values of the nodes in order
 * from top to bottom (decreasing Y coordinates).
 * 
 * If two nodes have the same position, then the value of the node that is
 * reported first is the value that is smaller.
 * 
 * Return an list of non-empty reports in order of X coordinate. Every report
 * will have a list of values of nodes.
 * 
 * Input: [3,9,20,null,null,15,7] Output: [[9],[3,15],[20],[7]]
 * 
 * Input: [1,2,3,4,5,6,7] Output: [[4],[2],[1,5,6],[3],[7]]
 */
public class VerticalOrderTraversalofaBinaryTree987 {
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();// col, map<row, pq<node.val>>
		dfs(root, 0, 0, map);
		List<List<Integer>> list = new ArrayList<>();
		for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
			list.add(new ArrayList<>());
			for (PriorityQueue<Integer> nodes : ys.values()) {
				while (!nodes.isEmpty()) {
					list.get(list.size() - 1).add(nodes.poll());
				}
			}
		}
		return list;
	}

	private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
		if (root == null) {
			return;
		}
		if (!map.containsKey(x)) {
			map.put(x, new TreeMap<>());
		}
		if (!map.get(x).containsKey(y)) {
			map.get(x).put(y, new PriorityQueue<>());
		}
		map.get(x).get(y).offer(root.val);
		dfs(root.left, x - 1, y + 1, map);
		dfs(root.right, x + 1, y + 1, map);
	}

	// pair with map o(nlog(n/k))
	Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap<>();
	int minColumn = 0, maxColumn = 0;

	public List<List<Integer>> verticalTraversal2(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null)
			return res;

		helper(root, 0, 0);
		for (int i = minColumn; i <= maxColumn; i++) {
			Collections.sort(columnTable.get(i), ((a, b) -> {
				if (a.getKey().equals(b.getKey())) {
					return a.getValue() - b.getValue();
				} else {
					return a.getKey() - b.getKey();
				}
			}));
			List<Integer> sortedCol = new ArrayList<>();
			for (Pair<Integer, Integer> p : columnTable.get(i)) {
				sortedCol.add(p.getValue());
			}
			res.add(sortedCol);
		}
		return res;
	}

	private void helper(TreeNode root, Integer row, Integer column) {
		if (root == null)
			return;

		if (!columnTable.containsKey(column)) {
			columnTable.put(column, new ArrayList<>());
		}
		columnTable.get(column).add(new Pair<Integer, Integer>(row, root.val));
		minColumn = Math.min(minColumn, column);
		maxColumn = Math.max(maxColumn, column);
		helper(root.left, row + 1, column - 1);
		helper(root.right, row + 1, column + 1);
	}

	class Pair<K, V> {

		private K key;

		public K getKey() {
			return key;
		}

		private V value;

		public V getValue() {
			return value;
		}

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

}
