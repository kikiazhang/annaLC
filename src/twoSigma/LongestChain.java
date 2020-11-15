package twoSigma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestChain {

	public int longestStrChain(String[] words) {
		Map<String, Integer> dp = new HashMap<>();
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		int res = 0;
		for (String word : words) {
			int best = 0;
			for (int i = 0; i < word.length(); ++i) {
				String prev = word.substring(0, i) + word.substring(i + 1);
				best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
			}
			dp.put(word, best);
			res = Math.max(res, best);
		}
		return res;
	}

	public int longestStrChain1(String[] words) {
		Map<Integer, List<Integer>> map = new HashMap<>();
//		Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
		for (int i = 0; i < words.length; i++) {
			List<Integer> list = map.getOrDefault(words[i].length(), new ArrayList<>());
			list.add(i);
			map.put(words[i].length(), list);
		}
		// 1 ->[a, b]
		// 2 ->[ab]
		// 3 ->[abc, abd]
		// 4 ->[abcd]
		int max = 1;
		int[] dp = new int[words.length];
		Arrays.fill(dp, 1);
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() == 1) {
				continue;
			}
			List<Integer> predecessorCandidates = map.get(words[i].length() - 1);
			if (predecessorCandidates == null) {
				continue;
			}
			for (int idx : predecessorCandidates) {
				if (isPredecessor(words[i], words[idx])) {
					dp[i] = Math.max(dp[i], dp[idx] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	private boolean isPredecessor(String prev, String s) {
		int i = 0, j = 0;
		while (i < prev.length() && j < s.length()) {
			if (prev.charAt(i) == s.charAt(j)) {
				i++;
				j++;
			} else {
				i++;
				if (i - j > 1)
					return false;
			}
		}
		return i - j <= 1;
	}
}
