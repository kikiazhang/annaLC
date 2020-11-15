package tags.other;

import java.util.HashSet;
import java.util.Set;

import tags.TreeNode;

/**
 * Given the root of a Binary Search Tree and a target number k, return true if
 * there exist two elements in the BST such that their sum is equal to the given
 * target. Input: root = [5,3,6,2,4,null,7], k = 9 Output: true Input: root =
 * [5,3,6,2,4,null,7], k = 28 Output: false Input: root = [2,1,3], k = 4 Output:
 * true
 */
public class TwoSumIV653 {
	public boolean findTarget(TreeNode root, int k) {
		Set<Integer> set = new HashSet<>();
		return helper(root, k, set);
	}

	private boolean helper(TreeNode root, int k, Set<Integer> set) {
		if (root == null)
			return false;
		if (set.contains(root.val))
			return true;
		set.add(k - root.val);
		return helper(root.left, k, set) || helper(root.right, k, set);
	}
}
