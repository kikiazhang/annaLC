package tags.dfs.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of words (without duplicates), please write a program that
 * returns all concatenated words in the given list of words. A concatenated
 * word is defined as a string that is comprised entirely of at least two
 * shorter words in the given array.
 * 
 * Example: Input:
 * ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; "ratcatdogcat"
 * can be concatenated by "rat", "cat", "dog" and "cat".
 */
public class ConcatenatedWords472 {
	// dp
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		List<String> res = new ArrayList<>();
		if (words == null || words.length == 0)
			return res;

		Set<String> dict = new HashSet<>();
		// 从长度排序
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String w1, String w2) {
				return w1.length() - w2.length();
			}
		});

		for (int i = 0; i < words.length; i++) {
			if (helper(words[i], dict)) {// 能否由dict里面的多个组成word
				res.add(words[i]);
			}
			dict.add(words[i]);// 写入cur word，下面作为dict
		}
		return res;
	}

	private boolean helper(String word, Set<String> dict) {
		if (dict.isEmpty())// 还没有呢
			return false;
		boolean[] dp = new boolean[word.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= word.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (!dp[j])// 不能组成j
					continue;
				if (dict.contains(word.substring(j, i))) {// 成组成j，判断substring j-i，有就成功
					dp[i] = true;
					break;
				}
			}
		}
		return dp[word.length()];
	}
}
