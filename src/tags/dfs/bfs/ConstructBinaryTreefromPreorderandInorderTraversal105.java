package tags.dfs.bfs;

import tags.TreeNode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * For example, given
 * 
 * preorder = [3,9,20,15,7] inorder = [9,3,15,20,7] Return the following binary
 * tree:
 * 
 * 3 / \ 9 20 / \ 15 7
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal105 {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private TreeNode buildTree(int[] pre, int preSt, int preEd, int[] in, int inSt, int inEd) {
		if (preSt > preEd || inSt > inEd) {
			return null;
		}
		int val = pre[preSt];// pre第一位是cur
		int rootIndex = 0;// 找到in中的cur所在
		for (int i = inSt; i <= inEd; i++) {
			if (in[i] == val) {
				rootIndex = i;
				break;
			}
		}
		int len = rootIndex - inSt;// 左边子树的总长度为：rootIndex-inSt
		TreeNode node = new TreeNode(val);
		node.left = buildTree(pre, preSt + 1, preSt + len, in, inSt, rootIndex - 1);// 左子树
		node.right = buildTree(pre, preSt + len + 1, preEd, in, rootIndex + 1, inEd);// 右子树
		return node;
	}
}
