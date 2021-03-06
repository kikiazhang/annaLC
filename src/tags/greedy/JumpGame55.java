package tags.greedy;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0
 * to 1, then 3 steps to the last index. Example 2:
 * 
 * Input: nums = [3,2,1,0,4] Output: false Explanation: You will always arrive
 * at index 3 no matter what. Its maximum jump length is 0, which makes it
 * impossible to reach the last index.
 */
public class JumpGame55 {
	public boolean canJump(int[] nums) {
		if (nums == null || nums.length <= 1)
			return true;

		int max = 0;
		int i = 0;
		while (i < nums.length && i <= max) {
			max = Math.max(nums[i] + i, max);
			i++;
		}
		return max >= nums.length - 1;
	}

	public boolean canJump2(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
		}
		return lastPos == 0;
	}
}
