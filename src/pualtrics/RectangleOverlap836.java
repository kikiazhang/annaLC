package pualtrics;

/**
 * An axis-aligned rectangle is represented as a list [x1, y1, x2, y2], where
 * (x1, y1) is the coordinate of its bottom-left corner, and (x2, y2) is the
 * coordinate of its top-right corner. Its top and bottom edges are parallel to
 * the X-axis, and its left and right edges are parallel to the Y-axis.
 * 
 * Two rectangles overlap if the area of their intersection is positive. To be
 * clear, two rectangles that only touch at the corner or edges do not overlap.
 * 
 * Given two axis-aligned rectangles rec1 and rec2, return true if they overlap,
 * otherwise return false.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3] Output: true Example 2:
 * 
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1] Output: false
 */
public class RectangleOverlap836 {
	public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		// 小x2大于大x1（x重叠） && 小y2大于大y1（y重叠）
		return Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
				&& Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);
	}

	// (count area) Rectangle Area 223
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int area = Math.abs(C - A) * Math.abs(D - B) + Math.abs(G - E) * Math.abs(H - F);
		if (E >= C || B >= H || A >= G || F >= D) {
			return area;
		} else {
			return area - Math.abs(Math.min(C, G) - Math.max(A, E)) * Math.abs(Math.min(D, H) - Math.max(B, F));
		}
	}
}
