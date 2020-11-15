package tags.linkedlist;

import tags.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example:
 * 
 * Input: 1->2->6->3->4->5->6, val = 6 Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements203 {
	public ListNode removeElements(ListNode head, int val) {
		if (head == null)
			return head;

		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		while (head != null) {
			if (head.val == val) {
				pre.next = head.next;
				head = head.next;
			} else {
				pre = pre.next;
				head = head.next;
			}
		}
		return dummy.next;
	}
}
