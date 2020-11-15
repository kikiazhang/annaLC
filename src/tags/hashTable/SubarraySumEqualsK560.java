package tags.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total
 * number of continuous subarrays whose sum equals to k.
 * 
 * Example 1:
 * 
 * Input:nums = [1,1,1], k = 2 Output: 2
 */
public class SubarraySumEqualsK560 {
	// two sum similar
	public int subarraySum(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return 0;

		int ans = 0;
		int sum = 0;// 一直到index的sum
		Map<Integer, Integer> map = new HashMap<>();
		// sum different equals 0 for getting start
		map.put(0, 1);//  什么都没有也算1
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			// check if there is any point exist
			if (map.containsKey(sum - k)) {// sum index - sum index2 == k就是找到了
				ans += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);// 每一个sum初始值是1
		}
		return ans;
	}

	// without space
	public int subarraySum2(int[] nums, int k) {
		int count = 0;
		for (int start = 0; start < nums.length; start++) {
			int sum = 0;
			for (int end = start; end < nums.length; end++) {
				sum += nums[end];
				if (sum == k)
					count++;
			}
		}
		return count;
	}
}
