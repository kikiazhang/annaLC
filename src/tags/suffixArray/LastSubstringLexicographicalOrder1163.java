package tags.suffixArray;

/**
 * Given a string s, return the last substring of s in lexicographical order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "abab" Output: "bab" Explanation: The substrings are ["a", "ab",
 * "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is
 * "bab". Example 2:
 * 
 * Input: "leetcode" Output: "tcode"
 */
public class LastSubstringLexicographicalOrder1163 {
	// two pointer
	public String lastSubstring(String s) {
		// Time: O(n), space: O(1)
		int i = 0, j = 1, offset = 0, len = s.length();
		while (i + offset < len && j + offset < len) {
			char c = s.charAt(i + offset), d = s.charAt(j + offset);
			if (c == d) {
				++offset;
			} else {
				// fist mismatch occur, smaller one will skip i + offset(part i+offset == part
				// j+offset)
				if (c < d) {
					i += offset + 1;
				} // chars in i+offset is smaller, need to move next
				else {
					j += offset + 1;
				} // c > d, chars in [j, ..., j + offset] <= charAt(i) == charAt(j)
				if (i == j) {
					++i;
				} // avoid duplicate start indices.
				offset = 0; // reset offset to 0.
			}
		}
		return s.substring(Math.min(i, j));
	}
}
