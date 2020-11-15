package evernote;

public class ProductofArrayExceptSelf {
	/**
	 * Given an array nums of n integers where n > 1, return an array output such
	 * that output[i] is equal to the product of all the elements of nums except
	 * nums[i].
	 * 
	 * Example:
	 * 
	 * Input: [1,2,3,4] Output: [24,12,8,6] Note: Please solve it without division
	 * and in O(n).
	 */
	// 方法是取两个数组，一个记录这个数之前的乘积，另一个记录这个数之后的乘积，两者相乘就是答案
	public int[] productExceptSelf(int[] nums) {
		int[] result = new int[nums.length];

		int[] t1 = new int[nums.length];
		int[] t2 = new int[nums.length];

		t1[0] = 1;
		t2[nums.length - 1] = 1;

		// scan from left to right
		for (int i = 0; i < nums.length - 1; i++) {
			t1[i + 1] = nums[i] * t1[i];
		}

		// scan from right to left
		for (int i = nums.length - 1; i > 0; i--) {
			t2[i - 1] = t2[i] * nums[i];
		}

		// multiply
		for (int i = 0; i < nums.length; i++) {
			result[i] = t1[i] * t2[i];
		}

		return result;
	}
}
