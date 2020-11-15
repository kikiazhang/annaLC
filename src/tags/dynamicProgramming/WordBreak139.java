package tags.dynamicProgramming;

import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * 
 * Note:
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation. You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"] Output: true Explanation:
 * Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak139 {
	// dp
	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0)
			return false;

		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		// ij都是dp index
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				dp[i] = dp[j] && wordDict.contains(s.substring(j, i));
				if (dp[i])
					break;
			}
		}
		return dp[s.length()];
	}
}
