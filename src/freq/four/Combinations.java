package freq.four;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

	/**
	 * Input: n = 4, k = 2 Output: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
	 */
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();

		if (k < 1 || n < 0)
			return res;
		helper(res, n, k, 1, new ArrayList<>());
		return res;
	}

	public void helper(List<List<Integer>> res, int n, int k, int st, List<Integer> layer) {
		if (layer.size() == k) {
			res.add(new ArrayList<>(layer));
		} else {
			for (int i = st; i <= n; i++) {
				layer.add(i);
				helper(res, n, k, i + 1, layer);
				layer.remove(layer.size() - 1);
			}
		}
	}

	// solution 2, better performance
	public List<List<Integer>> combine2(int n, int k) {
		List<List<Integer>> list = new ArrayList<>();
		// when k = 2, the first element can only come from 1 -> 3(n - k + 1)
		backtrack(list, new ArrayList<Integer>(), k, 1, n - k + 1);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int k, int start, int end) {
		if (tempList.size() == k)
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i <= end; i++) {
				tempList.add(i);
				backtrack(list, tempList, k, i + 1, end + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
}
