package pualtrics;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * Only one letter can be changed at a time. Each transformed word must exist in
 * the word list. Note:
 * 
 * Return 0 if there is no such transformation sequence. All words have the same
 * length. All words contain only lowercase alphabetic characters. You may
 * assume no duplicates in the word list. You may assume beginWord and endWord
 * are non-empty and are not the same. Example 1:
 * 
 * Input: beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 
 * Output: 5
 * 
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" ->
 * "dog" -> "cog", return its length 5. Example 2:
 * 
 * Input: beginWord = "hit" endWord = "cog" wordList =
 * ["hot","dot","dog","lot","log"]
 * 
 * Output: 0
 */
public class WordLadder127 {
	public int ladderLength(String b, String e, List<String> wordList) {
		Set<String> set = new HashSet<>(wordList);
		if (b == null || e == null || b.length() != e.length() || !set.contains(e))
			return 0;

		Queue<String> q = new LinkedList<>();
		q.offer(b);
		int step = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				String cur = q.poll();
				if (cur.equals(e))
					return step + 1;
				char[] chars = cur.toCharArray();
				for (int j = 0; j < chars.length; j++) {
					char c = chars[j];
					for (char k = 'a'; k <= 'z'; k++) {
						chars[j] = k;
						String tmp = String.valueOf(chars);
						if (set.contains(tmp)) {
							q.offer(tmp);
							set.remove(tmp);
						}
					}
					chars[j] = c;
				}
			}
			step++;
		}
		return 0;
	}
}
