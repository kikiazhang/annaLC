package tags.dynamicProgramming;

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
	// o(n^2) dp
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int n = s.length();
		String res = null;

		boolean[][] dp = new boolean[n][n];
		// 从后往前
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				// ij相同，就判断i+1 j-1 || 里面之后1或0个
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
				// 更新res
				if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
					res = s.substring(i, j + 1);
				}
			}
		}

		return res;
	}

	// o(n^2) 向外扩展
	public String longestPalindrome2(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int start = 0;
		int end = 0;
		// 从i开始，向外扩展
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);// bab
			int len2 = ((i < s.length() - 1) && (s.charAt(i) == s.charAt(i + 1))) ? expandAroundCenter(s, i, i + 1) : 0;
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
