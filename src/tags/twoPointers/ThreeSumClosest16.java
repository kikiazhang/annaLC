package tags.twoPointers;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers
 * in nums such that the sum is closest to target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [-1,2,1,-4], target = 1 Output: 2 Explanation: The sum that is
 * closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest16 {
	// o(n^2)
	public int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return 0;

		int diff = Integer.MAX_VALUE;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			int l = i + 1, r = nums.length - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (Math.abs(sum - target) < Math.abs(diff)) {// 注意abs
					diff = target - sum;
				}
				if (sum < target) {
					l++;
				} else {
					r--;
				}
			}
		}
		return target - diff;// 注意diff计算的顺序（target-sum）
	}
}
