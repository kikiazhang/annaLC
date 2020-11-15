package tags.bit;

import java.util.List;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence
 * of arr which have unique characters.
 * 
 * Return the maximum possible length of s.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: arr = ["un","iq","ue"] Output: 4 Explanation: All possible
 * concatenations are "","un","iq","ue","uniq" and "ique". Maximum length is 4.
 * Example 2:
 * 
 * Input: arr = ["cha","r","act","ers"] Output: 6 Explanation: Possible
 * solutions are "chaers" and "acters". Example 3:
 * 
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"] Output: 26
 */
public class MaximumLengthofaConcatenatedStringwithUniqueCharacters1239 {
	private int max = 0;

	// dfs subset
	public int maxLength(List<String> arr) {
		if (arr == null) {
			return 0;
		}
		dfs(arr, 0, "");
		return max;
	}

	public void dfs(List<String> arr, int index, String concatenatStr) {
		if (isUnique(concatenatStr))
			max = Math.max(max, concatenatStr.length());
		if (index == arr.size() || !isUnique(concatenatStr))
			return;
		for (int i = index; i < arr.size(); i++) {
			if (isUnique(arr.get(i))) {
				dfs(arr, i + 1, concatenatStr + arr.get(i));
			}
		}
	}

	public boolean isUnique(String s) {
		int[] alpha = new int[26];
		for (int i = 0; i < s.length(); i++)
			alpha[s.charAt(i) - 'a']++;
		for (int i = 0; i < alpha.length; i++)
			if (alpha[i] > 1)
				return false;
		return true;
	}
}
