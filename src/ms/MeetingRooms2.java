package ms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms2 {

	/**
	 * Given an array of meeting time intervals consisting of start and end times
	 * [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.
	 */
	public int minMeetingRooms(int[][] intervals) {
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});

		int max = 0;
		int st = intervals[0][0];
		int end = intervals[0][1];
		for (int i = 1; i < intervals.length; i++) {
			if (end < intervals[i][0]) {
				max++;
				st = intervals[i][0];
				end = intervals[i][1];
			} else {
				end = Math.max(end, intervals[i][1]);
			}
		}
		max++;
		return max;
	}

	public int minMeetingRooms2(int[][] intervals) {
		Arrays.sort(intervals, Comparator.comparing((int[] itv) -> itv[0]));

		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int count = 0;
		for (int[] itv : intervals) {
			if (heap.isEmpty()) {
				count++;
				heap.offer(itv[1]);
			} else {
				if (itv[0] >= heap.peek()) {
					heap.poll();
				} else {
					count++;
				}

				heap.offer(itv[1]);
			}
		}

		return count;
	}

	public static void main(String[] args) {
		MeetingRooms2 m = new MeetingRooms2();

		int[][] intervals = { { 2, 15 }, { 36, 45 }, { 9, 29 }, { 16, 23 }, { 4, 9 } };

		System.out.println(m.minMeetingRooms(intervals));
		System.out.println(m.minMeetingRooms2(intervals));
	}
}
