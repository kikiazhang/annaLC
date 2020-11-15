package tags.strings;

public class LongestPalindromicSubstring5 {
	/**
	 * Given a string s, find the longest palindromic substring in s. You may assume
	 * that the maximum length of s is 1000.
	 * 
	 * Example 1:
	 * 
	 * Input: "babad" Output: "bab" Note: "aba" is also a valid answer. Example 2:
	 * 
	 * Input: "cbbd" Output: "bb"
	 */
	// o(n^2)
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int start = 0;
		int end = 0;
		// 从i开始，向外扩展
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);// bab
			int len2 = ((i < s.length() - 1) && (s.charAt(i) == s.charAt(i + 1))) ? expandAroundCenter(s, i, i + 1) : 0;// 注意需要i
																														// valid时才计算
			// baab
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;// （3-1）/2 = （4-1）/2 = 1 ：bab或者baab i为第一个a
				end = i + len / 2;// 一定是一半
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int start, int end) {
		int left = start;
		int right = end;
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}
}
