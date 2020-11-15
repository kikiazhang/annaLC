package tags.math;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * 
 * Example 1:
 * 
 * Input: 123 Output: 321 Example 2:
 * 
 * Input: -123 Output: -321 Example 3:
 * 
 * Input: 120 Output: 21
 */
public class ReverseInteger7 {
	public int reverse(int x) {
		if (x < 10 && x > -10)
			return x;

		int num = 0;
		while (x != 0) {
			int cur = x % 10;// -10 % 10 = -1
			x /= 10;
			// watch out the boundary
			if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && cur > 7))
				return 0;
			if (num < Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE / 10 && cur < -8))
				return 0;
			num = num * 10 + cur;
		}
		return num;
	}
}
