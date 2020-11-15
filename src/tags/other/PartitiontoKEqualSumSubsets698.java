package tags.other;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into k non-empty subsets whose sums are all
 * equal.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4 Output: True Explanation: It's
 * possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal
 * sums.
 */
public class PartitiontoKEqualSumSubsets698 {
	public boolean search(int[] groups, int row, int[] nums, int target) {
		if (row < 0)
			return true;
		// 目前最大的value，并把row缩进
		int v = nums[row--];
		// 到放k组
		for (int i = 0; i < groups.length; i++) {
			// dfs
			if (groups[i] + v <= target) {
				groups[i] += v;
				if (search(groups, row, nums, target))
					return true;
				groups[i] -= v;
			}
			// all the 0 values of each group occur at the end of the array
			// groups，说明这一组放完了，但是还没找到dfs可以true的
			if (groups[i] == 0)
				break;
		}
		return false;
	}

	public boolean canPartitionKSubsets(int[] nums, int k) {
		// 不能除尽，就false
		int sum = Arrays.stream(nums).sum();
		if (sum % k > 0)
			return false;
		int target = sum / k;
		// 排序
		Arrays.sort(nums);
		int row = nums.length - 1;
		// 最大值大于target， false
		if (nums[row] > target)
			return false;
		// 把最大值==target排除掉，能分的组--
		while (row >= 0 && nums[row] == target) {
			row--;
			k--;
		}
		return search(new int[k], row, nums, target);
	}
}
