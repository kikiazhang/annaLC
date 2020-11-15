package tags.binarySearch;

/**
 * Given an array which consists of non-negative integers and an integer m, you
 * can split the array into m non-empty continuous subarrays. Write an algorithm
 * to minimize the largest sum among these m subarrays.
 * 
 * Note: If n is the length of array, assume the following constraints are
 * satisfied:
 * 
 * 1 ≤ n ≤ 1000 1 ≤ m ≤ min(50, n) Examples:
 * 
 * Input: nums = [7,2,5,10,8] m = 2
 * 
 * Output: 18
 */
public class SplitArrayLargestSum410 {
	// binary search + greedy o(nlogm) m是[l, r]的个数
	public int splitArray(int[] nums, int m) {
		long l = 0;// min
		long r = 0;// sum
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			r += nums[i];
			if (l < nums[i]) {
				l = nums[i];
			}
		}
		long ans = r;
		// 所有能组成的sum是[l, r]
		while (l <= r) {
			long mid = (l + r) >> 1;// 默认会有一半在左，一半在右
			long sum = 0;// 到i为止的subsum
			int cnt = 1;// sum比mid大，就sublist个数++
			for (int i = 0; i < n; i++) {
				// 再加i 在右边， cnt++，sum断开只要当前
				if (sum + nums[i] > mid) {
					cnt++;
					sum = nums[i];
				} else {
					sum += nums[i];
				}
			}
			// 出现的sublist个数 小于 m， 减小mid
			if (cnt <= m) {
				ans = Math.min(ans, mid);
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return (int) ans;
	}
}
