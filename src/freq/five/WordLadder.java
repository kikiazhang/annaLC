package freq.five;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	/**
	 * Input: beginWord = "hit", endWord = "cog", wordList =
	 * ["hot","dot","dog","lot","log","cog"]
	 * 
	 * Output: 5
	 */

	public int ladderLength(String b, String e, List<String> wordList) {
		if (b == null || e == null || b.length() == 0 || e.length() == 0)
			return 0;

		Set<String> wordSet = new HashSet<>();
		wordSet.addAll(wordList);
		if (!wordSet.contains(e))
			return 0;

		Queue<String> q = new LinkedList<>();
		q.offer(b);
		int level = 1;
		while (!q.isEmpty()) {
			// loop the whole layer
			int size = q.size();
			for (int s = 0; s < size; s++) {
				// every string
				String tmp = q.poll();
				char[] tmpArr = tmp.toCharArray();
				for (int i = 0; i < tmpArr.length; i++) {
					char oldChar = tmpArr[i];
					for (char c = 'a'; c <= 'z'; c++) {
						if (c == oldChar)
							continue;
						tmpArr[i] = c;
						String newString = String.valueOf(tmpArr);
						if (newString.equals(e))
							return level + 1;
						if (wordSet.contains(newString)) {
							q.offer(newString);
							wordSet.remove(newString);
						}
					}
					tmpArr[i] = oldChar;
				}
			}
			// end of this lev
			level++;
		}
		return 0;
	}
}
