package tags.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's
 * anagrams in s.
 * 
 * Strings consists of lowercase English letters only and the length of both
 * strings s and p will not be larger than 20,100.
 * 
 * The order of output does not matter.
 * 
 * Example 1:
 * 
 * Input: s: "cbaebabacd" p: "abc"
 * 
 * Output: [0, 6]
 * 
 * Explanation: The substring with start index = 0 is "cba", which is an anagram
 * of "abc". The substring with start index = 6 is "bac", which is an anagram of
 * "abc".
 */
public class FindAllAnagramsinaString438 {
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || p == null || p.length() == 0)
			return res;
		int[] chars = new int[26];
		for (char c : p.toCharArray()) {
			chars[c - 'a']++;
		}
		int st = 0, ed = 0;
		int count = 0;
		while (ed < s.length()) {
			if (chars[s.charAt(ed++) - 'a']-- > 0) {
				count++;
			}
			if (count == p.length()) {
				res.add(st);
			}
			if (ed - st >= p.length()) {
				if (chars[s.charAt(st++) - 'a']++ >= 0) {
					count--;
				}
			}
		}
		return res;
	}
}
