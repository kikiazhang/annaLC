package tags.bit;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums, in which exactly two elements appear only once
 * and all the other elements appear exactly twice. Find the two elements that
 * appear only once. You can return the answer in any order.
 * 
 * Follow up: Your algorithm should run in linear runtime complexity. Could you
 * implement it using only constant space complexity?
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,1,3,2,5] Output: [3,5] Explanation: [5, 3] is also a valid
 * answer. Example 2:
 * 
 * Input: nums = [-1,0] Output: [-1,0] Example 3:
 * 
 * Input: nums = [0,1] Output: [1,0]
 */
public class SingleNumberIII260 {
	public int[] singleNumber(int[] nums) {
		Set<Integer> set = new HashSet<>();
		Set<Integer> remove = new HashSet<>();
		for (int n : nums) {
			if (set.contains(n)) {
				remove.add(n);
			} else {
				set.add(n);
			}
		}
		set.removeAll(remove);
		int[] res = new int[set.size()];
		int i = 0;
		for (int n : set) {
			res[i++] = n;
		}
		return res;
	}

	public int[] singleNumber2(int[] nums) {
		// difference between two numbers (x and y) which were seen only once
		int bitmask = 0;
		for (int num : nums)
			bitmask ^= num;

		// rightmost 1-bit diff between x and y
		int diff = bitmask & (-bitmask);

		int x = 0;// 先找到&diff的那个x
		// bitmask which will contain only x
		for (int num : nums)
			if ((num & diff) != 0)
				x ^= num;
		// bitmask ^ x 为&diff==0的那个
		return new int[] { x, bitmask ^ x };
	}
}
