package tags.tree;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store
 * values 1 ... n?
 * 
 * Example:
 * 
 * Input: 3 Output: 5 Explanation: Given n = 3, there are a total of 5 unique
 * BST's:
 */
public class UniqueBinarySearchTrees96 {
	// dp o(n^2)
	public int numTrees(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; ++i) {
			for (int j = 1; j <= i; ++j) {
				// dp[j-1] as left sub tree, dp[i-j] as right sub tree, there could be i root
				dp[i] += dp[j - 1] * dp[i - j];
			}
		}
		return dp[n];
	}
}
