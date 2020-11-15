package tags.binarySearch;

/**
 * Given an array of integers nums sorted in ascending order, find the starting
 * and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * Example 1:
 * 
 * Input: nums = [5,7,7,8,8,10], target = 8 Output: [3,4] Example 2:
 * 
 * Input: nums = [5,7,7,8,8,10], target = 6 Output: [-1,-1]
 */
public class FindFirstAndLastPositionOfElementInSortedArray34 {
	// o(logn)
	public int[] searchRange(int[] nums, int target) {
		int[] res = { -1, -1 };

		int left = helper(nums, target, true);
		// can't find
		if (left == nums.length || nums[left] != target) {
			return res;
		}
		res[0] = left;
		res[1] = helper(nums, target, false) - 1;

		return res;
	}

	// flexable check mid == target in find left or find right
	private int helper(int[] nums, int target, boolean isLeft) {
		int left = 0, right = nums.length;

		while (left < right) {
			int mid = (left + right) / 2;
			if (nums[mid] > target || (isLeft && target == nums[mid])) {
				right = mid;// find left(when target == mid, still right--) || mid > target
			} else {
				left = mid + 1;// target > mid or find right(mid == target)
			}
		}
		return left;
	}
}
