package tags.dynamicProgramming;

/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given a non-empty string containing only
 * digits, determine the total number of ways to decode it.
 * 
 * Example 1:
 * 
 * Input: "12" Output: 2 Explanation: It could be decoded as "AB" (1 2) or "L"
 * (12). Example 2:
 * 
 * Input: "226" Output: 3 Explanation: It could be decoded as "BZ" (2 26), "VF"
 * (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays91 {
	// dp o(n)
	public int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int[] dp = new int[s.length() + 1];
		dp[0] = 1;// 1种可能
		dp[1] = s.charAt(0) == '0' ? 0 : 1;// 如果开始时0，没有可能
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != '0')
				dp[i + 1] = dp[i];// 先排出0，否则都可以i为一种可能

			int num = Integer.parseInt(s.substring(i - 1, i + 1));
			if (num >= 10 && num <= 26) {// 当可能10-26，再加入i-1时个数
				dp[i + 1] += dp[i - 1];
			}
		}
		return dp[s.length()];
	}
}
