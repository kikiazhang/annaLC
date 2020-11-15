package tags.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time Each transformed word must exist in
 * the word list. Note that beginWord is not a transformed word. Note:
 * 
 * Return an empty list if there is no such transformation sequence. All words
 * have the same length. All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list. You may assume beginWord and
 * endWord are non-empty and are not the same. Example 1:
 * 
 * Input: beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 
 * Output: [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]
 */
public class WordLadderII126 {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>(wordList);
		Set<String> start = new HashSet<>();
		start.add(beginWord);
		dict.removeAll(start); // the dict contains the beginWord, remove the loop

		Map<String, List<String>> map = new HashMap<>();// str，all next poss str
		List<List<String>> res = new ArrayList<>();

		// bfs方法写入map，直到endword出现
		while (!start.isEmpty()) {
			if (start.contains(endWord)) {// 找到了
				break;
			}
			Set<String> set = new HashSet<>();// 要remove的
			for (String w : start) {
				List<String> po = generatePotentials(w, dict);
				if (!po.isEmpty()) {
					map.put(w, po);// w，w能到的所有新w
					set.addAll(po);
				}
			}
			dict.removeAll(set);// 去掉所有这一次出现的
			start = set;// 下一层
		}
		List<String> temp = new ArrayList<>();
		temp.add(beginWord);
		dfs(beginWord, endWord, map, res, temp);// 用map来dfs写入res
		return res;
	}

	// generate potential transformation
	public List<String> generatePotentials(String beginWord, Set<String> wordList) {
		List<String> list = new ArrayList<>();
		char[] chars = beginWord.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char o = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				if (c == o) {
					continue;
				}
				chars[i] = c;
				String newWord = new String(chars);
				if (wordList.contains(newWord)) {
					list.add(newWord);
				}
			}
			chars[i] = o;
		}
		return list;
	}

	public void dfs(String beginWord, String endWord, Map<String, List<String>> map, List<List<String>> res,
			List<String> temp) {
		if (beginWord.equals(endWord)) {// 找到了，放入temp
			res.add(new ArrayList<>(temp));
			return;
		}
		if (map.containsKey(beginWord)) {
			for (String w : map.get(beginWord)) {// begin接下来所有可能str，dfs
				temp.add(w);
				dfs(w, endWord, map, res, temp);
				temp.remove(temp.size() - 1);
			}
		}
	}
}
