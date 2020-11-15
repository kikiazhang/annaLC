package freq.four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	/**
	 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"], Output: [
	 * ["ate","eat","tea"], ["nat","tan"], ["bat"] ]
	 */
	//O(m*nlogn)
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<>();

		if (strs == null || strs.length == 0)
			return res;
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String str = String.valueOf(chars);
			if (!map.containsKey(str)) {
				map.put(str, new ArrayList<>());
			}
			map.get(str).add(s);
		}
		res.addAll(map.values());
		return res;
	}
}
