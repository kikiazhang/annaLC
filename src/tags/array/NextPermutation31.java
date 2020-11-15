package tags.array;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place and use only constant extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2 3,2,1 → 1,2,3 1,1,5 → 1,5,1
 */
public class NextPermutation31 {
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0)
			return;

		int i = nums.length - 2;// 从后往前，比较最后两位
		while (i >= 0 && nums[i + 1] <= nums[i]) {// 高位大，继续
			i--;
		}
		if (i >= 0) {// 没走完，i为小，需要换到后面
			int j = nums.length - 1;
			while (j >= 0 && nums[j] <= nums[i]) {// 当前j大时，可以插入i
				j--;
			}
			swap(nums, i, j);
		}
		// 走完了，需要reverse全部；（4532）没走完，i此时以应换到后面（5432）需要把除了最高位的reverse变成下一个大的（5234）
		reverse(nums, i + 1);
	}

	private void reverse(int[] nums, int st) {
		int i = st, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[j];
		nums[j] = nums[i];
		nums[i] = tmp;
	}
}
