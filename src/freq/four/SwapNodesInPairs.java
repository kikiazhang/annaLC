package freq.four;

import freq.five.ListNode;

public class SwapNodesInPairs {

	/**
	 * Example:
	 * 
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 */
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;

		while (head != null && head.next != null) {
			//tail-next-cur
			tail.next = head.next;
			head.next = head.next.next;
			tail.next.next = head;
			//tail->head, head->next
			tail = head;
			head = head.next;
		}
		return dummy.next;
	}
}
