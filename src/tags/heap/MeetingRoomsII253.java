package tags.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms
 * required.
 * 
 * Example 1:
 * 
 * Input: [[0, 30],[5, 10],[15, 20]] Output: 2 Example 2:
 * 
 * Input: [[7,10],[2,4]] Output: 1
 */
public class MeetingRoomsII253 {
	public int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;

		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});

		PriorityQueue<Integer> heap = new PriorityQueue<>();// save end time
		int count = 0;
		for (int[] itv : intervals) {
			if (heap.isEmpty()) {
				count++;
				heap.offer(itv[1]);
			} else {
				if (itv[0] >= heap.peek()) {
					heap.poll();// connect two to one
				} else {
					count++;// need more room
				}
				heap.offer(itv[1]);// new end time
			}
		}

		return count;
	}
}
