package tags.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * 
 * Example:
 * 
 * Input: [1,2,3] Output: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
 * ]
 */
public class Permutations46 {
	// o(n*n!)
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return res;

		helper(nums, res, new ArrayList<>());
		return res;
	}

	private void helper(int[] nums, List<List<Integer>> res, List<Integer> layer) {
		if (layer.size() == nums.length) {
			res.add(new ArrayList<>(layer));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (layer.contains(nums[i]))
				continue;
			layer.add(nums[i]);
			helper(nums, res, layer);
			layer.remove(layer.size() - 1);
		}
	}
}
