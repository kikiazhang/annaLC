package ms;

public class LexicographicallySmallestString {

	/**
	 * Lexicographically smallest string formed by removing at most one character.
	 * 
	 * Example 1:
	 * 
	 * Input: "abczd" Output: "abcd"
	 * 
	 */

	// https://leetcode.com/problems/remove-k-digits
	public String lexiSmallestString(String s) {
		if (s == null || s.length() == 0)
			return null;

		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (; i < s.length() - 1; i++) {
			if (s.charAt(i) > s.charAt(i + 1)) {
				break;
			}
		}

		return sb.deleteCharAt(i).toString();
	}
}
