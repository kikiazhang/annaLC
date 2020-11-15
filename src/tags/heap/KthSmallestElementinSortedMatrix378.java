package tags.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix.
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 * 
 * Example:
 * 
 * matrix = [ [ 1, 5, 9], [10, 11, 13], [12, 13, 15] ], k = 8,
 * 
 * return 13.
 */
public class KthSmallestElementinSortedMatrix378 {
	// priorityQueue
	public int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;

		PriorityQueue<List<Integer>> p = new PriorityQueue<>(matrix.length, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> a, List<Integer> b) {
				return a.get(0) - b.get(0);
			}
		});
		for (int[] i : matrix) {
			List<Integer> list = Arrays.stream(i).boxed().collect(Collectors.toList());
			p.offer(list);
		}
		while (k > 1) {
			List<Integer> tmp = p.poll();
			tmp.remove(0);
			if (!tmp.isEmpty())
				p.offer(tmp);
			k--;
		}
		return p.poll().get(0);
	}

	// binary search o(nlog(max - min))
	public int kthSmallest2(int[][] matrix, int k) {

		int n = matrix.length;
		int start = matrix[0][0], end = matrix[n - 1][n - 1];
		while (start < end) {

			int mid = start + (end - start) / 2;
			// first number is the smallest and the second number is the largest
			int[] smallLargePair = { matrix[0][0], matrix[n - 1][n - 1] };

			int count = countLessEqual(matrix, mid, smallLargePair);

			if (count == k)
				return smallLargePair[0];

			if (count < k)
				start = smallLargePair[1]; // search higher
			else
				end = smallLargePair[0]; // search lower
		}
		return start;
	}

	private int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {

		int count = 0;
		int n = matrix.length, row = n - 1, col = 0;

		while (row >= 0 && col < n) {

			if (matrix[row][col] > mid) {

				// as matrix[row][col] is bigger than the mid, let's keep track of the
				// smallest number greater than the mid
				smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
				row--;

			} else {

				// as matrix[row][col] is less than or equal to the mid, let's keep track of the
				// biggest number less than or equal to the mid
				smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
				count += row + 1;
				col++;
			}
		}

		return count;
	}
}
