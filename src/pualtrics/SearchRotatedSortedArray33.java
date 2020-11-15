package pualtrics;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * Example 1:
 * 
 * Input: nums = [4,5,6,7,0,1,2], target = 0 Output: 4 Example 2:
 * 
 * Input: nums = [4,5,6,7,0,1,2], target = 3 Output: -1
 */
public class SearchRotatedSortedArray33 {
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

	// 81 have dul
	public boolean search2(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = (right + left) / 2;
			if (nums[mid] == target)
				return true;
			if (nums[mid] < nums[right]) {// 有序右边
				if (nums[mid] < target && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else if (nums[mid] > nums[right]) {// 有序左边
				if (nums[mid] > target && target >= nums[left]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {// 出现dul，都往左移
				right--;
			}
		}
		return false;
	}
}
