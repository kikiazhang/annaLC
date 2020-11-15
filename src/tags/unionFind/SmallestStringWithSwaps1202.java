package tags.unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given a string s, and an array of pairs of indices in the string
 * pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * 
 * You can swap the characters at any pair of indices in the given pairs any
 * number of times.
 * 
 * Return the lexicographically smallest string that s can be changed to after
 * using the swaps.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "dcab", pairs = [[0,3],[1,2]] Output: "bacd" Explaination: Swap
 * s[0] and s[3], s = "bcad" Swap s[1] and s[2], s = "bacd" Example 2:
 * 
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]] Output: "abcd" Explaination:
 * Swap s[0] and s[3], s = "bcad" Swap s[0] and s[2], s = "acbd" Swap s[1] and
 * s[2], s = "abcd" Example 3:
 * 
 * Input: s = "cba", pairs = [[0,1],[1,2]] Output: "abc" Explaination: Swap s[0]
 * and s[1], s = "bca" Swap s[1] and s[2], s = "bac" Swap s[0] and s[1], s =
 * "abc"
 */
public class SmallestStringWithSwaps1202 {
	// union find & PriorityQueue o（Mlogn） m是pairs的个数
	// （0，1）（0，2）-- （0，1，2）
	private int[] parent;

	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		if (s == null || s.length() == 0) {
			return null;
		}
		// union parent
		parent = new int[s.length()];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		// union index path
		for (List<Integer> pair : pairs) {
			union(pair.get(0), pair.get(1));
		}

		Map<Integer, PriorityQueue<Character>> map = new HashMap<>();// index，poss char（最起码有原始index的char）
		char[] sChar = s.toCharArray();
		for (int i = 0; i < sChar.length; i++) {
			int root = find(i);// 这个index能交换到的最小index
			map.putIfAbsent(root, new PriorityQueue<>());
			map.get(root).offer(sChar[i]);// 最小index能有cur char
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sChar.length; i++) {
			sb.append(map.get(find(i)).poll());// 每一位index，拿priorityqueue里面的最小char
		}
		return sb.toString();
	}

	private int find(int index) {
		while (parent[index] != index) {
			parent[index] = parent[parent[index]];
			index = parent[index];
		}
		return index;
	}

	private void union(int a, int b) {// union到小的index
		int aParent = find(a);
		int bParent = find(b);
		if (aParent < bParent) {
			parent[bParent] = aParent;
		} else {
			parent[aParent] = bParent;
		}
	}
}
