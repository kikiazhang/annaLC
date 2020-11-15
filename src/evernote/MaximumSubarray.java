package evernote;

public class MaximumSubarray {
	/**
	 * Given an integer array nums, find the contiguous subarray (containing at
	 * least one number) which has the largest sum and return its sum.
	 * 
	 * Example:
	 * 
	 * Input: [-2,1,-3,4,-1,2,1,-5,4], Output: 6 Explanation: [4,-1,2,1] has the
	 * largest sum = 6.
	 */
	public int maxSubArray(int[] nums) {
		return helper(nums, 0, nums.length - 1);
	}

	private int helper(int[] nums, int left, int right) {
		if (left == right) {
			return nums[left];
		}

		int p = (left + right) / 2;

		int leftSum = helper(nums, left, p);
		int rightSum = helper(nums, p + 1, right);
		int crossSum = crossSum(nums, left, right, p);// 有left 也有right的sub

		return Math.max(Math.max(leftSum, rightSum), crossSum);
	}

	private int crossSum(int[] nums, int left, int right, int p) {
		if (left == right) {
			return nums[left];
		}

		int leftSubsum = Integer.MIN_VALUE;
		int currSum = 0;
		// 往左走，找到最大sum
		for (int i = p; i > left - 1; --i) {
			currSum += nums[i];
			leftSubsum = Math.max(leftSubsum, currSum);
		}
		// 往右走，找到最大sum
		int rightSubsum = Integer.MIN_VALUE;
		currSum = 0;
		for (int i = p + 1; i < right + 1; ++i) {
			currSum += nums[i];
			rightSubsum = Math.max(rightSubsum, currSum);
		}
		// sum
		return leftSubsum + rightSubsum;
	}
}
