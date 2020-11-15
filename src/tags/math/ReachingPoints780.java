package tags.math;

/**
 * A move consists of taking a point (x, y) and transforming it to either (x,
 * x+y) or (x+y, y).
 * 
 * Given a starting point (sx, sy) and a target point (tx, ty), return True if
 * and only if a sequence of moves exists to transform the point (sx, sy) to
 * (tx, ty). Otherwise, return False.
 * 
 * Examples: Input: sx = 1, sy = 1, tx = 3, ty = 5 Output: True Explanation: One
 * series of moves that transforms the starting point to the target is: (1, 1)
 * -> (1, 2) (1, 2) -> (3, 2) (3, 2) -> (3, 5)
 * 
 * Input: sx = 1, sy = 1, tx = 2, ty = 2 Output: False
 * 
 * Input: sx = 1, sy = 1, tx = 1, ty = 1 Output: True
 */
public class ReachingPoints780 {
	// time limit exceeded o(2^(tx+ty)) space: o(tx*ty)
	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
		if (sx > tx || sy > ty)
			return false;
		if (sx == tx && sy == ty)
			return true;
		return reachingPoints(sx + sy, sy, tx, ty) || reachingPoints(sx, sx + sy, tx, ty);
	}

	// O(logN) where N = max(tx,ty).
	public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
		while (sx < tx && sy < ty)
			// to (x, y+kx) or (x+ky,y)
			if (tx < ty)
				ty %= tx;// ty大，如果tx==sx，那么ty-sy一定是tx的倍数，如果不是就取余数，说明有几个是tx组成的
			else
				tx %= ty;
		// 缩小到任意tx ty到sx sy
		return sx == tx && sy <= ty && (ty - sy) % sx == 0 || sy == ty && sx <= tx && (tx - sx) % sy == 0;
	}

	// 整理版
	public boolean reachingPoints3(int sx, int sy, int tx, int ty) {
		if (sx > tx || sy > ty)
			return false;
		if (sx == tx && (ty - sy) % sx == 0)
			return true;
		if (sy == ty && (tx - sx) % sy == 0)
			return true;
		return reachingPoints(sx, sy, tx % ty, ty % tx);
	}
}
