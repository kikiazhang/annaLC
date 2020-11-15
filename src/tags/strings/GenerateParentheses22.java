package tags.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 */
public class GenerateParentheses22 {
	// dfs o(2^2n)
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		if (n <= 0)
			return res;

		helper(res, n, 0, 0, "");
		return res;
	}

	public void helper(List<String> res, int n, int open, int close, String sb) {
		if (open == n && close == n) {
			res.add(sb.toString());// new String(tmp)
			return;
		}

		if (open < n)
			helper(res, n, open + 1, close, sb + "(");
		if (close < open)// just need to < open
			helper(res, n, open, close + 1, sb + ")");

	}
}
