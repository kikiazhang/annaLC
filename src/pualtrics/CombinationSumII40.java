package pualtrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number
 * (target), find all unique combinations in candidates where the candidate
 * numbers sum to target.
 * 
 * Each number in candidates may only be used once in the combination.
 * 
 * Note: The solution set must not contain duplicate combinations.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: candidates = [10,1,2,7,6,1,5], target = 8 Output: [ [1,1,6], [1,2,5],
 * [1,7], [2,6] ]
 */
public class CombinationSumII40 {
	public List<List<Integer>> combinationSum2(int[] cand, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (cand == null || cand.length == 0)
			return res;
		Arrays.sort(cand);
		boolean[] visited = new boolean[cand.length];
		helper(res, cand, 0, target, new ArrayList<>(), visited);
		return res;
	}

	private void helper(List<List<Integer>> res, int[] cand, int st, int target, List<Integer> cur, boolean[] visited) {
		if (target == 0) {
			res.add(new ArrayList<>(cur));
			return;
		}
		if (target < 0)
			return;
		for (int i = st; i < cand.length; i++) {
			if (i != 0 && cand[i] == cand[i - 1] && !visited[i - 1])
				continue;
			visited[i] = true;
			cur.add(cand[i]);
			helper(res, cand, i + 1, target - cand[i], cur, visited);
			cur.remove(cur.size() - 1);
			visited[i] = false;
		}
	}
}
