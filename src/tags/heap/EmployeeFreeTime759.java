package tags.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We are given a list schedule of employees, which represents the working time
 * for each employee.
 * 
 * Each employee has a list of non-overlapping Intervals, and these intervals
 * are in sorted order.
 * 
 * Return the list of finite intervals representing common, positive-length free
 * time for all employees, also in sorted order.
 * 
 * (Even though we are representing Intervals in the form [x, y], the objects
 * inside are Intervals, not lists or arrays. For example, schedule[0][0].start
 * = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined). Also, we
 * wouldn't include intervals like [5, 5] in our answer, as they have zero
 * length.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]] Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common free time
 * intervals would be [-inf, 1], [3, 4], [10, inf]. We discard any intervals
 * that contain inf as they aren't finite. Example 2:
 * 
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]] Output:
 * [[5,6],[7,9]]
 */
public class EmployeeFreeTime759 {
	// o(clogn), where N is the number of employees, and C is the number of jobs
	// across all employees
	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
		List<Interval> res = new ArrayList<>();
		PriorityQueue<List<Interval>> p = new PriorityQueue<>((a, b) -> {
			if (a.get(0).start == b.get(0).start) {
				return a.get(0).end - b.get(0).end;
			} else {
				return a.get(0).start - b.get(0).start;
			}
		});
		for (List<Interval> l : schedule) {
			p.offer(l);
		}
		int st = Integer.MIN_VALUE;
		int end = Integer.MIN_VALUE;
		while (!p.isEmpty()) {
			List<Interval> list = p.poll();
			Interval cur = list.get(0);
			if (cur.start > end) {
				if (st != Integer.MIN_VALUE) {
					Interval in = new Interval(end, cur.start);
					res.add(in);
				}
				st = cur.start;
				end = cur.end;
			} else {
				end = Math.max(end, cur.end);
			}
			list.remove(0);
			if (!list.isEmpty())
				p.offer(list);
		}
		return res;
	}

	class Interval {
		public int start;
		public int end;

		public Interval() {
		}

		public Interval(int _start, int _end) {
			start = _start;
			end = _end;
		}
	}
}
