package tags.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Suppose you have a random list of people standing in a queue. Each person is
 * described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height
 * greater than or equal to h. Write an algorithm to reconstruct the queue.
 * 
 * Note: The number of people is less than 1,100.
 * 
 * 
 * Example
 * 
 * Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 * Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionbyHeight406 {
	// greedy o(n^2)
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// if the heights are equal, compare k-values
				return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
			}
		});

		List<int[]> output = new LinkedList<>();
		for (int[] p : people) {
			output.add(p[1], p);
		}

		int n = people.length;
		return output.toArray(new int[n][2]);
	}

	public int[][] reconstructQueue2(int[][] people) {
		PriorityQueue<int[]> p = new PriorityQueue<>((a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			} else {
				return b[0] - a[0];
			}
		});
		for (int[] i : people) {
			p.offer(i);
		}
		List<int[]> res = new ArrayList<>();
		while (!p.isEmpty()) {
			int[] cur = p.poll();
			res.add(cur[1], cur);
		}
		return res.toArray(new int[people.length][2]);
	}
}
