package ms;

public class LongestSemiAlternatingSubstring {

	/**
	 * S="baaabbabbb" return 7, "aabbabb" S="babba" return 5, "babba" S="abaaaa"
	 * return 4, "abaa"
	 * 
	 * find longest substring not contain "aaa" or "bbb"
	 */

	public int solution(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int st = 0, end = 1;
		int count = 1;
		char c = s.charAt(st);

		int maxLen = 1;
		while (end < s.length()) {
			if (c == s.charAt(end)) {
				count++;
				if (count >= 3) {
					st = end - 1;
				} else {
					if (end - st + 1 > maxLen) {
						maxLen = end - st + 1;
					}
				}
			} else {
				count = 1;
				c = s.charAt(end);
				if (end - st + 1 > maxLen) {
					maxLen = end - st + 1;
				}
			}
			end++;
		}
		return maxLen;
	}

	public static void main(String[] args) {
		LongestSemiAlternatingSubstring m = new LongestSemiAlternatingSubstring();

		String s1 = "baaaaa";
		String s2 = "baaabbaabbba";
		String s3 = "baabab";
		String s4 = "abbbaaabbbaaabbaa";

		System.out.println(m.solution(s1));
		System.out.println(m.solution(s2));
		System.out.println(m.solution(s3));
		System.out.println(m.solution(s4));
	}
}
