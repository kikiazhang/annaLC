package tags.design;

import java.util.Stack;

import tags.TreeNode;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST. BSTIterator
 * iterator = new BSTIterator(root); iterator.next(); // return 3
 * iterator.next(); // return 7 iterator.hasNext(); // return true
 * iterator.next(); // return 9 iterator.hasNext(); // return true
 * iterator.next(); // return 15 iterator.hasNext(); // return true
 * iterator.next(); // return 20 iterator.hasNext(); // return false
 */
public class BinarySearchTreeIterator173 {
	Stack<TreeNode> stack;

	public BinarySearchTreeIterator173(TreeNode root) {
        stack = new Stack<>();
        toLeftMost(root);
    }

	private void toLeftMost(TreeNode root) {
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode node = stack.pop();
		if (node.right != null) {
			toLeftMost(node.right);
		}
		return node.val;
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}
}
