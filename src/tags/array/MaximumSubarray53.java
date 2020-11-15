package tags.array;

/**
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest sum and return its sum.
 * 
 * Example:
 * 
 * Input: [-2,1,-3,4,-1,2,1,-5,4], Output: 6 Explanation: [4,-1,2,1] has the
 * largest sum = 6.
 */
public class MaximumSubarray53 {
	// o(n) greedy
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int currSum = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			currSum = Math.max(nums[i], currSum + nums[i]);
			max = Math.max(max, currSum);
		}
		return max;
	}

	// o(n) dp
	public int maxSubArray2(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int max = nums[0];
		// nums[i]记录到这里为止的max sum（nums[i-1] > 0说明能加入，不然就为自己）
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] > 0)
				nums[i] += nums[i - 1];
			max = Math.max(max, nums[i]);
		}
		return max;
	}

	// dp
	public int maxSubArray3(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int[] dp = new int[nums.length + 1];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (dp[i] > 0) {
				dp[i + 1] = dp[i] + nums[i];
			} else {
				dp[i + 1] = nums[i];
			}
			max = Math.max(max, dp[i + 1]);
		}
		return max;
	}
}
