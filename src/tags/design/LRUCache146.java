package tags.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache146 {
	// HashMap + DoubleLinkedList o(1)
	Map<Integer, DoubleLinkedListNode> map;
	DoubleLinkedListNode head, tail;
	int cap;
	int size;

	public LRUCache146(int capacity) {
		map = new HashMap<>();
		cap = capacity;
		size = 0;
		head = new DoubleLinkedListNode();
		tail = new DoubleLinkedListNode();
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		DoubleLinkedListNode node = map.get(key);
		if (node == null) {
			return -1;
		}
		moveToHead(node);
		return node.value;
	}

	public void put(int key, int value) {
		DoubleLinkedListNode node = map.get(key);
		if (node == null) {
			node = new DoubleLinkedListNode();
			node.key = key;
			node.value = value;

			map.put(key, node);
			addNode(node);
			size++;
			if (size > cap) {
				DoubleLinkedListNode tail = popTail();
				map.remove(tail.key);
				size--;
			}
		} else {
			node.value = value;
			moveToHead(node);
		}
	}

	private void moveToHead(DoubleLinkedListNode node) {
		removeNode(node);
		addNode(node);
	}

	private void addNode(DoubleLinkedListNode node) {
		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(DoubleLinkedListNode node) {
		DoubleLinkedListNode prev = node.prev;
		DoubleLinkedListNode next = node.next;

		prev.next = next;
		next.prev = prev;
	}

	private DoubleLinkedListNode popTail() {
		DoubleLinkedListNode res = tail.prev;
		removeNode(res);
		return res;
	}

	class DoubleLinkedListNode {
		int key;
		int value;
		DoubleLinkedListNode prev;
		DoubleLinkedListNode next;
	}
}
