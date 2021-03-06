package tags.greedy;

import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two
 * characters that are adjacent to each other are not the same.
 * 
 * If possible, output any possible result. If not possible, return the empty
 * string.
 * 
 * Example 1:
 * 
 * Input: S = "aab" Output: "aba" Example 2:
 * 
 * Input: S = "aaab" Output: ""
 */
public class ReorganizeString767 {
	public String reorganizeString(String S) {
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);// [char, count]
		int[] m = new int[26];
		for (int i = 0; i < S.length(); i++)
			m[S.charAt(i) - 'a']++; // map of char counts

		for (int i = 0; i < 26; i++) {
			if (m[i] != 0) {
				q.offer(new int[] { i, m[i] }); // add char counts to priority queue
			}
		}

		int[] prev = new int[] { -1, 0 };
		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (prev[1] > 0)
				q.offer(prev); // add back last used character

			sb.append((char) (cur[0] + 'a')); // append current character
			cur[1]--; // decrease count of current char since it's used
			prev = cur; // set this character as previous used
			if (q.isEmpty() && prev[1] > 0)
				return ""; // if we left with anything return ""
		}
		return sb.toString();
	}
}
