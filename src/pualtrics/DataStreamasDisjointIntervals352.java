package pualtrics;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals.
 * 
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6,
 * ..., then the summary will be:
 * 
 * [1, 1] [1, 1], [3, 3] [1, 1], [3, 3], [7, 7] [1, 3], [7, 7] [1, 3], [6, 7]
 */
public class DataStreamasDisjointIntervals352 {
	// treeMap
	TreeMap<Integer, int[]> map;

	/** Initialize your data structure here. */
	public DataStreamasDisjointIntervals352() {
		map = new TreeMap<>();
	}

	public void addNum(int val) {
		if (map.containsKey(val))
			return;
		Integer l = map.floorKey(val);
		Integer h = map.ceilingKey(val);
		// [1,1] 2 [3,3]
		if (l != null && h != null && map.get(l)[1] + 1 == val && h == val + 1) {
			map.get(l)[1] = map.get(h)[1];
			map.remove(h);
		} else if (l != null && map.get(l)[1] + 1 >= val) {// [1,3] 2
			map.get(l)[1] = Math.max(map.get(l)[1], val);
		} else if (h != null && h == val + 1) {// 2 [3,5]
			map.put(val, new int[] { val, map.get(h)[1] });
			map.remove(h);
		} else {// [1,1] 3 [5,5]
			map.put(val, new int[] { val, val });
		}
	}

	public int[][] getIntervals() {
		List<int[]> res = new ArrayList<>(map.values());
		return res.toArray(new int[res.size()][]);
	}
}
