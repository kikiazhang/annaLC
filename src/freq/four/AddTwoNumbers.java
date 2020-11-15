package freq.four;

import freq.five.ListNode;

public class AddTwoNumbers {

	/**
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8 Explanation: 342 +
	 * 465 = 807.
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		int carry = 0;

		while (l1 != null || l2 != null || carry != 0) {
			int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
			carry = sum / 10;
			int num = sum % 10;
			l1 = l1 == null ? l1 : l1.next;
			l2 = l2 == null ? l2 : l2.next;
			head.next = new ListNode(num);
			head = head.next;
		}
		return dummy.next;
	}
}
