package tags.bit;

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
}
