package ms;

public class LongestSubstringWithout3ContiguousLetter {

	/**
	 * Given a string s containing only a and b, find longest substring of s such
	 * that s does not contain more than two contiguous occurrences of a and b.
	 * 
	 * Example 1:
	 * 
	 * Input: "aabbaaaaabb" Output: "aabbaa" Example 2:
	 * 
	 * Input: "aabbaabbaabbaa" Output: "aabbaabbaabbaa"
	 * 
	 */

	public String validLongestSubstring(String s) {
		if (s == null || s.length() == 0)
			return null;
		if (s.length() < 3)
			return s;

		int cur = 0, end = 1;
		char c = s.charAt(0);
		// count = duplicate numbers, maxLen = max substring len, st, substring st index
		int count = 1;
		int maxLen = 1;
		int st = 0;
		while (end < s.length()) {
			if (s.charAt(end) == c) {
				count++;
				if (count == 2) {// valid, can update the maxLen
					if (end - cur + 1 > maxLen) {
						maxLen = end - cur + 1;
						st = cur;
					}
				} else {// >=3, can only start at end - 1
					cur = end - 1;
				}
			} else {
				c = s.charAt(end);
				count = 1;
				if (end - cur + 1 > maxLen) {
					maxLen = end - cur + 1;
					st = cur;
				}
			}
			end++;
		}
		return s.substring(st, st + maxLen);
	}
	
	public static void main(String[] args) {
		LongestSubstringWithout3ContiguousLetter m = new LongestSubstringWithout3ContiguousLetter();

		String s1 = "baaaaa";
		String s2 = "baaabbaabbba";
		String s3 = "baabab";
		String s4 = "abbbaaabbbaaabbaa";

		System.out.println(m.validLongestSubstring(s1));
		System.out.println(m.validLongestSubstring(s2));
		System.out.println(m.validLongestSubstring(s3));
		System.out.println(m.validLongestSubstring(s4));
	}
}
