package tags.divideConquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * Example 1:
 * 
 * Input: [3,2,3] Output: 3 Example 2:
 * 
 * Input: [2,2,1,1,1,2,2] Output: 2
 */
public class MajorityElement169 {
	// o(nlogn)
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}

	// 名人问题 o（n）
	public int majorityElement2(int[] nums) {
		int count = 0;
		Integer candidate = null;

		for (int num : nums) {
			if (count == 0) {
				candidate = num;
			}
			count += (num == candidate) ? 1 : -1;
		}
		return candidate;
	}

	// divide and conquer o(nlogn)
	private int countInRange(int[] nums, int num, int lo, int hi) {
		int count = 0;
		for (int i = lo; i <= hi; i++) {
			if (nums[i] == num) {
				count++;
			}
		}
		return count;
	}

	private int majorityElementRec(int[] nums, int lo, int hi) {
		// base case; the only element in an array of size 1 is the majority
		// element.
		if (lo == hi) {
			return nums[lo];
		}

		// recurse on left and right halves of this slice to find the major num
		int mid = (hi - lo) / 2 + lo;
		int left = majorityElementRec(nums, lo, mid);
		int right = majorityElementRec(nums, mid + 1, hi);

		// if the two halves agree on the majority element, return it.
		if (left == right) {
			return left;
		}

		// otherwise, count each element and return the "winner".
		int leftCount = countInRange(nums, left, lo, hi);
		int rightCount = countInRange(nums, right, lo, hi);

		return leftCount > rightCount ? left : right;
	}

	public int majorityElement3(int[] nums) {
		return majorityElementRec(nums, 0, nums.length - 1);
	}

	// hashMap o(n)
	private Map<Integer, Integer> countNums4(int[] nums) {
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for (int num : nums) {
			if (!counts.containsKey(num)) {
				counts.put(num, 1);
			} else {
				counts.put(num, counts.get(num) + 1);
			}
		}
		return counts;
	}

	public int majorityElemen4t(int[] nums) {
		Map<Integer, Integer> counts = countNums4(nums);

		Map.Entry<Integer, Integer> majorityEntry = null;
		for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
			if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
				majorityEntry = entry;
			}
		}

		return majorityEntry.getKey();
	}
}
