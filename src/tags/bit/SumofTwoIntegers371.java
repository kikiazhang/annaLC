package tags.bit;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the
 * operator + and -.
 * 
 * Example 1:
 * 
 * Input: a = 1, b = 2 Output: 3 Example 2:
 * 
 * Input: a = -2, b = 3 Output: 1
 */
public class SumofTwoIntegers371 {
	public int getSum(int a, int b) {
		// 直到car没有了，就剩a
		while (b != 0) {
			// 异或，表示此位只有一个1时
			int xor = a ^ b;
			// 与，表示改进位时，并且右移一位
			int car = (a & b) << 1;
			a = xor;
			b = car;
		}
		return a;
	}
}
