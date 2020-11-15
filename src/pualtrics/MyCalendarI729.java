package pualtrics;

import java.util.TreeMap;

/**
 * Implement a MyCalendar class to store your events. A new event can be added
 * if adding the event will not cause a double booking.
 * 
 * Your class will have the method, book(int start, int end). Formally, this
 * represents a booking on the half open interval [start, end), the range of
 * real numbers x such that start <= x < end.
 * 
 * A double booking happens when two events have some non-empty intersection
 * (ie., there is some time that is common to both events.)
 * 
 * For each call to the method MyCalendar.book, return true if the event can be
 * added to the calendar successfully without causing a double booking.
 * Otherwise, return false and do not add the event to the calendar.
 * 
 * Your class will be called like this: MyCalendar cal = new MyCalendar();
 * MyCalendar.book(start, end) Example 1:
 * 
 * MyCalendar(); MyCalendar.book(10, 20); // returns true MyCalendar.book(15,
 * 25); // returns false MyCalendar.book(20, 30); // returns true Explanation:
 * The first event can be booked. The second can't because time 15 is already
 * booked by another event. The third event can be booked, as the first event
 * takes every time less than 20, but not including 20.
 */
public class MyCalendarI729 {

	TreeMap<Integer, Integer> map; // start, end time

	public MyCalendarI729() {
		map = new TreeMap<>();
		map2 = new TreeMap<>();
	}

	// [start, end) o(logn)
	public boolean book(int start, int end) {
		Integer prev = map.floorKey(start);
		Integer next = map.ceilingKey(end);
		if ((prev == null || map.get(prev) <= start) && (next == null || end <= next)) {
			map.put(start, end);
			return true;
		}
		return false;
	}

	// fllow up, can have two meeting book at the same time, but can't be triple
	// book
	TreeMap<Integer, Integer> map2; // st/ed time, count

	// o(n) count the boundary
	public boolean book2(int start, int end) {
		// start++, end--
		map2.put(start, map.getOrDefault(start, 0) + 1);
		map2.put(end, map.getOrDefault(end, 0) - 1);

		int active = 0;
		// for loop every boundary, there can't be active >= 3
		for (int t : map2.values()) {
			active += t;
			if (active >= 3) {
				// remove current meeting
				map2.put(start, map2.get(start) - 1);
				map2.put(end, map2.get(end) + 1);
				return false;
			}
		}
		return true;
	}

	// fllow up 2, count the k-booking
	public int book3(int start, int end) {
		map2.put(start, map2.getOrDefault(start, 0) + 1);
		map2.put(end, map2.getOrDefault(end, 0) - 1);

		int active = 0, max = 0;
		for (int t : map2.values()) {
			active += t;
			max = Math.max(max, active);
		}
		return max;
	}
}
