package tags.trie;

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
	// trie
	// 先用所有的word组建trie，在search每一个word，看能否由其他word组成（在trie里面找，找到一个单词，就回root recursive）
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		List<String> res = new ArrayList<String>();
		if (words == null || words.length == 0) {
			return res;
		}
		TrieNode root = new TrieNode();
		for (String word : words) { // construct Trie tree
			if (word.length() == 0) {
				continue;
			}
			addWord(word, root);
		}
		for (String word : words) { // test word is a concatenated word or not
			if (word.length() == 0) {
				continue;
			}
			if (testWord(word.toCharArray(), 0, root, 0)) {
				res.add(word);// 能被写入，才加到res里面
			}
		}
		return res;
	}

	public boolean testWord(char[] chars, int index, TrieNode root, int count) { // count means how many words during
																					// the search path
		TrieNode cur = root;
		int n = chars.length;
		for (int i = index; i < n; i++) {
			if (cur.sons[chars[i] - 'a'] == null) {// 到i找不到children了，结束
				return false;
			}
			if (cur.sons[chars[i] - 'a'].isEnd) {// 找到前缀单词
				if (i == n - 1) { // no next word, so test count to get result.
					return count >= 1;// 大于1，说明曾经成功过
				}
				if (testWord(chars, i + 1, root, count + 1)) {// 看后面能否有其他单词组成
					return true;
				}
			}
			cur = cur.sons[chars[i] - 'a'];// 去children层
		}
		return false;
	}

	public void addWord(String str, TrieNode root) {
		char[] chars = str.toCharArray();
		TrieNode cur = root;
		for (char c : chars) {
			if (cur.sons[c - 'a'] == null) {
				cur.sons[c - 'a'] = new TrieNode();
			}
			cur = cur.sons[c - 'a'];
		}
		cur.isEnd = true;
	}

	class TrieNode {
		TrieNode[] sons;
		boolean isEnd;

		public TrieNode() {
			sons = new TrieNode[26];
		}
	}

	// dp
	public List<String> findAllConcatenatedWordsInADict2(String[] words) {
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
