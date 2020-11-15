package ms;

import java.util.HashMap;
import java.util.Map;

public class NumbersWithEqualDigitSum {

	/**
	 * A=[51,71,17,42] return 93, (51, 42) > (17, 71) B=[42,33,60] return 102, 42+60
	 * C=[51,32,43], return -1, no same add-up digits sum
	 * 
	 */

	// Java O(NlogK) time complexity & O(N) space complexity solution
	public int solution(int[] A) {
		if (A == null || A.length <= 1)
			return -1;

		Map<Integer, Integer> map = new HashMap<>();// sumDigit, max index value for now
		int res = -1;
		for (int i = 0; i < A.length; i++) {
			int sumDigit = sumDigit(A[i]);
			if (map.containsKey(sumDigit)) {
				res = Math.max(res, map.get(sumDigit) + A[i]);
				map.put(sumDigit, Math.max(A[i], map.get(sumDigit)));

			} else {
				map.put(sumDigit, A[i]);
			}

		}
		return res;
	}

	private int sumDigit(int a) {
		a = Math.abs(a);

		int res = 0;
		while (a > 0) {
			res += a % 10;
			a /= 10;
		}
		return res;
	}

	public static void main(String[] args) {
		NumbersWithEqualDigitSum main = new NumbersWithEqualDigitSum();
		int[][] testcases = { { 51, 71, 17, 42, 33, 44, 24, 62 }, { 51, 71, 17, 42, 60 }, { 42, 33, 60 }, { 51, 32, 43 } };
		for (int[] testcase : testcases)
			System.out.println(main.solution(testcase));
	}
}
