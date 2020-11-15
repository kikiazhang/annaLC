package freq.four;

import freq.five.TreeNode;

public class SumRootToLeafNumbers {
	/**
	 * Input: [1,2,3]
		    1
		   / \
		  2   3
		Output: 25
		Explanation:
		The root-to-leaf path 1->2 represents the number 12.
		The root-to-leaf path 1->3 represents the number 13.
		Therefore, sum = 12 + 13 = 25.
	 * */
	int sum = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        
        helper(root, 0);
        return sum;
    }
    public void helper(TreeNode node, int curSum){
        if(node.left == null && node.right == null) {
            sum += curSum*10 + node.val;
            return;
        } else {
            if(node.left != null ) helper(node.left, curSum*10 + node.val);
            if(node.right != null) helper(node.right, curSum*10 + node.val);
        }
    }
    //solution 2
	public int sumNumbers2(TreeNode root) {
		if (root == null)
			return 0;
		return helper2(root, 0);
	}

	public int helper2(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		// 到leaf
		if (root.left == null && root.right == null)
			return sum * 10 + root.val;
		// 没到leaf，就计算两边
		return helper2(root.left, sum * 10 + root.val) + helper2(root.right, sum * 10 + root.val);
	}
}
