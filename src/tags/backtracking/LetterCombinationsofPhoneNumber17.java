package tags.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters. Input: "23" Output: ["ad",
 * "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsofPhoneNumber17 {
	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		if (digits == null || digits.length() == 0)
			return res;

		String[] poss = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		helper(digits, 0, res, "", poss);
		return res;
	}

	private void helper(String digits, int cur, List<String> res, String curStr, String[] poss) {
		if (cur == digits.length()) {
			res.add(curStr);
			return;
		}
		String tmp = poss[(int) (digits.charAt(cur) - '0') - 2];
		for (int i = 0; i < tmp.length(); i++) {
			helper(digits, cur + 1, res, curStr + tmp.charAt(i), poss);
		}
	}

	@SuppressWarnings("serial")
	// map o(3^N * 4^N)
	Map<String, String> phone = new HashMap<String, String>() {
		{
			put("2", "abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}
	};

	public List<String> letterCombinations2(String digits) {
		List<String> rst = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return rst;
		}
		helper(rst, digits, 0, "");
		return rst;
	}

	private void helper(List<String> rst, String digits, int start, String temp) {
		if (temp.length() == digits.length()) {
			rst.add(temp);
			return;
		}
		for (int i = start; i < digits.length(); i++) {
			String s = phone.get(String.valueOf(digits.charAt(i)));
			for (int j = 0; j < s.length(); j++) {
				helper(rst, digits, i + 1, temp + s.substring(j, j + 1));
			}
		}
	}
}
