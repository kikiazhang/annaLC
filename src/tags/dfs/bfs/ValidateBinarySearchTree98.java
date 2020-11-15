package tags.dfs.bfs;

import tags.TreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's
 * key. The right subtree of a node contains only nodes with keys greater than
 * the node's key. Both the left and right subtrees must also be binary search
 * trees. Input: [2,1,3] Output: true Input: [5,1,4,null,null,3,6] Output: false
 */
public class ValidateBinarySearchTree98 {
	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;

		return helper(root, null, null);
	}

	private boolean helper(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;
		if (max != null && root.val >= max)
			return false;
		if (min != null && root.val <= min)
			return false;
		boolean left = helper(root.left, min, root.val);
		boolean right = helper(root.right, root.val, max);
		return left && right;
	}
}
