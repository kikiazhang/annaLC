package freq.four;

public class Sqrtx {

	/**
	 * Example 1:
	 * 
	 * Input: 4 Output: 2 Example 2:
	 * 
	 * Input: 8 Output: 2 Explanation: The square root of 8 is 2.82842..., and since
	 * the decimal part is truncated, 2 is returned.
	 */
	public int mySqrt(int x) {
		if (x <= 1)
			return x;

		int left = 1;
		int right = Integer.MAX_VALUE;
		while (true) {
			int mid = left + (right - left) / 2;
			// use devide to avoid overflow
			if (mid > x / mid) {
				right = mid - 1;
			} else {
				if (mid + 1 > x / (mid + 1)) {
					return mid;
				}
				left = mid + 1;
			}
		}
	}
}
