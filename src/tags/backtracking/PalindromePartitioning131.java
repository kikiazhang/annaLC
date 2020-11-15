package tags.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * Example:
 * 
 * Input: "aab" Output: [ ["aa","b"], ["a","a","b"] ]
 */
public class PalindromePartitioning131 {
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		if (s == null || s.length() == 0)
			return res;

		helper(s, 0, res, new ArrayList<>());
		return res;
	}

	private void helper(String s, int st, List<List<String>> res, List<String> cur) {
		if (st == s.length()) {
			res.add(new ArrayList<>(cur));
			return;
		}
		for (int j = st; j < s.length(); j++) {
			if (isPalind(s, st, j)) {
				cur.add(s.substring(st, j + 1));
				helper(s, j + 1, res, cur);
				cur.remove(cur.size() - 1);
			} else {

			}
		}

	}

	private boolean isPalind(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}
