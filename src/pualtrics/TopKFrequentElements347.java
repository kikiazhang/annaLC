package pualtrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2] Example 2:
 * 
 * Input: nums = [1], k = 1 Output: [1]
 */
public class TopKFrequentElements347 {
	// priorityqueue
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : nums) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		int[] res = new int[k];
		PriorityQueue<Map.Entry<Integer, Integer>> p = new PriorityQueue<>(k,
				new Comparator<Map.Entry<Integer, Integer>>() {
					@Override
					public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
						return e1.getValue() - e2.getValue();
					}
				});
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			p.offer(e);
			if (p.size() > k) {
				p.poll();
			}
		}
		for (int i = k - 1; i >= 0; i--) {
			res[i] = p.poll().getKey();
		}

		return res;
	}

	// bucket sort
	public List<Integer> topKFrequent2(int[] nums, int k) {

		List<Integer>[] bucket = new List[nums.length + 1]; // freq [0, len]
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();// key, freq

		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}
		// insert into bucket
		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}

		List<Integer> res = new ArrayList<>();
		// start from len, count k
		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}

	// quicksort
	public int[] topKFrequent3(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();// key, freq
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		return quickSort(map, new ArrayList<Integer>(map.keySet()), 0, map.size() - 1, k);
	}

	private int[] quickSort(Map<Integer, Integer> map, List<Integer> keys, int st, int ed, int k) {
		int left = st, right = ed;
		int[] res = new int[k];
		while (left < right) {
			int pivot = partition(map, keys, left, right);
			if (pivot < k - 1) {
				left = pivot + 1;
			} else if (pivot > k + 1) {
				right = pivot - 1;
			} else {
				break;
			}
		}
		for (int i = 0; i < k; i++) {
			res[i] = keys.get(i);
		}
		return res;
	}

	private int partition(Map<Integer, Integer> map, List<Integer> keys, int st, int ed) {
		int left = st;
		int pivot = map.get(keys.get(st));// freq
		for (int i = st + 1; i <= ed; i++) {
			if (map.get(keys.get(i)) >= pivot) {// every freq > pivot to left
				Collections.swap(keys, i, ++left);
			}
		}
		// swap pivot and left
		Collections.swap(keys, st, left);
		return left;
	}
}
