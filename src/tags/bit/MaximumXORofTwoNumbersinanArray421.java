package tags.bit;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * 
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * 
 * Could you do this in O(n) runtime?
 * 
 * Example:
 * 
 * Input: [3, 10, 5, 25, 2, 8]
 * 
 * Output: 28
 * 
 * Explanation: The maximum result is 5 ^ 25 = 28.
 */
public class MaximumXORofTwoNumbersinanArray421 {
	/**
	 * Let's start from rewriting all numbers [3, 10, 5, 25, 2, 8] in binary from 3
	 * = (00011)_23=(00011) 2 ​ 10 = (01010)_210=(01010) 2 ​ 5 = (00101)_25=(00101)
	 * 2 ​ 25 = (11001)_225=(11001) 2 ​ 2 = (00010)_22=(00010) 2 ​ 8 =
	 * (01000)_28=(01000) 2 ​ To simplify the work with prefixes, better to use the
	 * same number of bits LL for all the numbers. It's enough to take LL equal to
	 * the length of the max number in the binary representation.
	 * 
	 * Now let's construct the max XOR starting from the leftmost bit. The absolute
	 * maximum one could have with L = 5L=5 bits here is (11111)_2(11111) 2 ​ . So
	 * let's check bit by bit:
	 * 
	 * Could we have the leftmost bit for XOR to be equal to 1-bit, i.e. max XOR to
	 * be equal to (1****)_2(1∗∗∗∗) 2 ​ ? Yes, for that it's enough to pair 25 =
	 * (11001)_225=(11001) 2 ​ with another number starting with the zero leftmost
	 * bit. So the max XOR is (1****)_2(1∗∗∗∗) 2 ​ .
	 * 
	 * Next step. Could we have max XOR to be equal to (11***)_2(11∗∗∗) 2 ​ ? For
	 * that, let's consider all prefixes of length 2 and check if there is a pair of
	 * them, p_1p 1 ​ and p_2p 2 ​ , such that its XOR is equal to 11: p_1 \oplus
	 * p_2 == 11p 1 ​ ⊕p 2 ​ ==11
	 * 
	 * 3 = (00***)_23=(00∗∗∗) 2 ​ 10 = (01***)_210=(01∗∗∗) 2 ​ 5 =
	 * (00***)_25=(00∗∗∗) 2 ​ 25 = (11***)_225=(11∗∗∗) 2 2 = (00***)_22=(00∗∗∗) 2 ​
	 * 8 = (01***)_28=(01∗∗∗) 2 ​
	 * 
	 * Yes, it's the case, for example, pair 5 = (00***)_25=(00∗∗∗) 2 ​ and 25 =
	 * (11***)_225=(11∗∗∗) 2 ​ , or 2 = (00***)_22=(00∗∗∗) 2 ​ and 25 =
	 * (11***)_225=(11∗∗∗) 2 ​ , or 3 = (00***)_23=(00∗∗∗) 2 ​ and 25 =
	 * (11***)_225=(11∗∗∗) 2 ​ .
	 */
	public int findMaximumXOR(int[] nums) {
		int maxNum = nums[0];
		for (int num : nums)
			maxNum = Math.max(maxNum, num);

		int L = (Integer.toBinaryString(maxNum)).length();

		int maxXor = 0, currXor;
		Set<Integer> prefixes = new HashSet<>();
		for (int i = L - 1; i > -1; --i) {
			// go to the next bit by the left shift
			maxXor <<= 1;
			// set 1 in the smallest bit
			currXor = maxXor | 1;
			prefixes.clear();
			// compute all possible prefixes
			// of length (L - i) in binary representation
			for (int num : nums)
				prefixes.add(num >> i);
			// Update maxXor, if two of these prefixes could result in currXor.
			// Check if p1^p2 == currXor, i.e. p1 == currXor^p2.
			for (int p : prefixes) {
				if (prefixes.contains(currXor ^ p)) {
					maxXor = currXor;
					break;
				}
			}
		}
		return maxXor;
	}
}
