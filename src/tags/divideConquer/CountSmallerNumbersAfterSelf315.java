package tags.divideConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * Example:
 * 
 * Input: [5,2,6,1] Output: [2,1,1,0] Explanation: To the right of 5 there are 2
 * smaller elements (2 and 1). To the right of 2 there is only 1 smaller element
 * (1). To the right of 6 there is 1 smaller element (1). To the right of 1
 * there is 0 smaller element.
 */
public class CountSmallerNumbersAfterSelf315 {
	// time limit exceeded o(n^2)
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return res;

		Integer[] dp = new Integer[nums.length];
		for (int i = nums.length - 1; i >= 0; i--) {
			int count = 0;
			for (int j = nums.length - 1; j > i; j--) {
				if (nums[i] > nums[j]) {
					count++;
				}
			}
			dp[i] = count;
		}
		return Arrays.asList(dp);
	}
	
	// create node for BST
	public List<Integer> countSmaller2(int[] nums) {
		Integer[] ans = new Integer[nums.length];
		Node root = null;

		for (int i = nums.length - 1; i >= 0; i--) {
			root = insert(nums[i], root, ans, i, 0);
		}
		return Arrays.asList(ans);
	}

	private Node insert(int num, Node node, Integer[] ans, int index, int preSum) {
		if (node == null) {
			// any new node has no left bot node, then sum will be 0
			node = new Node(num, 0);
			ans[index] = preSum;
		} else if (node.val == num) {
			node.times++;
			ans[index] = preSum + node.sum;
		} else if (node.val > num) {
			// one more left bot
			node.sum++;
			node.left = insert(num, node.left, ans, index, preSum);
		} else {
			node.right = insert(num, node.right, ans, index, node.sum + node.times + preSum);
		}
		return node;
	}

	class Node {
		int times = 1;// how many times the node appear
		int sum;// how many node to its left bottom
		int val;// node val
		Node left;
		Node right;

		public Node(int val, int sum) {
			this.val = val;
			this.sum = sum;
		}
	}
}
