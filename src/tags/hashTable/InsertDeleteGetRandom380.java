package tags.hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Design a data structure that supports all following operations in average
 * O(1) time.
 * 
 * 
 * 
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present. getRandom: Returns
 * a random element from current set of elements (it's guaranteed that at least
 * one element exists when this method is called). Each element must have the
 * same probability of being returned.
 */
public class InsertDeleteGetRandom380 {
	Map<Integer, Integer> map; // val, list index
	List<Integer> list;
	Random r = new Random();

	/** Initialize your data structure here. */
	public InsertDeleteGetRandom380() {
		map = new HashMap<>();
		list = new ArrayList<>();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain
	 * the specified element.
	 */
	public boolean insert(int val) {
		if (!map.containsKey(val)) {
			map.put(val, list.size());
			list.add(list.size(), val);
			return true;
		}
		return false;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified
	 * element.
	 */
	public boolean remove(int val) {
		if (map.containsKey(val)) {
			// move the last val to the place index of the remove val
			int lastVal = list.get(list.size() - 1);
			int index = map.get(val);
			list.set(index, lastVal); // list insert val to index
			map.put(lastVal, index);
			// delete val
			list.remove(list.size() - 1);
			map.remove(val);
			return true;
		}
		return false;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return list.get(r.nextInt(list.size()));
	}
}
