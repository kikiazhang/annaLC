package freq.five;

public class ClimbingStairs {

	/**
	 * Input: 3 Output: 3 Explanation: There are three ways to climb to the top. 1.
	 * 1 step + 1 step + 1 step 2. 1 step + 2 steps 3. 2 steps + 1 step
	 */
	public int climbStairs(int n) {
		if (n < 2)
			return 1;
		int one = 1;
		int two = 1;
		for (int i = 2; i <= n; i++) {
			int tmp = one;
			one = one + two;
			two = tmp;
		}
		return one;
	}
}
