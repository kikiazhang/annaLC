package tags.math;

/**
 * Given a positive integer N, how many ways can we write it as a sum of
 * consecutive positive integers?
 * 
 * Example 1:
 * 
 * Input: 5 Output: 2 Explanation: 5 = 5 = 2 + 3 Example 2:
 * 
 * Input: 9 Output: 3 Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 */
public class ConsecutiveNumbersSum829 {
	public int consecutiveNumbersSum(int N) {
		int sum = 0, ans = 0;
		// (x+1) + (x+2) ... + (x + i) = N
		// i*x + i(i+1)/2 = N
		// x = (N - sum)/i
		for (int i = 1; sum < N; i++) {
			sum += i;
			if (((N - sum) % i) == 0)
				ans++;
		}
		return ans;
	}
}
