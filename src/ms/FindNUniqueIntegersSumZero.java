package ms;

public class FindNUniqueIntegersSumZero {
	/**
	 * 
	 * Given an integer n, return any array containing n unique integers such that
	 * they add up to 0.
	 * 
	 * Example 1:
	 * 
	 * Input: n = 5 Output: [-7,-1,1,3,4] Explanation: These arrays also are
	 * accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4]. Example 2:
	 * 
	 * Input: n = 3 Output: [-1,0,1]
	 * 
	 */

	public int[] sumZero(int n) {
		if (n <= 0)
			return new int[0];

		int[] res = new int[n];

		int idx = 0;
		for (int i = 1; i <= n / 2; i++) {
			res[idx++] = i;
			res[idx++] = i * (-1);
		}
		if (n % 2 != 0) {
			res[n - 1] = 0;
		}
		return res;
	}

	public static void main(String[] args) {
		FindNUniqueIntegersSumZero m = new FindNUniqueIntegersSumZero();

		int[] res = m.sumZero(5);
		for (int r : res)
			System.out.println(r);
	}
}
