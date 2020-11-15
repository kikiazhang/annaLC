package freq.four;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

	/**
	 * For example, given n = 3, a solution set is:
	 * 
	 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
	 */
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();

		if (n <= 0)
			return res;

		helper(n, 0, 0, res, "");
		return res;
	}

	public void helper(int len, int left, int right, List<String> res, String s) {
		if (s.length() == len * 2) {
			res.add(s);
			return;
		}
		// first (s
		if (left < len) {
			helper(len, left + 1, right, res, s + "(");
		}
		// then valid )
		if (left > right) {
			helper(len, left, right + 1, res, s + ")");
		}
	}
}
