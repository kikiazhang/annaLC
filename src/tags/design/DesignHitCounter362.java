package tags.design;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Design a hit counter which counts the number of hits received in the past 5
 * minutes.
 * 
 * Each function accepts a timestamp parameter (in seconds granularity) and you
 * may assume that calls are being made to the system in chronological order
 * (ie, the timestamp is monotonically increasing). You may assume that the
 * earliest timestamp starts at 1.
 * 
 * It is possible that several hits arrive roughly at the same time.
 */
public class DesignHitCounter362 {
	Queue<Integer> queue;

	/** Initialize your data structure here. */
	public DesignHitCounter362() {
		queue = new LinkedList<>();
	}

	/**
	 * Record a hit.
	 * 
	 * @param timestamp - The current timestamp (in seconds granularity).
	 */
	public void hit(int timestamp) {
		queue.offer(timestamp);
	}

	/**
	 * Return the number of hits in the past 5 minutes.
	 * 
	 * @param timestamp - The current timestamp (in seconds granularity).
	 */
	public int getHits(int timestamp) {
		while (!queue.isEmpty() && (timestamp - queue.peek() >= 300)) {
			queue.poll();
		}
		return queue.size();
	}

}

//class HitCounter {
//    TreeMap<Integer, Integer> map; 
//    int lastTS;
//    /** Initialize your data structure here. */
//    public HitCounter() {
//        map = new TreeMap();
//        map.put(0,0);
//        lastTS = 0;
//    }
//
//	/**
//	 * Record a hit.
//	 * 
//	 * @param timestamp - The current timestamp (in seconds granularity).
//	 */
//	public void hit(int timestamp) {
//		map.put(timestamp, map.get(lastTS) + 1);
//		if (timestamp != lastTS) {
//			lastTS = timestamp;
//		}
//	}
//
//	/** Return the number of hits in the past 5 minutes.
//        @param timestamp - The current timestamp (in seconds granularity). */
//    public int getHits(int timestamp) {
//        int old = Math.max(0, timestamp-300); 
//        timestamp = Math.min(timestamp, lastTS); //if timestamp is out of the stored values, then use lastTS
//        return map.get(timestamp)-map.get(map.floorKey(old));
//    }
//}
