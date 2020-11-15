package google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of
 * all integers in the sliding window.
 * 
 * Example:
 * 
 * MovingAverage m = new MovingAverage(3); m.next(1) = 1 m.next(10) = (1 + 10) /
 * 2 m.next(3) = (1 + 10 + 3) / 3 m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAveragefromDataStream {
	Queue<Integer> queue;
	int cap;
	double sum;
	int currentSize;

	/** Initialize your data structure here. */
	public MovingAveragefromDataStream(int size) {
		cap = size;
		currentSize = 0;
		sum = 0.0;
		queue = new LinkedList<>();
	}

	public double next(int val) {
		if (currentSize < cap) {
			currentSize++;
			sum += val;
			queue.offer(val);
			return sum / currentSize;
		} else {
			int delete = queue.poll();
			sum = sum - delete + val;
			queue.offer(val);
			return sum / currentSize;
		}
	}
}
