package tags.heap;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * Example 1:
 * 
 * Input: [3,2,1,5,6,4] and k = 2 Output: 5 Example 2:
 * 
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4 Output: 4
 */
public class KthLargestElementinArray215 {
	// quick sort
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return -1;

		int l = 0, r = nums.length - 1;
		while (true) {
			int mid = helper(nums, l, r);
			if (mid + 1 == k) {
				return nums[mid];
			} else if (mid + 1 < k) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
	}

	private int helper(int[] nums, int l, int r) {
		int pivot = nums[l];
		int left = l + 1, right = r;
		while (left <= right) {
			if (nums[left] < pivot && nums[right] > pivot) {
				swap(nums, left, right);
			}
			if (nums[left] >= pivot)
				left++;
			if (nums[right] <= pivot)
				right--;
		}
		swap(nums, l, right);
		return right;
	}

	private void swap(int[] nums, int l, int r) {
		int tmp = nums[l];
		nums[l] = nums[r];
		nums[r] = tmp;
	}

	// priorityQueue o(nlogk)
	public int findKthLargest2(int[] nums, int k) {
		// init heap 'the smallest element first'
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

		// keep k largest elements in the heap
		for (int n : nums) {
			heap.add(n);
			if (heap.size() > k)
				heap.poll();
		}

		// output
		return heap.poll();
	}
}
