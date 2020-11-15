package google;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums sorted in ascending order, return true if and only if you
 * can split it into 1 or more subsequences such that each subsequence consists
 * of consecutive integers and has length at least 3.
 * 
 * Input: [1,2,3,3,4,4,5,5] Output: True Explanation: You can split them into
 * two consecutive subsequences : 1, 2, 3, 4, 5 3, 4, 5
 * 
 * 
 * Input: [1,2,3,4,4,5] Output: False
 */
public class SplitArrayIntoConsecutiveSubsequences659 {
	public boolean isPossible(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
		// freq 剩下的个数， appendfreq以他结束的个数（不包括这位）
		for (int n : nums)
			freq.put(n, freq.getOrDefault(n, 0) + 1);
		for (int n : nums) {
			if (freq.get(n) == 0)// 都用完了，跳过
				continue;
			else if (appendfreq.getOrDefault(n, 0) > 0) {// 有以他结束的
				appendfreq.put(n, appendfreq.get(n) - 1);// 以他结束的-1
				appendfreq.put(n + 1, appendfreq.getOrDefault(n + 1, 0) + 1);// 以他下一位结束的+1
			} else if (freq.getOrDefault(n + 1, 0) > 0 && freq.getOrDefault(n + 2, 0) > 0) {// 可以以他开始三个
				freq.put(n + 1, freq.get(n + 1) - 1);
				freq.put(n + 2, freq.get(n + 2) - 1);
				appendfreq.put(n + 3, appendfreq.getOrDefault(n + 3, 0) + 1);// 第四位设为结束
			} else {
				return false;
			}
			freq.put(n, freq.get(n) - 1);// 去掉一个freq
		}
		return true;
	}
}
