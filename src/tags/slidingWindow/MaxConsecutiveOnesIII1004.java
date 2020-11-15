package tags.slidingWindow;

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * 
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2 Output: 6 Explanation:
 * [1,1,1,0,0,1,1,1,1,1,1] Bolded numbers were flipped from 0 to 1. The longest
 * subarray is underlined. Example 2:
 * 
 * Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3 Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1] Bolded numbers were
 * flipped from 0 to 1. The longest subarray is underlined.
 */
public class MaxConsecutiveOnesIII1004 {
	public int longestOnes(int[] A, int K) {
		int st = 0, ed = 0;
		int max = 0;
		int count = 0;
		while (ed < A.length) {
			if (A[ed] == 0)
				count++;

			while (count > K) {
				max = Math.max(max, ed - st);
				if (A[st++] == 0) {
					count--;
				}
			}
			ed++;
		}
		max = Math.max(max, ed - st);
		return max;
	}
}
