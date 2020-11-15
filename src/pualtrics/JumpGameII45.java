package pualtrics;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * Example:
 * 
 * Input: [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps to
 * reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the
 * last index. Note:
 * 
 * You can assume that you can always reach the last index.
 */
public class JumpGameII45 {
	public int jump(int[] nums) {
		if (nums.length < 2)
			return 0;

		int maxPos = nums[0];// cur max position can be reached，每一步更新最大
		int maxSteps = nums[0];// 跳到这一步【index 0】，他最多能到的位置【2】
		int jump = 1;// 最少一步
		for (int i = 1; i < nums.length; i++) {
			if (maxSteps < i) {// need one more jump，那接下来一步能到的最大就是maxPos
				jump++;
				maxSteps = maxPos;
			}
			maxPos = Math.max(maxPos, nums[i] + i);
		}
		return jump;
	}

	// jump game I (can reach the last index)
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
}
