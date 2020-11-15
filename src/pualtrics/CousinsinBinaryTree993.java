package pualtrics;

import java.util.LinkedList;
import java.util.Queue;

import tags.TreeNode;

/**
 * In a binary tree, the root node is at depth 0, and children of each depth k
 * node are at depth k+1.
 * 
 * Two nodes of a binary tree are cousins if they have the same depth, but have
 * different parents.
 * 
 * We are given the root of a binary tree with unique values, and the values x
 * and y of two different nodes in the tree.
 * 
 * Return true if and only if the nodes corresponding to the values x and y are
 * cousins.
 * 
 * 
 */
public class CousinsinBinaryTree993 {
	public boolean isCousins(TreeNode root, int x, int y) {
		if (root == null)
			return false;
		// bfs
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			// same level find x and y is true
			boolean findX = false;
			boolean findY = false;
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();
				if (cur.val == x)
					findX = true;
				if (cur.val == y)
					findY = true;
				if (cur.left != null && cur.right != null) {
					// same parent is false
					if (cur.left.val == x && cur.right.val == y)
						return false;
					if (cur.left.val == y && cur.right.val == x)
						return false;
				}
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
			if (findX && findY)
				return true;
		}
		return false;
	}
}
