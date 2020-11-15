package tags.binarySearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1
 * and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Example 1:
 * 
 * Input: [1,3,4,2,2] Output: 2 Example 2:
 * 
 * Input: [3,1,3,4,2] Output: 3 You must not modify the array (assume the array
 * is read only). You must use only constant, O(1) extra space. Your runtime
 * complexity should be less than O(n2). There is only one duplicate number in
 * the array, but it could be repeated more than once.
 */
public class FindDuplicateNumber287 {
	// Floyd's Tortoise and Hare (Cycle Detection) o(n)
	// 2(f + a) = f + a + b + a => f = b
	public int findDuplicate(int[] nums) {
		int slow = nums[0];
		int fast = nums[0];
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while (slow != fast);// find b

		slow = nums[0];// back to 0
		while (slow != fast) {// f steps is the cycle start point, which is the dul
			slow = nums[slow];
			fast = nums[fast];
		}

		return fast;
	}

	// o(nlogn) sort
	public int findDuplicate2(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				return nums[i];
			}
		}

		return -1;
	}

	// hashSet o(n)
	public int findDuplicate3(int[] nums) {
		Set<Integer> seen = new HashSet<Integer>();
		for (int num : nums) {
			if (seen.contains(num)) {
				return num;
			}
			seen.add(num);
		}

		return -1;
	}
}
