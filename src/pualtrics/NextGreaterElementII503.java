package pualtrics;

import java.util.HashMap;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first
 * element of the array), print the Next Greater Number for every element. The
 * Next Greater Number of a number x is the first greater number to its
 * traversing-order next in the array, which means you could search circularly
 * to find its next greater number. If it doesn't exist, output -1 for this
 * number.
 * 
 * Example 1: Input: [1,2,1] Output: [2,-1,2] Explanation: The first 1's next
 * greater number is 2; The number 2 can't find next greater number; The second
 * 1's next greater number needs to search circularly, which is also 2.
 */
public class NextGreaterElementII503 {
	// o(n)
	public int[] nextGreaterElements(int[] nums) {
		int[] res = new int[nums.length];
		Stack<Integer> stack = new Stack<>();
		// 连接两次，实现circular
		for (int i = 2 * nums.length - 1; i >= 0; --i) {
			// 使cur num为stack peek最大（排除自己和小的）
			while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
				stack.pop();
			}
			res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];// 先不管后面的，因为会再遍历一次
			stack.push(i % nums.length);// 5 4 3 。。。
		}
		return res;
	}

	// o(m + n) NextGreaterElementI
	// Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
	// Output: [-1,3,-1]
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Stack<Integer> stack = new Stack<>();// save cur peak
		HashMap<Integer, Integer> map = new HashMap<>();// num, next bigger
		int[] res = new int[findNums.length];
		for (int i = 0; i < nums.length; i++) {
			while (!stack.empty() && nums[i] > stack.peek())
				map.put(stack.pop(), nums[i]);// cur num is prev num's next bigger
			stack.push(nums[i]);// save cur bigger(5 3 2 ...)
		}
		// which don't have the next bigger
		while (!stack.empty())
			map.put(stack.pop(), -1);
		for (int i = 0; i < findNums.length; i++) {
			res[i] = map.get(findNums[i]);
		}
		return res;
	}
}
