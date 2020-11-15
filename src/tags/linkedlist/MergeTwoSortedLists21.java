package tags.linkedlist;

import tags.ListNode;

/**
 * Merge two sorted linked lists and return it as a new sorted list. The new
 * list should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * 
 * Input: 1->2->4, 1->3->4 Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists21 {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				res.next = new ListNode(l1.val);
				l1 = l1.next;
			} else {
				res.next = new ListNode(l2.val);
				l2 = l2.next;
			}
			res = res.next;
		}
		if (l1 != null) {
			res.next = l1;
		}
		if (l2 != null) {
			res.next = l2;
		}
		return dummy.next;
	}
}
