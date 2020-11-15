package tags.sort;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval57 {

	/**
	 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
	 * Output: [[1,2],[3,10],[12,16]] Explanation: Because the new interval [4,8]
	 * overlaps with [3,5],[6,7],[8,10].
	 */
	public int[][] insert(int[][] intervals, int[] newInterval) {
		if (newInterval == null || newInterval.length != 2)
			return intervals;

		List<int[]> res = new ArrayList<>();
		int i = 0;
		//找到开始相交的interval
		while (i < intervals.length && intervals[i][1] < newInterval[0]) {
			// no overlap, intervals.get(i) before newInterval, save get(i), continue
			res.add(intervals[i]);
			i++;
		}
		//在相交中，不断更新newInterval
		while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
			newInterval = new int[] { Math.min(intervals[i][0], newInterval[0]),
					Math.max(newInterval[1], intervals[i][1]) };
			i++;
		}
		//需要放入的newInterval
		res.add(newInterval);
		//放完
		while (i < intervals.length) {
			res.add(intervals[i]);
			i++;
		}
		//list to array
		return res.toArray(new int[res.size()][2]);
	}
}
