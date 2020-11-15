package google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5] Output:
 * [[1,2,null,4],[6],[7]]
 * 
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * 
 * After deleting all nodes with a value in to_delete, we are left with a forest
 * (a disjoint union of trees).
 * 
 * Return the roots of the trees in the remaining forest. You may return the
 * result in any order.
 * 
 * 
 */
public class DeleteNodesAndReturnForest1110 {
	Set<Integer> to_delete_set = new HashSet<>();
	List<TreeNode> res = new ArrayList<>();

	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		for (int node : to_delete) {
			to_delete_set.add(node);
		}
		helper(root, true);
		return res;
	}

	private TreeNode helper(TreeNode node, boolean isRoot) {
		if (node == null)
			return null;

		boolean delete = to_delete_set.contains(node.val);
		if (isRoot & !delete)
			res.add(node);// only save roots
		node.left = helper(node.left, delete);// if root is deleted, then left is new root
		node.right = helper(node.right, delete);// if root is deleted, then right is new root

		return delete ? null : node;
	}
}
