package tags.slidingWindow;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so
 * that T is a subsequence of W.
 * 
 * If there is no such window in S that covers all characters in T, return the
 * empty string "". If there are multiple such minimum-length windows, return
 * the one with the left-most starting index.
 * 
 * Example 1:
 * 
 * Input: S = "abcdebdde", T = "bde" Output: "bcde" Explanation: "bcde" is the
 * answer because it occurs before "bdde" which has the same length. "deb" is
 * not a smaller window because the elements of T in the window must occur in
 * order.
 */
public class MinimumWindowSubsequence727 {

	// dp
	public String minWindow(String S, String T) {
		int m = T.length(), n = S.length();
		int[][] dp = new int[m + 1][n + 1];// the starting index of the substring where T has length i and S has length
											// j
		for (int j = 0; j <= n; j++) {// T是null时
			dp[0][j] = j + 1;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (T.charAt(i - 1) == S.charAt(j - 1)) {// match一个char
					dp[i][j] = dp[i - 1][j - 1];
				} else {// 不能match T中i，看s是j-1时能不能有valid substring
					dp[i][j] = dp[i][j - 1];
				}
			}
		}

		int start = 0, len = n + 1;// len不可能到n+1
		for (int j = 1; j <= n; j++) {// 遍历s
			if (dp[m][j] != 0) {// 能组成T的s substring的st都在哪，比较len
				if (j - dp[m][j] + 1 < len) {
					start = dp[m][j] - 1;
					len = j - dp[m][j] + 1;
				}
			}
		}
		return len == n + 1 ? "" : S.substring(start, start + len);
	}
}
