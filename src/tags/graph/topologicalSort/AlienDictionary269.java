package tags.graph.topologicalSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There is a new alien language which uses the latin alphabet. However, the
 * order among letters are unknown to you. You receive a list of non-empty words
 * from the dictionary, where words are sorted lexicographically by the rules of
 * this new language. Derive the order of letters in this language.
 * 
 * Example 1:
 * 
 * Input: [ "wrt", "wrf", "er", "ett", "rftt" ]
 * 
 * Output: "wertf" Example 2:
 * 
 * Input: [ "z", "x" ]
 * 
 * Output: "zx"
 */
public class AlienDictionary269 {
	public String alienOrder(String[] words) {
		Map<Character, Set<Character>> map = new HashMap<>();
		Map<Character, Integer> degree = new HashMap<>();
		// 初始化degree
		for (String str : words) {
			char[] chars = str.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				degree.put(chars[i], 0);
			}
		}
		// 比较两个词
		for (int i = 0; i < words.length - 1; i++) {
			String s1 = words[i];
			String s2 = words[i + 1];
			// Check that word2 is not a prefix of word1.
			if (s1.length() > s2.length() && s1.startsWith(s2)) {
				return "";
			}
			// 找到第一个不同
			int min = Math.min(s1.length(), s2.length());
			for (int j = 0; j < min; j++) {
				if (s1.charAt(j) != s2.charAt(j)) {
					Set<Character> set = map.getOrDefault(s1.charAt(j), new HashSet<>());
					if (set.add(s2.charAt(j))) {// 能加入，就增加c2 degree
						map.put(s1.charAt(j), set);// 写入c1
						degree.put(s2.charAt(j), degree.get(s2.charAt(j)) + 1);
					}
					break;
				}
			}
		}
		// bfs写入stringBuilder
		Queue<Character> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Character, Integer> entry : degree.entrySet()) {
			if (entry.getValue() == 0) {
				q.offer(entry.getKey());
			}
		}
		while (!q.isEmpty()) {
			char c = q.poll();
			sb.append(c);
			Set<Character> set = map.getOrDefault(c, new HashSet<>());
			for (Character ch : set) {
				degree.put(ch, degree.get(ch) - 1);
				if (degree.get(ch) == 0)
					q.offer(ch);
			}
		}
		// sb需要有所有char
		return sb.length() != degree.size() ? "" : sb.toString();
	}
}
