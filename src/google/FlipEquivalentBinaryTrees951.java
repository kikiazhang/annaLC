package google;

/**
 * For a binary tree T, we can define a flip operation as follows: choose any
 * node, and swap the left and right child subtrees.
 * 
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can
 * make X equal to Y after some number of flip operations.
 * 
 * Write a function that determines whether two binary trees are flip
 * equivalent. The trees are given by root nodes root1 and root2.
 * 
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 =
 * [1,3,2,null,6,4,5,null,null,null,null,8,7] Output: true Explanation: We
 * flipped at nodes with values 1, 3, and 5.
 */
public class FlipEquivalentBinaryTrees951 {
	/**
	 * Time Complexity: O(min(N_1, N_2))O(min(N 1 ​ ,N 2 ​ )), where N_1, N_2N 1 ​
	 * ,N 2 ​ are the lengths of root1 and root2.
	 * 
	 * Space Complexity: O(min(H_1, H_2))O(min(H 1 ​ ,H 2 ​ )), where H_1, H_2H 1 ​
	 * ,H 2 ​ are the heights of the trees of root1 and root2.
	 */
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
		return helper(root1, root2);
	}

	private boolean helper(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null || root1.val != root2.val)
			return false;

		return (helper(root1.left, root2.right) && helper(root1.right, root2.left))
				|| (helper(root1.right, root2.right) && helper(root1.left, root2.left));
	}
}
