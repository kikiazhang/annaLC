package amazonOA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimalUtilization {
	/**
	 * Given 2 lists a and b. Each element is a pair of integers where the first
	 * integer represents the unique id and the second integer represents a value.
	 * Your task is to find an element from a and an element form b such that the
	 * sum of their values is less or equal to target and as close to target as
	 * possible. Return a list of ids of selected elements. If no pair is possible,
	 * return an empty list.
	 * 
	 * Example 1:
	 * 
	 * Input: a = [[1, 2], [2, 4], [3, 6]] b = [[1, 2]] target = 7
	 * 
	 * Output: [[2, 1]]
	 * 
	 * Explanation: There are only three combinations [1, 1], [2, 1], and [3, 1],
	 * which have a total sum of 4, 6 and 8, respectively. Since 6 is the largest
	 * sum that does not exceed 7, [2, 1] is the optimal pair.
	 */
	public List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
		Collections.sort(a, (i, j) -> i[1] - j[1]);
		Collections.sort(b, (i, j) -> i[1] - j[1]);
		List<int[]> result = new ArrayList<>();
		int max = Integer.MIN_VALUE;
		int m = a.size();
		int n = b.size();
		int i = 0;
		int j = n - 1;
		while (i < m && j >= 0) {
			int sum = a.get(i)[1] + b.get(j)[1];// sum of values
			if (sum > target) {// skip the sum > target, j--
				--j;
			} else {
				if (max <= sum) {// update the max sum
					if (max < sum) {// clear max
						max = sum;
						result.clear();
					}
					result.add(new int[] { a.get(i)[0], b.get(j)[0] });
					int index = j - 1;
					// add res when multiple same values in b
					while (index >= 0 && b.get(index)[1] == b.get(index + 1)[1]) {
						result.add(new int[] { a.get(i)[0], b.get(index--)[0] });
					}
				}
				++i;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		OptimalUtilization m = new OptimalUtilization();

		int[][] a = { { 1, 8 }, { 2, 15 }, { 3, 9 } };
		List<int[]> aList = new ArrayList<int[]>();
		Collections.addAll(aList, a);
		int[][] b = { { 1, 8 }, { 2, 11 }, { 3, 12 } };
		List<int[]> bList = new ArrayList<int[]>();
		Collections.addAll(bList, b);
		int target = 20;

		List<int[]> res = m.getPairs(aList, bList, target);
		for (int[] list : res) {
			for (int i : list) {
				System.out.print(i + " ");
			}
			System.out.print("\n");
		}

	}
}
