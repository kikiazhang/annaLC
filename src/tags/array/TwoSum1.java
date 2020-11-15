package tags.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 */
public class TwoSum1 {
	public int[] twoSum(int[] nums, int target) {
		int[] rst = new int[] { -1, -1 };
		if (nums == null || nums.length == 0) {
			return rst;
		}

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				// return new int[]{map.get(nums[i]), i};
				rst[0] = map.get(nums[i]);
				rst[1] = i;
				break;
			} else {
				map.put(target - nums[i], i);
			}
		}
		return rst;
	}
}
