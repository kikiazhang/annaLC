package tags.dynamicProgramming;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money
 * amount. Write a function to compute the fewest number of coins that you need
 * to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * 
 * Example 1:
 * 
 * Input: coins = [1, 2, 5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * 
 * Input: coins = [2], amount = 3 Output: -1
 */
public class CoinChange322 {
	// dp o(s*n)
	public int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0)
			return -1;

		int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);// 最大不会超过amount
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (i >= coins[j]) {// 如果能用coin j组成i，那么减去coin j
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];// 能组成就是dp，不能就是-1
	}
}
