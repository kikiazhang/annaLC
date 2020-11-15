package tags.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Example 1:
 * 
 * Input: "abcabcbb" Output: 3 Explanation: The answer is "abc", with the length
 * of 3. Example 2:
 * 
 * Input: "bbbbb" Output: 1 Explanation: The answer is "b", with the length of
 * 1. Example 3:
 * 
 * Input: "pwwkew" Output: 3 Explanation: The answer is "wke", with the length
 * of 3. Note that the answer must be a substring, "pwke" is a subsequence and
 * not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters3 {
	// o(n) sliding window
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int[] chars = new int[256];
		int st = 0, end = 0;
		int max = 0;
		while (st < s.length() && end < s.length()) {
			if (chars[s.charAt(end)] == 0) {
				chars[s.charAt(end++)]++;// 只有在没出现过时才chars++，end++，不然就去掉一个st
				max = Math.max(max, end - st);
			} else {
				chars[s.charAt(st++)]--; // end不动，直到end char == 0，才继续
			}
		}
		return max;
	}

	// hashtable做法
	public int lengthOfLongestSubstring2(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int max = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0, j = 0; j < s.length(); j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(i, map.get(s.charAt(j)));// 上次出现的index+1
			}
			max = Math.max(max, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return max;
	}
}
