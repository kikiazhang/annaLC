package tags.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1. put(key, value) - Set or insert the
 * value if the key is not already present. When the cache reaches its capacity,
 * it should invalidate the least frequently used item before inserting a new
 * item. For the purpose of this problem, when there is a tie (i.e., two or more
 * keys that have the same frequency), the least recently used key would be
 * evicted.
 * 
 * Note that the number of times an item is used is the number of calls to the
 * get and put functions for that item since it was inserted. This number is set
 * to zero when the item is removed.
 */
public class LFUCache460 {
	int cap, size, min;// current min count
	Map<Integer, Node> cache;// key, node
	Map<Integer, DLList> count;// count, double linkedList

	public LFUCache460(int capacity) {
		cap = capacity;
		cache = new HashMap<>();
		count = new HashMap<>();
	}

	public int get(int key) {
		Node node = cache.get(key);
		if (node != null) {
			update(node);
			return node.value;
		} else {
			return -1;
		}
	}

	// 更新node count，需要将它从old freq list转移到new freq list
	private void update(Node node) {
		// old freq list
		DLList oldList = count.get(node.ctn);
		oldList.remove(node);
		// 最小一个freq拿完了，更新min
		if (min == node.ctn && oldList.size == 0) {
			min++;
		}
		// 保存进count+1的list
		node.ctn++;
		DLList newList = count.getOrDefault(node.ctn, new DLList());
		newList.add(node);// addToHead
		// if newList is new
		count.put(node.ctn, newList);
	}

	public void put(int key, int value) {
		if (cap == 0)
			return;
		Node node;
		// 找到了，拿出来，更新node freq，加入新list
		if (cache.containsKey(key)) {
			node = cache.get(key);
			node.value = value;
			update(node);
		} else {
			// 没找到，新建node，放入cache，还要更新min回到1
			node = new Node(key, value);
			cache.put(key, node);
			// 溢出了，需要先去掉此时的min freq list中的tail，再加入新node
			if (size == cap) {
				DLList lastList = count.get(min);
				Node tail = lastList.removeTail();
				cache.remove(tail.key);
				size--;
			}
			size++;
			min = 1;
			DLList newList = count.getOrDefault(node.ctn, new DLList());
			newList.add(node);
			// if newList is new
			count.put(node.ctn, newList);
		}
	}
}

class Node {
	int key, value, ctn;
	Node prev, next;

	public Node(int key, int val) {
		this.key = key;
		this.value = val;
		this.ctn = 1;
	}
}

//相当于queue
class DLList {
	Node head, tail;
	int size;

	public DLList() {
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	// addToHead
	public void add(Node node) {
		head.next.prev = node;
		node.next = head.next;
		node.prev = head;
		head.next = node;
		size++;
	}

	public void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
	}

	public Node removeTail() {
		if (size > 0) {
			Node node = tail.prev;
			remove(node);
			return node;
		} else {
			return null;
		}
	}
}
