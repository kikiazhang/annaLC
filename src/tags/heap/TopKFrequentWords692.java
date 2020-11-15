package tags.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 * 
 * Your answer should be sorted by frequency from highest to lowest. If two
 * words have the same frequency, then the word with the lower alphabetical
 * order comes first.
 * 
 * Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"] Explanation: "i" and "love" are the two most frequent
 * words. Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2: Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny",
 * "is", "is"], k = 4 Output: ["the", "is", "sunny", "day"] Explanation: "the",
 * "is", "sunny" and "day" are the four most frequent words, with the number of
 * occurrence being 4, 3, 2 and 1 respectively.
 */
public class TopKFrequentWords692 {
	public List<String> topKFrequent(String[] words, int k) {
		List<String> res = new ArrayList<>();
		if (words == null || words.length == 0)
			return res;

		Map<String, Integer> map = new HashMap<>();
		Map<Integer, List<String>> freq = new HashMap<>();
		int max = 1;
		freq.put(1, new ArrayList<>());
		for (String word : words) {
			if (map.containsKey(word)) {
				int count = map.get(word);
				if (freq.get(count + 1) == null) {
					freq.put(count + 1, new ArrayList<>());
				}
				map.put(word, count + 1);
				List<String> list = freq.get(count + 1);
				list.add(word);
				List<String> remove = freq.get(count);
				remove.remove(word);
				max = Math.max(max, count + 1);
			} else {
				map.put(word, 1);
				List<String> list = freq.get(1);
				list.add(word);
			}
		}
		for (int cur = max; cur >= 1; cur--) {
			if (freq.containsKey(cur)) {
				List<String> layer = freq.get(cur);
				Collections.sort(layer, new Comparator<String>() {
					@Override
					public int compare(String a, String b) {
						for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
							if (a.charAt(i) != b.charAt(i)) {
								return a.charAt(i) - b.charAt(i);
							}
						}
						return a.length() - b.length();
					}
				});
				if (layer.size() <= k - res.size()) {
					res.addAll(layer);
				} else {
					res.addAll(layer.subList(0, k - res.size()));
				}
			}
		}
		return res;
	}

	// o(nlogn)
	public List<String> topKFrequent2(String[] words, int k) {
		List<String> res = new ArrayList<>();
		if (words == null || words.length == 0)
			return res;

		Map<String, Integer> map = new HashMap<>();

		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		List<String> candidates = new ArrayList<String>(map.keySet());
		Collections.sort(candidates, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				if (map.get(a).equals(map.get(b))) {
					return a.compareTo(b);
				} else {
					return map.get(b) - map.get(a);// freq desc
				}
			}
		});
		return candidates.subList(0, k);
	}

	// PriorityQueue o(nlogk)
	public List<String> topKFrequent3(String[] words, int k) {
		Map<String, Integer> count = new HashMap<String, Integer>();
		for (String word : words) {
			count.put(word, count.getOrDefault(word, 0) + 1);
		}
		PriorityQueue<String> heap = new PriorityQueue<String>(
				(w1, w2) -> count.get(w1).equals(count.get(w2)) ? w2.compareTo(w1) : count.get(w1) - count.get(w2));// freq从小到大

		for (String word : count.keySet()) {
			heap.offer(word);
			if (heap.size() > k)
				heap.poll();// 小的拿出来
		}

		List<String> ans = new ArrayList<String>();
		while (!heap.isEmpty())
			ans.add(heap.poll());// 反序
		Collections.reverse(ans);
		return ans;
	}
}
