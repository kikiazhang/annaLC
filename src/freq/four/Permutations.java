package freq.four;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	/**
	 * Input: [1,2,3] Output: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
	 * ]
	 */
	//time: O(n!) * O(n) -> list.contains
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();

		if (nums == null || nums.length == 0)
			return res;

		helper(nums, res, new ArrayList<>());
		return res;
	}

	public void helper(int[] nums, List<List<Integer>> res, List<Integer> layer) {
		if (layer.size() == nums.length) {
			res.add(new ArrayList<>(layer));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (layer.contains(nums[i]))
					continue;
				layer.add(nums[i]);
				helper(nums, res, layer);
				layer.remove(layer.size() - 1);
			}
		}
	}
}
