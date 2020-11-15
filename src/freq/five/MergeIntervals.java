package freq.five;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

	/**
	 * Input: [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
	 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
	 */
	public int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length == 0)
			return intervals;

		List<int[]> res = new ArrayList<>();
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0])
					return a[1] - b[1];
				return a[0] - b[0];
			}
		});

		int st = intervals[0][0];
		int end = intervals[0][1];
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] <= end) {
				//拿最大
				end = Math.max(end, intervals[i][1]);
			} else {
				//初始化array with values
				int[] tmp = { st, end };
				res.add(tmp);
				st = intervals[i][0];
				end = intervals[i][1];
			}
		}
		//记录最后一个
		int[] tmp = { st, end };
		res.add(tmp);
		//list to array
		return res.toArray(new int[res.size()][2]);
	}
}
