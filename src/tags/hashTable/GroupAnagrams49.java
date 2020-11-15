package tags.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, group anagrams together.
 * 
 * Example:
 * 
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"], Output: [
 * ["ate","eat","tea"], ["nat","tan"], ["bat"] ]
 */
public class GroupAnagrams49 {
	// o(nklogk)
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		if (strs == null || strs.length == 0)
			return res;

		Map<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars); // Arrays.sort(chars)
			String newStr = String.valueOf(chars); // String.valueOf(chars)
			if (!map.containsKey(newStr)) {
				map.put(newStr, new ArrayList<>());
			}
			List<String> layer = map.get(newStr);
			layer.add(str);
		}
		res.addAll(map.values());
		return res;
	}

	// o(nk) where NN is the length of strs, and KK is the maximum length of a
	// string in strs.
	public List<List<String>> groupAnagrams2(String[] strs) {
		if (strs.length == 0)
			return new ArrayList<>();
		Map<String, List<String>> ans = new HashMap<>();
		int[] count = new int[26];
		for (String s : strs) {
			Arrays.fill(count, 0);
			for (char c : s.toCharArray())
				count[c - 'a']++;
			// #0#1... 整理成多少a多少b...
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < 26; i++) {
				sb.append('#');
				sb.append(count[i]);
			}
			String key = sb.toString();
			if (!ans.containsKey(key))
				ans.put(key, new ArrayList<>());
			ans.get(key).add(s);
		}
		return new ArrayList<>(ans.values());
	}
}
