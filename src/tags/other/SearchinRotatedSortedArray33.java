package tags.other;

/**
 * You are given an integer array nums sorted in ascending order, and an integer
 * target.
 * 
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e.,
 * [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * 
 * If target is found in the array return its index, otherwise, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [4,5,6,7,0,1,2], target = 0 Output: 4 Example 2:
 * 
 * Input: nums = [4,5,6,7,0,1,2], target = 3 Output: -1 Example 3:
 * 
 * Input: nums = [1], target = 0 Output: -1
 */
public class SearchinRotatedSortedArray33 {
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;

		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] >= nums[left]) {// left sorted
				if (target >= nums[left] && target < nums[mid])
					right = mid - 1;// target inside left sorted part
				else
					left = mid + 1;
			} else {// right sorted
				if (target <= nums[right] && target > nums[mid])
					left = mid + 1;// target inside right sorted part
				else
					right = mid - 1;
			}
		}
		return -1;
	}
}
