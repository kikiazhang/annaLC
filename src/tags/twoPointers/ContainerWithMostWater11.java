package tags.twoPointers;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with
 * x-axis forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container and n is at least 2. Input:
 * [1,8,6,2,5,4,8,3,7] Output: 49
 */
public class ContainerWithMostWater11 {
	// o(n)
	public int maxArea(int[] height) {
		if (height == null || height.length == 0)
			return 0;
		int left = 0, right = height.length - 1;
		int res = 0;
		while (left < right) {
			res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return res;
	}
}
