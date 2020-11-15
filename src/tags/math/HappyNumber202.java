package tags.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is "happy".
 * 
 * A happy number is a number defined by the following process: Starting with
 * any positive integer, replace the number by the sum of the squares of its
 * digits, and repeat the process until the number equals 1 (where it will
 * stay), or it loops endlessly in a cycle which does not include 1. Those
 * numbers for which this process ends in 1 are happy numbers.
 * 
 * Return True if n is a happy number, and False if not.
 * 
 * Example:
 * 
 * Input: 19 Output: true Explanation: 12 + 92 = 82 82 + 22 = 68 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber202 {
	// o(logn) space:o(logn)
	public boolean isHappy(int n) {
		// 不能有cycle
		Set<Integer> set = new HashSet<>();
		// 最后为1
		while (n != 1) {
			set.add(n);
			n = helper(n);
			if (set.contains(n))
				return false;
		}
		return n == 1;
	}

	// 计算digit^2 sum
	private int helper(int n) {
		int num = 0;
		while (n > 0) {
			num = num + (n % 10) * (n % 10);
			n = n / 10;
		}
		return num;
	}

	// Floyd's cycle (check whether has cycle) o(logn) space: 1
	public boolean isHappy2(int n) {
		int slowRunner = n;
		int fastRunner = helper(n);
		while (fastRunner != 1 && slowRunner != fastRunner) {
			slowRunner = helper(slowRunner);
			fastRunner = helper(helper(fastRunner));
		}
		return fastRunner == 1;
	}
}
