package amazonOA;

public class SubstringsExactlyKdistinctChars {
	/**
	 * Given a string s and an int k, return an int representing the number of
	 * substrings (not unique) of s with exactly k distinct characters. If the given
	 * string doesn't have k distinct characters, return 0.
	 * https://leetcode.com/problems/subarrays-with-k-different-integers
	 * 
	 * Example 1:
	 * 
	 * Input: s = "pqpqs", k = 2 Output: 7 Explanation: ["pq", "pqp", "pqpq", "qp",
	 * "qpq", "pq", "qs"] Example 2:
	 * 
	 * Input: s = "aabab", k = 3 Output: 0 Constraints:
	 * 
	 * The input string consists of only lowercase English letters [a-z] 0 ≤ k ≤ 26
	 */

	public static int SubstringWithKDistinctChars(String s, int k) {
		int index = 0;
		int hi = 0, lo = 0;
		int[] ch = new int[26];
		int[] forIndex = new int[26];
		int sum = 0;// res
		int count = 0;// how many dist char
		int countForIndex = 0;
		int flag = 0;
		// An edge case where k is bigger than s.length is not tested here

		for (int i = 0; i < s.length(); i++) {
			if (flag == 0) {
				ch[s.charAt(i) - 'a']++;
				if (ch[s.charAt(i) - 'a'] == 1)
					count++;
			}
			flag = 0;
			if (count < k)
				continue;
			else if (count == k) {// save sum, and need to remove one dist char
				for (int j = 0; j < ch.length; j++)// 26
					forIndex[j] = ch[j];
				countForIndex = count;
				index = lo;// moving index to remove dist char
				while (countForIndex == k) {
					if (forIndex[s.charAt(index) - 'a'] == 1)
						countForIndex--; // Equivalent to break (supposed to be)
					else {
						forIndex[s.charAt(index) - 'a']--;
						index++;
					}
				}
				sum = sum + index - lo + 1;// index - lo + 1 = num to save (pppq, ppq , pq)
			} else // count > k
			{
				flag = 1;
				while (count > k) {// sliding window to remove dist char
					ch[s.charAt(lo) - 'a']--;
					if (ch[s.charAt(lo) - 'a'] == 0)
						count--;
					lo++;
				}
				i--;
			}
		}
		return sum;
	}
}
