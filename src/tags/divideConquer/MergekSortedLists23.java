package tags.divideConquer;

import java.util.Comparator;
import java.util.PriorityQueue;

import tags.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * Example:
 * 
 * Input: [ 1->4->5, 1->3->4, 2->6 ] Output: 1->1->2->3->4->4->5->6
 */
public class MergekSortedLists23 {
	// o(nlogk) where k is the number of linked lists. where N is the total number
	// of nodes.
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode a, ListNode b) {
				return a.val - b.val;
			}
		});
		for (ListNode l : lists) {
			if (l != null)
				pq.offer(l);
		}
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (!pq.isEmpty()) {
			ListNode node = pq.poll();
			res.next = new ListNode(node.val);
			res = res.next;
			if (node.next != null)
				pq.offer(node.next);
		}
		return dummy.next;
	}

	// divide and conquer
	public ListNode mergeKLists2(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		while (lists.length > 1) {// merge two at one time
			ListNode[] newList = new ListNode[(lists.length + 1) / 2];
			int index = 0;
			for (int i = 0; i < lists.length - 1; i += 2) {
				newList[index++] = mergeTwoLists(lists[i], lists[i + 1]);
			}
			if (lists.length % 2 == 1) {
				newList[index] = lists[lists.length - 1];
			}
			lists = newList;
		}
		return lists[0];
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// write your code here
		if (l1 == null && l2 == null) {
			return null;
		}
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
		if (l1 != null) {
			head.next = l1;
		}
		if (l2 != null) {
			head.next = l2;
		}
		return dummy.next;
	}
}
