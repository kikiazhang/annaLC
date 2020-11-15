package tags.array;

/**
 * Given an array nums of n integers where n > 1, return an array output such
 * that output[i] is equal to the product of all the elements of nums except
 * nums[i].
 * 
 * Example:
 * 
 * Input: [1,2,3,4] Output: [24,12,8,6] Constraint: It's guaranteed that the
 * product of the elements of any prefix or suffix of the array (including the
 * whole array) fits in a 32 bit integer.
 * 
 * Note: Please solve it without division and in O(n).
 */
public class ProductofArrayExceptSelf238 {

	public int[] productExceptSelf(int[] nums) {
		int[] res = new int[nums.length];
		res[0] = 1;
		for (int i = 1; i < nums.length; i++) {//左边的product，不包括自己
			res[i] = res[i - 1] * nums[i - 1];
		}
		int right = 1;//右边的product，不包括自己
		for (int i = nums.length - 1; i >= 0; i--) {
			res[i] = res[i] * right;
			right = nums[i] * right;
		}
		return res;
	}

	public int[] productExceptSelf2(int[] nums) {
		int pro = 1;
		for (int n : nums) {
			pro = pro * n;
		}
		int[] res = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			res[i] = pro / nums[i];
		}
		return res;
	}
}
