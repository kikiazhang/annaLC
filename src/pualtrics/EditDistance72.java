package pualtrics;

/**
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2.
 * 
 * You have the following three operations permitted on a word:
 * 
 * Insert a character Delete a character Replace a character
 * 
 * 
 * Example 1:
 * 
 * Input: word1 = "horse", word2 = "ros" Output: 3 Explanation: horse -> rorse
 * (replace 'h' with 'r') rorse -> rose (remove 'r') rose -> ros (remove 'e')
 * Example 2:
 * 
 * Input: word1 = "intention", word2 = "execution" Output: 5 Explanation:
 * intention -> inention (remove 't') inention -> enention (replace 'i' with
 * 'e') enention -> exention (replace 'n' with 'x') exention -> exection
 * (replace 'n' with 'c') exection -> execution (insert 'u')
 */
public class EditDistance72 {
	// dp
	public int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();

		int[][] dp = new int[len1 + 1][len2 + 1];
		// 初始化，都是delete才能match “”
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= len1; i++) {
			char a = word1.charAt(i - 1);
			for (int j = 1; j <= len2; j++) {
				char b = word2.charAt(j - 1);
				if (a == b) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					// ab都不要
					int replace = dp[i - 1][j - 1] + 1;
					// b不要
					int delete = dp[i][j - 1] + 1;
					// a不要
					int insert = dp[i - 1][j] + 1;
					dp[i][j] = Math.min(replace, Math.min(insert, delete));
				}
			}
		}
		return dp[len1][len2];
	}
}
