package freq.four;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

	/**
	 * Input: "aab" Output: [ ["aa","b"], ["a","a","b"] ]
	 */
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();

		if (s == null || s.length() == 0)
			return res;
		helper(res, s, 0, new ArrayList<>());
		return res;
	}

	public void helper(List<List<String>> res, String s, int st, List<String> layer) {
		if (st == s.length()) {
			res.add(new ArrayList<>(layer));
			return;
		}
		for (int i = st; i < s.length(); i++) {
			if (isValid(s.substring(st, i + 1))) {
				layer.add(s.substring(st, i + 1));
				helper(res, s, i + 1, layer);
				layer.remove(layer.size() - 1);
			}
		}
	}

	public boolean isValid(String s) {
		int i = 0, j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	//solution 2, index -> index+1 is valid, no need to check
	private void partition(String s, int index, List<String> result, List<List<String>> res) {
        if (index == s.length()) {
            res.add(new ArrayList(result));
            return;
        }
        String str = s.substring(index, index + 1);
        result.add(str);
        partition(s, index + 1, result, res);
        result.remove(result.size() - 1);
        for (int i = index + 1; i < s.length(); i++) {
            if (isValid(s.substring(index, i+1))) {
                str = s.substring(index, i + 1);
                result.add(str);
                partition(s, i + 1, result, res);
                result.remove(result.size() - 1);
            }
        }
    }
}
