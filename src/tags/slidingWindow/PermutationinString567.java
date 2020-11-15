package tags.slidingWindow;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains
 * the permutation of s1. In other words, one of the first string's permutations
 * is the substring of the second string.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s1 = "ab" s2 = "eidbaooo" Output: True Explanation: s2 contains one
 * permutation of s1 ("ba"). Example 2:
 * 
 * Input:s1= "ab" s2 = "eidboaoo" Output: False
 */
public class PermutationinString567 {
	// sliding window o(n + 26*(m-n)) m&n are len
	public boolean checkInclusion(String s1, String s2) {
		if (s1.length() > s2.length())
			return false;

		int[] ch1 = new int[26];
		int[] ch2 = new int[26];
		// init, save s1
		for (int i = 0; i < s1.length(); i++) {
			ch1[s1.charAt(i) - 'a']++;
			ch2[s2.charAt(i) - 'a']++;
		}
		for (int i = 0; i < s2.length() - s1.length(); i++) {
			// check current s1 & sub s2 match
			if (match(ch1, ch2)) {
				return true;
			}
			// save current s2 char & remove most left s2 char
			ch2[s2.charAt(i + s1.length()) - 'a']++;
			ch2[s2.charAt(i) - 'a']--;
		}
		return match(ch1, ch2);
	}

	private boolean match(int[] ch1, int[] ch2) {
		for (int i = 0; i < 26; i++) {
			if (ch1[i] != ch2[i]) {
				return false;
			}
		}
		return true;
	}
}
