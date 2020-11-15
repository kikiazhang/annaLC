package freq.five;

public class ValidateBinarySearchTree {

	/**
	 * Assume a BST is defined as follows:
	 * 
	 * The left subtree of a node contains only nodes with keys less than the node's
	 * key. The right subtree of a node contains only nodes with keys greater than
	 * the node's key. Both the left and right subtrees must also be binary search
	 * trees.
	 * 
	 */
	public boolean isValidBST(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return true;

		return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean helper(TreeNode root, long min, long max) {
		if (root == null) {
			return true;
		} else {
			if (root.val <= min || root.val >= max)
				return false;
			boolean left = helper(root.left, min, root.val);
			boolean right = helper(root.right, root.val, max);
			return left & right;
		}
	}
}
