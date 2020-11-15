package tags.divideConquer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
 * number on it represented by array nums. You are asked to burst all the
 * balloons. If the you burst balloon i you will get nums[left] * nums[i] *
 * nums[right] coins. Here left and right are adjacent indices of i. After the
 * burst, the left and right then becomes adjacent.
 * 
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * 
 * Note:
 * 
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can
 * not burst them. 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100 Example:
 * 
 * Input: [3,1,5,8] Output: 167 Explanation: nums = [3,1,5,8] --> [3,5,8] -->
 * [3,8] --> [8] --> [] coins = 3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 = 167
 */
public class BurstBalloons312 {
	// time limit exceeded
	int max = 0;

	public int maxCoins(int[] nums) {
		List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
		helper(list, 0);
		return max;
	}

	private void helper(List<Integer> list, int cur) {
		if (list.size() == 0) {
			max = Math.max(max, cur);
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			int sum = (i == 0 ? 1 : list.get(i - 1)) * list.get(i) * (i == list.size() - 1 ? 1 : list.get(i + 1));
			int tmp = list.get(i);
			list.remove(i);
			helper(list, cur + sum);
			list.add(i, tmp);
		}
	}

	// divided and conquer
	public int maxCoins2(int[] iNums) {
		int[] nums = new int[iNums.length + 2];
		int n = 1;
		// 先记录自己
		for (int x : iNums)
			if (x > 0)
				nums[n++] = x;
		// 两头是1，防止越界
		nums[0] = nums[n++] = 1;
		// dp记录算过的sum， n = len
		int[][] memo = new int[n][n];
		return burst(memo, nums, 0, n - 1);
	}

	public int burst(int[][] memo, int[] nums, int left, int right) {
		// 没有中间的index
		if (left + 1 == right)
			return 0;
		// 计算过了，直接返回
		if (memo[left][right] > 0)
			return memo[left][right];
		// 此时最大ans
		int ans = 0;
		// 默认i是最有一个戳破的气球，所以左右可以先计算，不会互相影响
		for (int i = left + 1; i < right; ++i)
			ans = Math.max(ans,
					nums[left] * nums[i] * nums[right] + burst(memo, nums, left, i) + burst(memo, nums, i, right));
		memo[left][right] = ans;
		return ans;
	}

	// dp
	public int maxCoins3(int[] iNums) {
		int[] nums = new int[iNums.length + 2];
		int n = 1;
		for (int x : iNums)
			if (x > 0)
				nums[n++] = x;
		nums[0] = nums[n++] = 1;

		int[][] dp = new int[n][n];
		for (int k = 2; k < n; ++k)
			for (int left = 0; left < n - k; ++left) {
				int right = left + k;
				// 默认i是最有一个戳破的气球，所以左右可以先计算，不会互相影响
				for (int i = left + 1; i < right; ++i)
					dp[left][right] = Math.max(dp[left][right],
							nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
			}

		return dp[0][n - 1];
	}
}
