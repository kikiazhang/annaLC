package tags.slidingWindow;

import java.util.TreeMap;

/**
 * Example 1:
 * 
 * Input: nums = [8,2,4,7], limit = 4 Output: 2 Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4. [8,2] with maximum absolute
 * diff |8-2| = 6 > 4. [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4. [2] with maximum absolute
 * diff |2-2| = 0 <= 4. [2,4] with maximum absolute diff |2-4| = 2 <= 4. [2,4,7]
 * with maximum absolute diff |2-7| = 5 > 4. [4] with maximum absolute diff
 * |4-4| = 0 <= 4. [4,7] with maximum absolute diff |4-7| = 3 <= 4. [7] with
 * maximum absolute diff |7-7| = 0 <= 4. Therefore, the size of the longest
 * subarray is 2. Example 2:
 * 
 * Input: nums = [10,1,2,4,7,2], limit = 5 Output: 4 Explanation: The subarray
 * [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 * 
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0 Output: 3
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanLimit1438 {
	// o(n) treeMap + slidingWindow
	public int longestSubarray(int[] A, int limit) {
		int i = 0, j;// i = st, j = ed
		TreeMap<Integer, Integer> m = new TreeMap<>();// num, occur count
		// sliding window
		int max = 0;
		for (j = 0; j < A.length; j++) {
			m.put(A[j], 1 + m.getOrDefault(A[j], 0));
			if (m.lastEntry().getKey() - m.firstEntry().getKey() > limit) {// 大于limit，需要去掉st
				m.put(A[i], m.get(A[i]) - 1);// st次数--， st++
				if (m.get(A[i]) == 0)// 次数为0，从map中remove
					m.remove(A[i]);
				i++;
			} else {
				max = Math.max(max, j - i + 1);
			}
		}
		return max;
	}
}
