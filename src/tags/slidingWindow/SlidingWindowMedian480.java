package tags.slidingWindow;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * 
 * Examples: [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Given an array nums, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k numbers
 * in the window. Each time the sliding window moves right by one position. Your
 * job is to output the median array for each window in the original array.
 * 
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * For example,
	Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
	
	Window position                Median
	---------------               -----
	[1  3  -1] -3  5  3  6  7       1
	 1 [3  -1  -3] 5  3  6  7       -1
	 1  3 [-1  -3  5] 3  6  7       -1
	 1  3  -1 [-3  5  3] 6  7       3
	 1  3  -1  -3 [5  3  6] 7       5
	 1  3  -1  -3  5 [3  6  7]      6
	Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 */
public class SlidingWindowMedian480 {
	public double[] medianSlidingWindow(int[] nums, int k) {
		double[] result = new double[nums.length - k + 1];
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());// 放小的
		PriorityQueue<Integer> right = new PriorityQueue<>();// 放大的

		for (int i = 0; i < nums.length; i++) {
			if (left.size() <= right.size()) {// 让left个数多
				right.add(nums[i]);// 先放进right排序
				left.add(right.remove());// 把right最小的放入left
			} else {
				left.add(nums[i]);
				right.add(left.remove());
			}

			if (left.size() + right.size() == k) {// 有k个了，需要求median
				double median;
				if (left.size() == right.size()) {// 个数相同，都取/2
					median = (double) ((long) left.peek() + (long) right.peek()) / 2;
				} else {
					median = (double) left.peek();
				}
				// （res的index，同时也是nums的index）放入median
				int start = i - k + 1;
				result[start] = median;
				// 在heap中去掉nums的range最左的值
				if (!left.remove(nums[start])) {
					right.remove(nums[start]);
				}
			}
		}
		return result;
	}
}
