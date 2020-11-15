package tags.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a
 * target number (target), find all unique combinations in candidates where the
 * candidate numbers sums to target.
 * 
 * The same repeated number may be chosen from candidates unlimited number of
 * times.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers. The solution set
 * must not contain duplicate combinations. Example 1:
 * 
 * Input: candidates = [2,3,6,7], target = 7, A solution set is: [ [7], [2,2,3]
 * ] Example 2:
 * 
 * Input: candidates = [2,3,5], target = 8, A solution set is: [ [2,2,2,2],
 * [2,3,3], [3,5] ]
 */
public class CombinationSum39 {
	// Time complexity is O(N^target) where N is a length of candidates array.
	// 每个位置可以取集合中的任意数字, so n possibles，最多有target/min个数字。
	// Space complexity is O(target).
	// 2^n. The 2 is "take" or "not take".
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (candidates == null || candidates.length == 0)
			return res;

		Arrays.sort(candidates);
		helper(res, candidates, target, new ArrayList<>(), 0);
		return res;
	}

	private void helper(List<List<Integer>> res, int[] cand, int target, List<Integer> layer, int st) {
		if (target == 0) {
			res.add(new ArrayList<>(layer));
			return;
		}
		// start at st, avoid duplicated layer
		for (int i = st; i < cand.length; i++) {
			if (target < cand[i])
				break;
			layer.add(cand[i]);
			helper(res, cand, target - cand[i], layer, i);
			layer.remove(layer.size() - 1);
		}
	}
}
