package ms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	/**
	 * For example, given array S = [-1, 0, 1, 2, -1, -4], A solution set is: [ [-1,
	 * 0, 1], [-1, -1, 2] ]
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int twoSum = 0 - nums[i];
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				if (nums[left] + nums[right] == twoSum) {
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					// skip the same ones
					while (left < right && nums[left] == nums[left + 1])
						left++;
					while (left < right && nums[right] == nums[right - 1])
						right--;
					left++;
					right--;
				} else if (nums[left] + nums[right] < twoSum) {
					left++;
				} else {
					right--;
				}
			}
		}
		return result;
	}
}
