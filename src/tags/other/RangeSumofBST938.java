package tags.other;

import tags.TreeNode;

/**
 * Given the root node of a binary search tree, return the sum of values of all
 * nodes with value between L and R (inclusive).
 * 
 * The binary search tree is guaranteed to have unique values.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15 Output: 32 Example 2:
 * 
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10 Output: 23
 */
public class RangeSumofBST938 {
	int sum = 0;

	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null)
			return 0;

		helper(root, L, R);
		return sum;
	}

	private void helper(TreeNode root, int L, int R) {
		if (root != null) {
			if (root.val <= R && root.val >= L)
				sum += root.val;
			if (L < root.val) {
				helper(root.left, L, R);
			}
			if (root.val < R) {
				helper(root.right, L, R);
			}
		}
	}
}
