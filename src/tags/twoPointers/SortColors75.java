package tags.twoPointers;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order
 * red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this
 * problem.
 * 
 * Example:
 * 
 * Input: [2,0,2,1,1,0] Output: [0,0,1,1,2,2]
 */
public class SortColors75 {
	// o(n)
	public void sortColors(int[] nums) {
		if (nums == null || nums.length == 0)
			return;

		int po = 0, p2 = nums.length - 1;// 放0，放2处
		int cur = 0;
		int tmp;
		while (cur <= p2) {// 到sorted好了p2结束
			if (nums[cur] == 0) {// 换向左
				tmp = nums[po];
				nums[po++] = nums[cur];// po++，下一个该放0的地方
				nums[cur++] = tmp;// cur++
			} else if (nums[cur] == 2) {// 换向右
				tmp = nums[cur];
				nums[cur] = nums[p2];// 此时cur不动，因为可能p2放的是0，还需要下一次放向左
				nums[p2--] = tmp;// p2--
			} else {// 1，下一位
				cur++;
			}
		}
	}
}
