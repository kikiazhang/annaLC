package pualtrics;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty array of integers, every element appears twice except for
 * one. Find that single one.
 * 
 * Note:
 * 
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * 
 * Example 1:
 * 
 * Input: [2,2,1] Output: 1 Example 2:
 * 
 * Input: [4,1,2,1,2] Output: 4
 */
public class SingleNumber136 {
	public int singleNumber(int[] nums) {
		int res = 0;
		for (int n : nums) {
			res ^= n;
		}
		return res;
	}

	public int singleNumber2(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int n : nums) {
			if (set.contains(n)) {
				set.remove(n);
			} else {
				set.add(n);
			}
		}
		for (int n : set) {
			return n;
		}
		return 0;
	}
}
