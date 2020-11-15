package tags.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * Example 1:
 * 
 * Input: [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * 
 * Input: [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4] and [4,5]
 * are considered overlapping.
 */
public class MergeIntervals56 {

	public int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length == 0)
			return new int[0][0];
		// PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] > o2[1] ? -1
		// : 1);
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(intervals.length, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});
		for (int[] i : intervals) {
			pq.add(i);
		}
		List<int[]> resList = new ArrayList<>();
		int[] tmp = pq.poll();
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (tmp[1] >= cur[0]) {
				tmp[1] = Math.max(tmp[1], cur[1]);
			} else {
				resList.add(tmp.clone());
				tmp[0] = cur[0];
				tmp[1] = cur[1];
			}
		}
		resList.add(tmp.clone());
		return resList.toArray(new int[resList.size()][]);
	}
}
