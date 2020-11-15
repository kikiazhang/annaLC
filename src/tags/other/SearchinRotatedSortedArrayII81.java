package tags.other;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * 
 * You are given a target value to search. If found in the array return true,
 * otherwise return false.
 * 
 * Example 1:
 * 
 * Input: nums = [2,5,6,0,0,1,2], target = 0 Output: true Example 2:
 * 
 * Input: nums = [2,5,6,0,0,1,2], target = 3 Output: false
 */
public class SearchinRotatedSortedArrayII81 {
	public boolean search(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = (right + left) / 2;
			if (nums[mid] == target)
				return true;
			if (nums[mid] < nums[right]) {// 有序右边
				if (nums[mid] < target && target <= nums[right]) {// 在右边
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else if (nums[mid] > nums[right]) {// 有序左边
				if (nums[mid] > target && target >= nums[left]) {// 在左边
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
