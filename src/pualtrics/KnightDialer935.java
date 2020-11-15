package pualtrics;

import java.util.HashMap;
import java.util.Map;

/**
 * The chess knight has a unique movement, it may move two squares vertically
 * and one square horizontally, or two squares horizontally and one square
 * vertically (with both forming the shape of an L). The possible movements of
 * chess knight are shown in this diagaram:
 * 
 * A chess knight can move as indicated in the chess diagram below:
 * 
 * We have a chess knight and a phone pad as shown below, the knight can only
 * stand on a numeric cell (i.e. blue cell).
 * 
 * Given an integer n, return how many distinct phone numbers of length n we can
 * dial.
 * 
 * You are allowed to place the knight on any numeric cell initially and then
 * you should perform n - 1 jumps to dial a number of length n. All jumps should
 * be valid knight jumps.
 * 
 * As the answer may be very large, return the answer modulo 109 + 7.
 */
public class KnightDialer935 {
	public static final int max = 1000000007;// 10^9 + 7
	private static final int[][] dirs = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 },
			{ -2, -1 } };
	Map<String, Long> pathCount = new HashMap<>();

	// 任何位置开始，走n位，结果除以max
	public int knightDialer(int N) {
		long res = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				res = (res + helper(i, j, N)) % max;
			}
		}
		return (int) res;
	}

	public long helper(int i, int j, int n) {

		if (i < 0 || i >= 4 || j < 0 || j >= 3 || (i == 3 && j != 1))
			return 0;
		if (n == 1)
			return 1;
		String key = i + "_" + j + "_" + n;
		if (pathCount.containsKey(key))
			return pathCount.get(key);
		long ans = 0;
		for (int k = 0; k < dirs.length; k++) {
			ans += helper(i + dirs[k][0], j + dirs[k][1], n - 1) % max;
		}
		pathCount.put(key, ans);
		return ans;
	}
}
