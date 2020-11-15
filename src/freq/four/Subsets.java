package freq.four;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

	/**
	 * Input: nums = [1,2,3] Output: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2],
	 * [] ]
	 */
	// O(n x 2^n)
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();

		if (nums == null || nums.length == 0)
			return res;
		helper(nums, 0, res, new ArrayList<>());
		return res;
	}

	public void helper(int[] nums, int st, List<List<Integer>> res, List<Integer> layer) {
		res.add(new ArrayList<>(layer));
		for (int i = st; i < nums.length; i++) {
			layer.add(nums[i]);
			helper(nums, i + 1, res, layer);
			layer.remove(layer.size() - 1);
		}
	}
}
