package tags.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 * 
 * Note:
 * 
 * The solution set must not contain duplicate triplets.
 * 
 * Example:
 * 
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */
public class ThreeSum15 {
	// 注意重复
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length <= 2)
			return res;
		Arrays.sort(nums);
		for (int index = 0; index < nums.length - 2; index++) {
			// 跳过重复
			if (index != 0 && nums[index] == nums[index - 1]) {
				continue;
			}
			// 第一位超过0就不可能了
			if (nums[index] > 0) {
				break;
			}
			int i = index + 1;
			int j = nums.length - 1;
			// two sum：two pointers
			while (i < j) {
				int sum = nums[index] + nums[i] + nums[j];
				if (sum == 0) {
					List<Integer> temp = new ArrayList<>();
					temp.add(nums[index]);
					temp.add(nums[i]);
					temp.add(nums[j]);
					res.add(temp);
					i++;
					j--;
					// 跳过重复
					while (i < j && nums[i] == nums[i - 1]) {
						i++;
					}
					while (i < j && nums[j] == nums[j + 1]) {
						j--;
					}
				} else if (sum > 0) {
					j--;
				} else {
					i++;
				}
			}
		}
		return res;
	}
}
