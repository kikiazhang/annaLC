package freq.four;

import java.util.Comparator;
import java.util.PriorityQueue;

import freq.five.ListNode;

public class MergeKSortedLists {

	public class FixedArrayQueue<T> {
		private Node<T> head, tail;
		private int front, rear, size;
		private final int SIZE;

		public FixedArrayQueue(int n) {
			SIZE = n;
			head = tail = new Node<T>(SIZE);
			front = rear = size = 0;
		}

		public void enqueue(T t) {
			tail.array[rear++] = t;
			if (rear == SIZE) {
				rear = 0;
				append();
			}
			size++;
		}

		public T dequeue() {
			if (size == 0) {
				return null;
			}
			T ret = head.array[front++];
			if (front == SIZE) {
				front = 0;
				remove();
			}

			size--;
			return ret;
		}

		private void append() {
			tail.next = new Node<T>(SIZE);
			tail = tail.next;
		}

		private void remove() {
			head = head.next;
		}

		private boolean isEmpty() {
			return size == 0;
		}

		private int size() {
			return size;
		}
	}

	class Node<T> {
		T[] array;
		Node<T> next;

		public Node(int n) {
			array = (T[]) new Object[n];
		}
	}

	/**
	 * Input: [ 1->4->5, 1->3->4, 2->6 ] Output: 1->1->2->3->4->4->5->6
	 */
	// helper merge two lists
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		ListNode head = lists[0];
		for (int i = 1; i < lists.length; i++) {
			head = mergeSort(head, lists[i]);
		}
		return head;
	}

	public ListNode mergeSort(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				head.next = l1;
				l1 = l1.next;
			} else {
				head.next = l2;
				l2 = l2.next;
			}
			head = head.next;
		}
		if (l1 != null)
			head.next = l1;
		if (l2 != null)
			head.next = l2;
		return dummy.next;
	}

	// PriorityQueue
	// o(nlogk) space:o(n)
	public ListNode mergeKLists2(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode l1, ListNode l2) {
				return l1.val - l2.val;
			}
		});
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		for (ListNode node : lists) {
			if (node != null) {
				q.offer(node);
			}
		}
		while (!q.isEmpty()) {
			tail.next = q.poll();
			tail = tail.next;
			if (tail.next != null) {
				q.offer(tail.next);
			}
		}
		return dummy.next;
	}

	// merge sort algorithm time: O(nlogk)
	public ListNode mergeKLists3(ListNode[] lists) {
		return partion(lists, 0, lists.length - 1);
	}

	// separate to two list, then merge this two
	public ListNode partion(ListNode[] lists, int left, int right) {
		if (left == right)
			return lists[left];

		if (left < right) {
			int mid = left + (right - left) / 2;
			ListNode l1 = partion(lists, left, mid);
			ListNode l2 = partion(lists, mid + 1, right);
			return merge(l1, l2);
		}
		return null;
	}

	// recursion for merging
	public ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = merge(l1.next, l2);
			return l1;
		} else {
			l2.next = merge(l1, l2.next);
			return l2;
		}
	}
}
