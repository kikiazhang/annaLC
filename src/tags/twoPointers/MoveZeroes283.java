package tags.twoPointers;

/**
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * Example:
 * 
 * Input: [0,1,0,3,12] Output: [1,3,12,0,0]
 */
public class MoveZeroes283 {
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length <= 1)
			return;

		int m = -1;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {// 如果这一位是0，正常情况下是不动，如果这是第一次出现0，那么记录下来0的位置，
				if (m == -1) {
					m = i;
				}
			} else {
				if (m != -1) {// 如果还没有出现0，继续，不用交换，如果出现了，就要一直错位
					int temp = nums[i];
					nums[i] = nums[m];
					nums[m] = temp;
					m++;// 把0交换，m++为下一位交换的地方（要不就是连续的0，要不就是i）
				}
			}
		}
	}

	public void moveZeroes2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int idx = 0;
		for (int i = 0, max = nums.length; i < max; i++) {
			if (nums[i] != 0) {// 如果0，只动i
				nums[idx++] = nums[i];
			}
		}
		// 加0
		for (int i = idx, max = nums.length; i < max; i++) {
			nums[i] = 0;
		}
	}

	// 自己写的 不最优
	public void moveZeroes3(int[] nums) {
		if (nums == null || nums.length <= 1)
			return;

		int i = 0;
		while (i < nums.length) {
			if (nums[i] == 0) {
				int j = i + 1;
				while (j < nums.length && nums[j] == 0) {
					j++;
				}
				if (j < nums.length) {
					nums[i] = nums[j];
					nums[j] = 0;
				}
			}
			i++;
		}
	}
}
