package ms;

import java.util.HashMap;
import java.util.Map;

public class MaxNetworkRank {

	/**
	 * A=[1,2,3,3], B=[2,3,1,4], and N = 4, return 4. The Chosen cities are 2 and 3,
	 * (2,1),(2,3),(3,1),(3,4)
	 * 
	 */

	public int solution(int[] A, int[] B, int N) {
		// city, number of roads
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			map.put(A[i], map.getOrDefault(A[i], 0) + 1);
			map.put(B[i], map.getOrDefault(B[i], 0) + 1);
		}
		// rank is city A + city B - 1
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			max = Math.max(map.get(A[i]) + map.get(B[i]) - 1, max);// -1 remove dulplication
		}
		return max;
	}

	public static void main(String[] args) {
		MaxNetworkRank m = new MaxNetworkRank();
		int[] A = { 1, 2, 3, 3 };
		int[] B = { 2, 3, 1, 4 };
		int N = 4;
		System.out.print(m.solution(A, B, N));
	}
}
