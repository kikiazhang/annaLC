package pualtrics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tags.TreeNode;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 * For example: Given binary tree [3,9,20,null,null,15,7], 3 / \ 9 20 / \ 15 7
 * return its zigzag level order traversal as: [ [3], [20,9], [15,7] ]
 */
public class BinaryTreeZigzagLevelOrderTraversal103 {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		boolean even = true;
		while (!q.isEmpty()) {
			int size = q.size();
			LinkedList<Integer> layer = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node = q.poll();
				if (node.left != null)
					q.offer(node.left);
				if (node.right != null)
					q.offer(node.right);
				if (even) {
					layer.add(node.val);
				} else {
					layer.add(0, node.val);
				}
			}
			even = !even;
			res.add(layer);
		}
		return res;
	}

	// dfs
	protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
		if (level >= results.size()) {
			LinkedList<Integer> newLevel = new LinkedList<Integer>();
			newLevel.add(node.val);
			results.add(newLevel);
		} else {
			if (level % 2 == 0)
				results.get(level).add(node.val);
			else
				results.get(level).add(0, node.val);
		}

		if (node.left != null)
			DFS(node.left, level + 1, results);
		if (node.right != null)
			DFS(node.right, level + 1, results);
	}

	public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
		if (root == null) {
			return new ArrayList<List<Integer>>();
		}
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		DFS(root, 0, results);
		return results;
	}
}
