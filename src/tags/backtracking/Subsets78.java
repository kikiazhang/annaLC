package tags.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * Input: nums = [1,2,3] Output: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2],
 * [] ]
 */
public class Subsets78 {
	// o(n*2^n)
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return res;

		helper(res, 0, new ArrayList<>(), nums);
		return res;
	}

	private void helper(List<List<Integer>> res, int st, List<Integer> layer, int[] nums) {
		if (!res.contains(layer))
			res.add(new ArrayList<>(layer));

		for (int i = st; i < nums.length; i++) {
			layer.add(nums[i]);
			helper(res, i + 1, layer, nums);
			layer.remove(layer.size() - 1);
		}
	}

	// bit backtrack o(n*2^n)
	public List<List<Integer>> subsets2(int[] nums) {
		List<List<Integer>> output = new ArrayList<>();
		int n = nums.length;

		for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); ++i) {
			// generate bitmask, from 0..00 to 1..11
			String bitmask = Integer.toBinaryString(i).substring(1);

			// append subset corresponding to that bitmask
			List<Integer> curr = new ArrayList<>();
			for (int j = 0; j < n; ++j) {
				if (bitmask.charAt(j) == '1')
					curr.add(nums[j]);
			}
			output.add(curr);
		}
		return output;
	}
}
