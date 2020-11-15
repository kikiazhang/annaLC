package tags.linkedlist;

import tags.ListNode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
 * L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You may not modify the values in the list's nodes, only nodes itself may be
 * changed.
 * 
 * Example 1:
 * 
 * Given 1->2->3->4, reorder it to 1->4->2->3. Example 2:
 * 
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList143 {
	// o(n)
	public void reorderList(ListNode head) {
		if (head == null)
			return;
		// find mid and break to two(make sure first len > second len)
		int len = 0;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		while (head != null) {
			len++;
			head = head.next;
		}
		int mid = len / 2;
		head = dummy.next;
		while (mid > 0) {
			head = head.next;
			mid--;
		}
		ListNode newHead = head.next;
		head.next = null;
		head = dummy.next;
		// reverse second half
		newHead = reverse(newHead);
		// merge to one
		while (head != null && newHead != null) {
			ListNode next = head.next;
			ListNode newNext = newHead.next;
			head.next = newHead;
			newHead.next = next;
			head = next;
			newHead = newNext;
		}
	}

	private ListNode reverse(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	// no need to count len, just need fast&slow to find mid node
	private ListNode findMiddle(ListNode node) {
		ListNode fast = node.next;
		ListNode slow = node;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
}
