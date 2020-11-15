package tags.twoPointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two lists of closed intervals, each list of intervals is pairwise
 * disjoint and in sorted order.
 * 
 * Return the intersection of these two interval lists.
 * 
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real
 * numbers x with a <= x <= b. The intersection of two closed intervals is a set
 * of real numbers that is either empty, or can be represented as a closed
 * interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 */
public class IntervalListIntersections986 {
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		List<int[]> res = new ArrayList<>();
		if (A == null || A.length == 0)
			return res.toArray(new int[res.size()][]);
		if (B == null || B.length == 0)
			return res.toArray(new int[res.size()][]);

		int[] tmp = new int[2];
		int a = 0, b = 0;
		while (a < A.length && b < B.length) {
			if (A[a][0] < B[b][0]) {
				if (A[a][1] < B[b][0]) {// no intersection
					a++;
				} else {
					tmp[0] = Math.max(A[a][0], B[b][0]);
					tmp[1] = Math.min(A[a][1], B[b][1]);
					if (A[a][1] > B[b][1])
						b++;
					else
						a++;
					res.add(tmp.clone());
				}
			} else {
				if (B[b][1] < A[a][0]) {// no intersection
					b++;
				} else {
					tmp[0] = Math.max(A[a][0], B[b][0]);
					tmp[1] = Math.min(A[a][1], B[b][1]);
					if (A[a][1] > B[b][1])
						b++;
					else
						a++;
					res.add(tmp.clone());
				}
			}
		}
		return res.toArray(new int[res.size()][]);
	}

	// 整理版 o(m + n) space: o(m + n) when filling the array
	public int[][] intervalIntersection2(int[][] A, int[][] B) {
		List<int[]> res = new ArrayList<>();
		if (A == null || A.length == 0)
			return res.toArray(new int[res.size()][]);
		if (B == null || B.length == 0)
			return res.toArray(new int[res.size()][]);

		int a = 0, b = 0;
		while (a < A.length && b < B.length) {
			int lo = Math.max(A[a][0], B[b][0]);
			int hi = Math.min(A[a][1], B[b][1]);
			if (lo <= hi)
				res.add(new int[] { lo, hi });

			// Remove the interval with the smallest endpoint
			if (A[a][1] < B[b][1])
				a++;
			else
				b++;
		}
		return res.toArray(new int[res.size()][]);
	}
}
