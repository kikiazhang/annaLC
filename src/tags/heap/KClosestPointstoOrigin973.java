package tags.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointstoOrigin973 {
	// priorityQueue
	public int[][] kClosest(int[][] points, int K) {
		if (points == null || points.length == 0 || points[0].length == 0)
			return null;

		PriorityQueue<int[]> p = new PriorityQueue<int[]>(points.length, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1];
			}
		});

		for (int[] pairs : points) {
			p.offer(pairs);
			if (p.size() > K) {
				p.poll();
			}
		}
		int[][] res = new int[K][2];
		while (K > 0) {
			res[--K] = p.poll();
		}
		return res;
	}

	// quick sort, the average time complexity is O(N) , but just like quick sort,
	// in the worst case, this solution would be degenerated to O(N^2)
	public int[][] kClosest2(int[][] points, int K) {
		int len = points.length, l = 0, r = len - 1;
		while (l <= r) {
			int mid = helper(points, l, r);
			if (mid == K)
				break;
			if (mid < K) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return Arrays.copyOfRange(points, 0, K);
	}

	private int helper(int[][] A, int l, int r) {
		int[] pivot = A[l];
		while (l < r) {
			while (l < r && compare(A[r], pivot) >= 0)
				r--;
			A[l] = A[r];
			while (l < r && compare(A[l], pivot) <= 0)
				l++;
			A[r] = A[l];
		}
		A[l] = pivot;
		return l;
	}

	// sort o(nlogn)
	private int compare(int[] p1, int[] p2) {
		return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
	}

	public int[][] kClosest3(int[][] points, int K) {
		Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
		return Arrays.copyOfRange(points, 0, K);
	}
}
