package google;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into sets of k consecutive numbers Return True
 * if its possible otherwise return False.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4 Output: true Explanation: Array can be
 * divided into [1,2,3,4] and [3,4,5,6].
 * 
 * Input: nums = [1,2,3,4], k = 3 Output: false Explanation: Each array should
 * be divided in subarrays of size 3.
 */
public class DivideArrayinSetsKConsecutiveNumbers1296 {
	// Time O(MlogM + MK), where M is the number of different cards.
	public boolean isPossibleDivide(int[] nums, int k) {
		if (nums == null || nums.length == 0 || nums.length < k || nums.length % k != 0)
			return false;

		Map<Integer, Integer> map = new TreeMap<>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		for (int key : map.keySet()) {
			if (map.get(key) > 0) {
				for (int i = k - 1; i >= 0; i--) {
					if (map.getOrDefault(key + i, 0) < map.get(key))
						return false;
					map.put(key + i, map.get(key + i) - map.get(key));
				}
			}
		}

		return true;
	}

	// O(NlogM),where M is the number of different cards.
	public boolean isPossibleDivide2(int[] A, int k) {
		Map<Integer, Integer> c = new TreeMap<>();
		for (int i : A)
			c.put(i, c.getOrDefault(i, 0) + 1);
		Queue<Integer> start = new LinkedList<>();// opened straight groups
		int last_checked = -1, opened = 0;// last_checked : lastNum
		// opened: freq count that is ready to take in for the next consecutive number
		for (int i : c.keySet()) {
			if (opened > 0 && i > last_checked + 1 || opened > c.get(i))// 有opened&i不连续 || opened多余i的freq
				return false;
			start.add(c.get(i) - opened);// 还有几个需要连接后面的数
			last_checked = i;
			opened = c.get(i);
			if (start.size() == k)// 说明有k个数在queue里了，最前面的可以关闭
				opened -= start.remove();// current freq - start第一个 freq
		}
		return opened == 0;
	}
}
