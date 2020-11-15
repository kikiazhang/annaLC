package tags.binarySearch;

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
	// o(n) sp: o(1) two pointers(sliding windows)
	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int st = 0, end = 0;
		int sum = 0;// sum to the current
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

	// binary search o(nlogn)
	private int solveNLogN(int s, int[] nums) {
		int[] sums = new int[nums.length + 1];// 0-cur 的sum
		for (int i = 1; i < sums.length; i++)
			sums[i] = sums[i - 1] + nums[i - 1];// index i时是到i-1的sum
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < sums.length; i++) {// 需要len次，因为target每次为s+sum i
			int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);// target变成了s+sum i，计算i到len有没有
			if (end == sums.length)// 没有
				break;
			if (end - i < minLen)
				minLen = end - i;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	private int binarySearch(int lo, int hi, int key, int[] sums) {
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (sums[mid] >= key) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return lo;
	}
}
