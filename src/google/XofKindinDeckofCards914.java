package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In a deck of cards, each card has an integer written on it.
 * 
 * Return true if and only if you can choose X >= 2 such that it is possible to
 * split the entire deck into 1 or more groups of cards, where:
 * 
 * Each group has exactly X cards. All the cards in each group have the same
 * integer.
 * 
 * 
 * Example 1:
 * 
 * Input: deck = [1,2,3,4,4,3,2,1] Output: true Explanation: Possible partition
 * [1,1],[2,2],[3,3],[4,4]. Example 2:
 * 
 * Input: deck = [1,1,1,2,2,2,3,3] Output: false´ Explanation: No possible
 * partition. Example 3:
 * 
 * Input: deck = [1] Output: false Explanation: No possible partition. Example
 * 4:
 * 
 * Input: deck = [1,1] Output: true Explanation: Possible partition [1,1].
 */
public class XofKindinDeckofCards914 {
	// O(NlogN * logN), where N is the number of votes
	public boolean hasGroupsSizeX(int[] deck) {
		int N = deck.length;
		int[] count = new int[10000];
		for (int c : deck)
			count[c]++;

		List<Integer> values = new ArrayList<>();
		for (int i = 0; i < 10000; ++i)
			if (count[i] > 0)
				values.add(count[i]);

		search: for (int X = 2; X <= N; ++X)
			if (N % X == 0) {
				for (int v : values)
					if (v % X != 0)
						continue search;
				return true;
			}

		return false;
	}

	// o(n)
	public boolean hasGroupsSizeX2(int[] deck) {
		Map<Integer, Integer> count = new HashMap<>();
		int res = 0;
		for (int i : deck)
			count.put(i, count.getOrDefault(i, 0) + 1);
		for (int i : count.values())
			res = gcd(i, res);
		return res > 1;
	}

	public int gcd(int a, int b) {
		return b > 0 ? gcd(b, a % b) : a;
	}
}
