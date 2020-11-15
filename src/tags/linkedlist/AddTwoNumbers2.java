package tags.linkedlist;

import tags.ListNode;

/**
 * Example:
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8 Explanation: 342 +
 * 465 = 807.
 */
public class AddTwoNumbers2 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		int ca = 0;
		ListNode dummy = new ListNode(-1);
		ListNode res = dummy;
		while (l1 != null || l2 != null || ca != 0) {
			int cur = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + ca;
			int dig = cur % 10;
			ca = cur / 10;
			res.next = new ListNode(dig);
			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
			res = res.next;
		}
		return dummy.next;
	}
}
