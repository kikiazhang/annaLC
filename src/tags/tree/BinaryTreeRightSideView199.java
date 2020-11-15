package tags.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import tags.TreeNode;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * 
 * Example:
 * 
 * Input: [1,2,3,null,5,null,4] Output: [1, 3, 4]
 */
public class BinaryTreeRightSideView199 {
	// dfs o(n)
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		helper(root, res, 0);
		return res;
	}

	private void helper(TreeNode root, List<Integer> res, int level) {
		if (root == null)
			return;
		if (level == res.size())// 首先到的一定是最右
			res.add(root.val);
		// 先右后左进入下一层
		if (root.right != null)
			helper(root.right, res, level + 1);
		if (root.left != null)
			helper(root.left, res, level + 1);
	}

	// bfs
	public List<Integer> rightSideView2(TreeNode root) {
		if (root == null)
			return new ArrayList<Integer>();

		ArrayDeque<TreeNode> queue = new ArrayDeque<>() {
			{
				offer(root);
			}
		};
		List<Integer> rightside = new ArrayList<>();

		while (!queue.isEmpty()) {
			int levelLength = queue.size();

			for (int i = 0; i < levelLength; ++i) {
				TreeNode node = queue.poll();
				// if it's the rightmost element
				if (i == levelLength - 1) {
					rightside.add(node.val);
				}

				// add child nodes in the queue
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
		return rightside;
	}
}
