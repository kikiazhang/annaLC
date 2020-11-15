package tags.bit;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND
 * of all numbers in this range, inclusive.
 * 
 * Example 1:
 * 
 * Input: [5,7] Output: 4 Example 2:
 * 
 * Input: [0,1] Output: 0
 */
public class BitwiseANDofNumbersRange21 {
	public int rangeBitwiseAnd(int m, int n) {
		// 看两个数能一起右移几位，移完后小的数左移回去shift就是结果
		int shift = 0;
		while (m < n) {
			m >>= 1;
			n >>= 1;
			++shift;
		}
		return m << shift;
	}
}
