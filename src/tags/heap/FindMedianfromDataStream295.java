package tags.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * 
 * For example, [2,3,4], the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure. double findMedian() - Return the median of all elements so far.
 * 
 * 
 * Example:
 * 
 * addNum(1) addNum(2) findMedian() -> 1.5 addNum(3) findMedian() -> 2
 */
public class FindMedianfromDataStream295 {
	PriorityQueue<Integer> maxHeap;// lower half
	PriorityQueue<Integer> minHeap;// higher half

	/** initialize your data structure here. */
	public FindMedianfromDataStream295() {
		// maxHeap is decreasing(first half), minHeap is increasing(last half)
		maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		minHeap = new PriorityQueue<Integer>();
	}

	public void addNum(int num) {
		maxHeap.offer(num);
		minHeap.offer(maxHeap.poll());

		if (maxHeap.size() < minHeap.size()) {// make sure maxHeap size is larger
			maxHeap.offer(minHeap.poll());
		}

	}

	public double findMedian() {
		if (maxHeap.size() == minHeap.size()) {
			return (double) (maxHeap.peek() + (minHeap.peek())) / 2;
		} else {
			return maxHeap.peek();// have median, peek maxHeep
		}
	}
}
