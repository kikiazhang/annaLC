package tags.binarySearch;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 * 
 * Example:
 * 
 * Input: [10,9,2,5,3,7,101,18] Output: 4 Explanation: The longest increasing
 * subsequence is [2,3,7,101], therefore the length is 4. Note:
 * 
 * There may be more than one LIS combination, it is only necessary for you to
 * return the length. Your algorithm should run in O(n2) complexity. Follow up:
 * Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence300 {
	// dp o(n^2)
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int[] dp = new int[nums.length];
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			int maxval = 0;// i前面的 max LIS
			for (int j = 0; j < i; j++) {
				// 说明可以dp j + 1 == dp i
				if (nums[i] > nums[j]) {
					maxval = Math.max(maxval, dp[j]);
				}
			}
			dp[i] = maxval + 1;
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	// o(nlogn)
	public static int lengthOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int[] dp = new int[nums.length];
		System.out.println(Arrays.binarySearch(nums, 0, 4, 4));
		int max = 0;
		for (int num : nums) {
			int i = Arrays.binarySearch(dp, 0, max, num);// arr, [from, to), target
			// 如果找不到，就返回 - insertPoint - 1
			if (i < 0) {
				// 找到insert的index
				i = -(i + 1);
			}
			dp[i] = num;
			// 说明到长度该变大
			if (i == max) {
				max++;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 8, 4, 12 };
		lengthOfLIS2(nums);
	}
}
