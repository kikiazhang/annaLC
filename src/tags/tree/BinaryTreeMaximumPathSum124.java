package tags.tree;

import tags.TreeNode;

/**
 * 
 * Given a non-empty binary tree, find the maximum path sum.
 * 
 * For this problem, a path is defined as any sequence of nodes from some
 * starting node to any node in the tree along the parent-child connections. The
 * path must contain at least one node and does not need to go through the root.
 * 
 * Example 1:
 * 
 * Input: [1,2,3]
 * 
 * 1 / \ 2 3
 * 
 * Output: 6 Example 2:
 * 
 * Input: [-10,9,20,null,null,15,7]
 * 
 * -10 / \ 9 20 / \ 15 7
 * 
 * Output: 42
 */
public class BinaryTreeMaximumPathSum124 {
	int max = Integer.MIN_VALUE;

	private int helper(TreeNode root) {
		if (root == null)
			return 0;
		int left = Math.max(helper(root.left), 0);
		int right = Math.max(helper(root.right), 0);
		// cur是全要 the parent-child connections
		int cur = root.val + left + right;
		max = Math.max(max, cur);
		// 向上走只能选left right一边
		return root.val + Math.max(left, right);
	}

	public int maxPathSum(TreeNode root) {
		helper(root);
		return max;
	}

}
