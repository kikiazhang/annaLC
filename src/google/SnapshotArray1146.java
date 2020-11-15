package google;

import java.util.TreeMap;

/**
 * Implement a SnapshotArray that supports the following interface:
 * 
 * SnapshotArray(int length) initializes an array-like data structure with the
 * given length. Initially, each element equals 0. void set(index, val) sets the
 * element at the given index to be equal to val. int snap() takes a snapshot of
 * the array and returns the snap_id: the total number of times we called snap()
 * minus 1. int get(index, snap_id) returns the value at the given index, at the
 * time we took the snapshot with the given snap_id
 */
public class SnapshotArray1146 {
	/**
	 * Time O(logS) Space O(S) where S is the number of set called.
	 * 
	 * SnapshotArray(int length) is O(N) time set(int index, int val) is O(1) in
	 * Python and O(logSnap) in Java snap() is O(1) get(int index, int snap_id) is
	 * O(logSnap)
	 */
	// binary search
	TreeMap<Integer, Integer>[] A;
	int snap_id = 0;

	public SnapshotArray1146(int length) {
		A = new TreeMap[length];
		for (int i = 0; i < length; i++) {
			A[i] = new TreeMap<Integer, Integer>();
			A[i].put(0, 0);
		}
	}

	public void set(int index, int val) {
		A[index].put(snap_id, val);
	}

	public int snap() {
		return snap_id++;
	}

	public int get(int index, int snap_id) {
		// the greatest key less than or equal to the given key
		return A[index].floorEntry(snap_id).getValue();
	}
}
