package ms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PartitionArrayNSubsetsWithBalancedSum {

	/**
	 * Give you one sorted array, please put them into n buckets, we need to ensure
	 * we get n sub array with approximately equal weights. Example; input {1, 2, 3,
	 * 4, 5} n = 3 output [[[5],[1,4],[2,3]];
	 * 
	 */
	public static List<List<Integer>> part(int[] T, int n) {
		int[] nums = new int[n];// every sub list's sum
		// peek the current max sum sub list
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return nums[a.intValue()] - nums[b.intValue()];
			}
		});
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			res.add(new ArrayList<>());
			pq.add(i);
		}

		for (int i = T.length - 1; i >= 0; i--) {
			int c = pq.poll();
			res.get(c).add(T[i]);
			nums[c] += T[i];
			pq.add(c);
		}
		return res;
	}

	public static void main(String[] args) {
		List<List<Integer>> result = part(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 3);
		System.out.println(result);
	}
}
