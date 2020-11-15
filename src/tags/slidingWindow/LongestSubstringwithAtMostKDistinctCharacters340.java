package tags.slidingWindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at
 * most k distinct characters.
 * 
 * Example 1:
 * 
 * Input: s = "eceba", k = 2 Output: 3 Explanation: T is "ece" which its length
 * is 3. Example 2:
 * 
 * Input: s = "aa", k = 1 Output: 2 Explanation: T is "aa" which its length is
 * 2.
 */
public class LongestSubstringwithAtMostKDistinctCharacters340 {
	// o（n）
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.length() == 0 || k <= 0)
			return 0;

		int st = 0, ed = 0;
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		while (ed < s.length()) {
			map.put(s.charAt(ed), ed++);

			if (map.size() == k + 1) {
				// 确保remove st开始最先去掉的char，然后st成为delete+1
				int delete = Collections.min(map.values());
				map.remove(s.charAt(delete));
				st = delete + 1;
			}

			max = Math.max(max, ed - st);// 一直更新max
		}
		return max;
	}
}
