package tags.binarySearch;

/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * 
 * Example 1:
 * 
 * Input: 2.00000, 10 Output: 1024.00000 Example 2:
 * 
 * Input: 2.10000, 3 Output: 9.26100 Example 3:
 * 
 * Input: 2.00000, -2 Output: 0.25000 Explanation: 2-2 = 1/22 = 1/4 = 0.25
 */
public class Pow50 {
	// time limit exceeded
	public double myPow(double x, int n) {
		boolean div = false;
		if (n < 0) {
			n = -1 * n;
			div = true;
		}
		double pow = 1;
		while (n > 0) {
			pow = pow * x;
			n--;
		}
		return div ? 1 / pow : pow;
	}

	// divide o(logn)
	public double myPow2(double x, int n) {
		if (n < 0) {
			return 1 / helper(x, -n);
		} else {
			return helper(x, n);
		}
	}

	private Double helper(double x, int n) {
		if (n == 0)
			return 1.0;

		double res = helper(x, n / 2);
		if (n % 2 == 0) {
			return res * res;
		} else {
			return res * res * x;
		}
	}
}
