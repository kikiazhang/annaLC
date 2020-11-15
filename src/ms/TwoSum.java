package ms;

import java.util.HashMap;

public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int minus = target - nums[i];
			if (map.containsKey(nums[i])) {
				result[1] = i;
				result[0] = map.get(nums[i]);
				break;
			} else {
				map.put(minus, i);
			}
		}
		return result;
	}

	// sorted
	public int[] twoSum2(int[] num, int target) {
		int[] result = new int[2];
		int left = 0;
		int right = num.length - 1;
		while (left < right) {
			int sum = num[left] + num[right];
			if (sum == target) {
				result[0] = left + 1;
				result[1] = right + 1;
				break;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return result;
	}
}
