package tags.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of unique words, return all the pairs of the distinct indices
 * (i, j) in the given list, so that the concatenation of the two words words[i]
 * + words[j] is a palindrome.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: words = ["abcd","dcba","lls","s","sssll"] Output:
 * [[0,1],[1,0],[3,2],[2,4]] Explanation: The palindromes are
 * ["dcbaabcd","abcddcba","slls","llssssll"] Example 2:
 * 
 * Input: words = ["bat","tab","cat"] Output: [[0,1],[1,0]] Explanation: The
 * palindromes are ["battab","tabbat"] Example 3:
 * 
 * Input: words = ["a",""] Output: [[0,1],[1,0]]
 */
public class PalindromePairs336 {
	// O(n * k^2)
	class TrieNode {
		TrieNode[] next;
		int index;// input index
		List<Integer> list;// 所有从root开始到这个trie是prlindrome的word index

		TrieNode() {
			next = new TrieNode[26];
			index = -1;
			list = new ArrayList<>();
		}
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<>();

		TrieNode root = new TrieNode();

		for (int i = 0; i < words.length; i++) {
			addWord(root, words[i], i);
		}

		for (int i = 0; i < words.length; i++) {
			search(words, i, root, res);
		}

		return res;
	}

	// reverse order to save in trie
	private void addWord(TrieNode root, String word, int index) {
		for (int i = word.length() - 1; i >= 0; i--) {
			int j = word.charAt(i) - 'a';

			if (root.next[j] == null) {
				root.next[j] = new TrieNode();
			}

			if (isPalindrome(word, 0, i)) {// 这个word有prefix的palindrome，在这一层trie的list中记录
				root.list.add(index);
			}

			root = root.next[j];// 去children层
		}
		// word结束时，记录list和index
		root.list.add(index);
		root.index = index;
	}

	// 因为之前add是倒序放的，现在正序检查，能往下走，就说明到此为止有palindrome
	private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
		for (int j = 0; j < words[i].length(); j++) {
			// 是一个palindrome && 不是cur word && cur word后半段是palindrome
			if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
				res.add(Arrays.asList(i, root.index));
			}

			root = root.next[words[i].charAt(j) - 'a'];
			if (root == null)
				return;
		}
		// 走完了cur word，还要检查此时trie有没有palindrome的前缀（lls）
		for (int j : root.list) {
			if (i == j)
				continue;
			res.add(Arrays.asList(i, j));
		}
	}

	private boolean isPalindrome(String word, int i, int j) {
		while (i < j) {
			if (word.charAt(i++) != word.charAt(j--))
				return false;
		}

		return true;
	}
}
