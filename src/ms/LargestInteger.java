package ms;

import java.util.HashMap;
import java.util.Map;

public class LargestInteger {

	/**
	 * Write a function that, given an array A of N integers, returns the lagest
	 * integer K > 0 such that both values K and -K exisit in array A. If there is
	 * no such integer, the function should return 0.
	 * 
	 * Example 1:
	 * 
	 * Input: [3, 2, -2, 5, -3] Output: 3 Example 2:
	 * 
	 * Input: [1, 2, 3, -4] Output: 0
	 * 
	 */
	public int solution(int[] nums) {
		if (nums == null || nums.length < 2)
			return 0;

		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(Math.abs(nums[i]))) {
				if (map.get(Math.abs(nums[i])) == (-1) * nums[i]) {
					max = Math.max(max, Math.abs(nums[i]));
				}
			} else {
				map.put(Math.abs(nums[i]), nums[i]);
			}
		}

		/**
		 * Set<Integer> set = new HashSet<>(); 
		 * for(int i=0;i<nums.length;i++) {
		 * 	set.add(-nums[i]); 
		 * 	if(set.contains(nums[i])) { 
		 * 		res = Math.max(res, Math.abs(nums[i])); } }
		 * 
		 */
		return max;
	}

	public static void main(String[] args) {
		LargestInteger m = new LargestInteger();

		int[] nums1 = { 3, 2, -2, 5, -3 };
		int[] nums2 = { 1, 2, 3, -4 };

		System.out.println(m.solution(nums1));
		System.out.println(m.solution(nums2));
	}
}
