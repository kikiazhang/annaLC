package tags.twoPointers;

/**
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
 * one, return 0 instead.
 * 
 * Example:
 * 
 * Input: s = 7, nums = [2,3,1,2,4,3] Output: 2 Explanation: the subarray [4,3]
 * has the minimal length under the problem constraint. Follow up: If you have
 * figured out the O(n) solution, try coding another solution of which the time
 * complexity is O(n log n).
 */
public class MinimumSizeSubarraySum209 {
	// o(n) sp: o(1)
	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int st = 0, end = 0;
		int sum = 0;
		int max = Integer.MAX_VALUE;
		while (end < nums.length) {
			sum += nums[end++];
			while (st < end && sum >= s) {
				max = Math.min(max, end - st);
				sum -= nums[st++];
			}
		}
		return max == Integer.MAX_VALUE ? 0 : max;
	}
}
