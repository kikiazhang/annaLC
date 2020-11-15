package amazonOA;

import java.util.HashSet;

import freq.five.TreeNode;

public class SubtreeAnotherTree {
	/**
	 * 
	 * Given two non-empty binary trees s and t, check whether tree t has exactly
	 * the same structure and node values with a subtree of s. A subtree of s is a
	 * tree consists of a node in s and all of this node's descendants. The tree s
	 * could also be considered as a subtree of itself.
	 * 
	 * Example 1: Given tree s:
	 * 
	 * 3 / \ 4 5 / \ 1 2 Given tree t: 4 / \ 1 2 Return true, because t has the same
	 * structure and node values with a subtree of s.
	 * 
	 */
	/**
	 * Time complexity : O(m^2+n^2+m*n)O(m 2 +n 2 +m∗n). A total of nn nodes of the
	 * tree ss and mm nodes of tree tt are traversed. Assuming string concatenation
	 * takes O(k)O(k) time for strings of length kk and indexOf takes O(m*n)O(m∗n).
	 * 
	 * Space complexity : O(max(m,n))O(max(m,n)). The depth of the recursion tree
	 * can go upto nn for tree tt and mm for tree ss in worst case.
	 */
	HashSet<String> trees = new HashSet<>();

	public boolean isSubtree(TreeNode s, TreeNode t) {
		String tree1 = preorder(s, true);
		String tree2 = preorder(t, true);
		return tree1.indexOf(tree2) >= 0;
	}

	public String preorder(TreeNode t, boolean left) {
		if (t == null) {
			if (left)
				return "lnull";
			else
				return "rnull";
		}
		return "#" + t.val + " " + preorder(t.left, true) + " " + preorder(t.right, false);
	}

	/**
	 * Time complexity : O(m*n)O(m∗n). In worst case(skewed tree) traverse function
	 * takes O(m*n)O(m∗n) time.
	 * 
	 * Space complexity : O(n)O(n). The depth of the recursion tree can go upto nn.
	 * nn refers to the number of nodes in ss.
	 */
	public boolean isSubtree2(TreeNode s, TreeNode t) {
		return traverse(s, t);
	}

	public boolean equals(TreeNode x, TreeNode y) {
		if (x == null && y == null)
			return true;
		if (x == null || y == null)
			return false;
		return x.val == y.val && equals(x.left, y.left) && equals(x.right, y.right);
	}

	public boolean traverse(TreeNode s, TreeNode t) {
		return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
	}
}
