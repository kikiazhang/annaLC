package tags.hashTable;

/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * Example:
 * 
 * Input: S = "ADOBECODEBANC", T = "ABC" Output: "BANC"
 */
public class MinimumWindowSubstring76 {
	public String minWindow(String s, String t) {
		if (s == null || s.length() == 0)
			return "";

		int[] chars = new int[256];
		for (char c : t.toCharArray()) {
			chars[c]++;
		}

		int count = t.length();
		int st = 0, end = 0;
		int min = Integer.MAX_VALUE;
		String res = "";
		while (end < s.length()) {
			if (chars[s.charAt(end)] > 0) {
				count--;
			}
			chars[s.charAt(end++)]--;// 每一位都减，所以在t里面的最终==0，不在t的为负数

			while (count == 0) {// 一直为0，就一直看min
				if (min > end - st) {// 更新res
					min = Math.min(min, end - st);
					res = s.substring(st, end);
				}

				if (chars[s.charAt(st)] == 0) {// 出现t里面的
					count++;
				}
				chars[s.charAt(st++)]++;// 每一位都加回来
			}
		}
		return res;
	}
}
