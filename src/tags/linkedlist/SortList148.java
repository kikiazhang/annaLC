package tags.linkedlist;

import tags.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * Example 1:
 * 
 * Input: 4->2->1->3 Output: 1->2->3->4 Example 2:
 * 
 * Input: -1->5->3->4->0 Output: -1->0->3->4->5
 */
public class SortList148 {
	// merge sort
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)// 只有一个数，sort完成
			return head;

		ListNode fast = head;
		ListNode slow = head;
		ListNode pre = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			pre = slow;
			slow = slow.next;
		}
		pre.next = null;// 断开两段
		ListNode h1 = sortList(head);
		ListNode h2 = sortList(slow);
		return merge(h1, h2);
	}

	private ListNode merge(ListNode h1, ListNode h2) {
		ListNode dummy = new ListNode(-1);
		ListNode pre = dummy;
		while (h1 != null && h2 != null) {
			if (h1.val < h2.val) {
				pre.next = new ListNode(h1.val);
				h1 = h1.next;
			} else {
				pre.next = new ListNode(h2.val);
				h2 = h2.next;
			}
			pre = pre.next;
		}
		if (h1 != null)
			pre.next = h1;
		if (h2 != null)
			pre.next = h2;
		return dummy.next;
	}
}
