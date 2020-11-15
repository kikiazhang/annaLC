package tags.dfs.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import tags.TreeNode;

/**
 * We are given a binary tree (with root node root), a target node, and an
 * integer value K.
 * 
 * Return a list of the values of all nodes that have a distance K from the
 * target node. The answer can be returned in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 
 * Output: [7,4,1]
 * 
 * Explanation: The nodes that are a distance 2 from the target node (with value
 * 5) have values 7, 4, and 1.
 * 
 */
public class AllNodesDistanceKinBinaryTree863 {
	// o（n）
	List<Integer> res = new ArrayList<>();

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

		helper(root, target, K);
		return res;
	}

	// From root, say the target node is at depth 3 in the left branch. It means
	// that any nodes that are distance K - 3 in the right branch should be added to
	// the answer.
	private int helper(TreeNode root, TreeNode target, int K) {
		if (root == null) {
			return -1;
		} else if (root == target) {
			// 从target开始，dist=0，他自己是1
			subTreeAdd(root, 0, K);
			return 1;
		} else {
			// 如果root不是，那么看左右，target一定在左或者右
			int left = helper(root.left, target, K);
			int right = helper(root.right, target, K);
			// left、right!=-1说明为target在那一侧， 一侧为L+1，那么另一侧是k-L-1
			if (left != -1) {
				if (left == K) // root距离target为k
					res.add(root.val);
				subTreeAdd(root.right, left + 1, K);
				return left + 1;
			} else if (right != -1) {
				if (right == K)
					res.add(root.val);
				subTreeAdd(root.left, right + 1, K);
				return right + 1;
			} else {
				return -1;
			}
		}
	}

	// 向下找，每次+1
	private void subTreeAdd(TreeNode node, int dist, int K) {
		if (node == null)
			return;
		if (dist == K) {
			res.add(node.val);
		} else {
			subTreeAdd(node.left, dist + 1, K);
			subTreeAdd(node.right, dist + 1, K);
		}
	}

	// bfs
	Map<TreeNode, TreeNode> parent;// node, parent node

	public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
		parent = new HashMap<>();
		dfs(root, null);// save all node with parent

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(null);
		queue.add(target);

		Set<TreeNode> seen = new HashSet<>();
		seen.add(target);
		seen.add(null);

		int dist = 0;
		// start at target to find K steps
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			// 说明一步能到的结束了，dist++
			if (node == null) {
				if (dist == K) {// 此时所有queue里的都是k steps
					List<Integer> ans = new ArrayList<>();
					for (TreeNode n : queue)
						ans.add(n.val);
					return ans;
				}
				queue.offer(null);
				dist++;
			} else {
				// 保存这个点的左右和parent，这是他one step能到的
				if (!seen.contains(node.left)) {
					seen.add(node.left);
					queue.offer(node.left);
				}
				if (!seen.contains(node.right)) {
					seen.add(node.right);
					queue.offer(node.right);
				}
				TreeNode par = parent.get(node);
				if (!seen.contains(par)) {
					seen.add(par);
					queue.offer(par);
				}
			}
		}

		return new ArrayList<Integer>();
	}

	// save all node,parent pair into hashMap
	public void dfs(TreeNode node, TreeNode par) {
		if (node != null) {
			parent.put(node, par);
			dfs(node.left, node);
			dfs(node.right, node);
		}
	}
}
