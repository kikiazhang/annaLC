package tags.twoPointers;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase English letters is given. We want to partition this
 * string into as many parts as possible so that each letter appears in at most
 * one part, and return a list of integers representing the size of these parts.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: S = "ababcbacadefegdehijhklij" Output: [9,7,8] Explanation: The
 * partition is "ababcbaca", "defegde", "hijhklij". This is a partition so that
 * each letter appears in at most one part. A partition like "ababcbacadefegde",
 * "hijhklij" is incorrect, because it splits S into less parts.
 */
public class PartitionLabels763 {
	public List<Integer> partitionLabels(String S) {
		// o(n) space:o(1)
		int[] lasts = new int[26];
		for (int i = 0; i < S.length(); i++) {
			lasts[S.charAt(i) - 'a'] = i;
		}
		int j = 0, anchor = 0;
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < S.length(); i++) {
			j = Math.max(j, lasts[S.charAt(i) - 'a']);// 一直记录的是到目前为止的last
			if (i == j) {// 说明这一段可以结束了
				res.add(i - anchor + 1);
				anchor = i + 1;
			}
		}
		return res;
	}
}
