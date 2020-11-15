package tags.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Example: Operation: AutocompleteSystem(["i love you", "island","ironman", "i
 * love leetcode"], [5,3,2,2]) The system have already tracked down the
 * following sentences and their corresponding times: "i love you" : 5 times
 * "island" : 3 times "ironman" : 2 times "i love leetcode" : 2 times Now, the
 * user begins another search:
 * 
 * Operation: input('i') Output: ["i love you", "island","i love leetcode"]
 * Explanation: There are four sentences that have prefix "i". Among them,
 * "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII
 * code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of
 * "ironman". Also we only need to output top 3 hot sentences, so "ironman" will
 * be ignored.
 * 
 * Operation: input(' ') Output: ["i love you","i love leetcode"] Explanation:
 * There are only two sentences that have prefix "i ".
 * 
 * Operation: input('a') Output: [] Explanation: There are no sentences that
 * have prefix "i a".
 * 
 * Operation: input('#') Output: [] Explanation: The user finished the input,
 * the sentence "i a" should be saved as a historical sentence in system. And
 * the following input will be counted as a new search.
 */
public class DesignSearchAutocompleteSystem642 {
	// trie 结构， node记录可能的string+times
	Trie root;
	String cur = "";// 目前输入的string

	public DesignSearchAutocompleteSystem642(String[] sentences, int[] times) {
		root = new Trie();
		for (int i = 0; i < sentences.length; i++) {
			insert(root, sentences[i], times[i]);
		}
	}

	private void insert(Trie node, String s, int times) {
		for (int i = 0; i < s.length(); i++) {
			if (node.branches[toInt(s.charAt(i))] == null) {
				node.branches[toInt(s.charAt(i))] = new Trie();
			}
			node = node.branches[toInt(s.charAt(i))];
		}
		// 最后加入times，说明完整string有times
		node.times += times;
	}

	// a-z和‘ ’
	private int toInt(char c) {
		return c == ' ' ? 26 : c - 'a';
	}

	public List<String> input(char c) {
		List<String> res = new ArrayList<>();
		// #说明输入结束，需要把cur加入trie
		if (c == '#') {
			insert(root, cur, 1);
			cur = "";
		} else {
			cur += c;
			// 找到cur之后所有可能string
			List<Node> list = lookup(root, cur);
			// 按照times倒叙，不然ASCII正序（a compareTo b）
			Collections.sort(list, (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
			// 只拿三个放到res
			for (int i = 0; i < Math.min(3, list.size()); i++) {
				res.add(list.get(i).sentence);
			}
		}
		return res;
	}

	private List<Node> lookup(Trie node, String s) {
		List<Node> res = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			// 无法继续了
			if (node.branches[toInt(s.charAt(i))] == null) {
				return new ArrayList<>();
			}
			node = node.branches[toInt(s.charAt(i))];
		}
		// 遍历接下来所有可能
		traverse(s, node, res);
		return res;
	}

	private void traverse(String s, Trie node, List<Node> list) {
		// 说明是一个string，可以加入list
		if (node.times > 0)
			list.add(new Node(s, node.times));
		// 遍历a-z
		for (char i = 'a'; i <= 'z'; i++) {
			if (node.branches[i - 'a'] != null) {
				// dfs下一层
				traverse(s + i, node.branches[i - 'a'], list);
			}
		}
		// 遍历‘ ’
		if (node.branches[26] != null) {
			traverse(s + ' ', node.branches[26], list);
		}
	}

	class Node {
		String sentence;
		int times;

		Node(String st, int t) {
			sentence = st;
			times = t;
		}
	}

	class Trie {
		int times;
		Trie[] branches = new Trie[27];
	}
}
