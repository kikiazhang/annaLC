package facebook;

public class ReverseLinkedListIII {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	/**
	 * Reverse a linked list from position m to n. Do it in one-pass.
	 * 
	 * Note: 1 ≤ m ≤ n ≤ length of list.
	 * 
	 * Example:
	 * 
	 * Input: 1->2->3->4->5->NULL, m = 2, n = 4 Output: 1->4->3->2->5->NULL
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null)
			return head;

		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		head = dummy;

		ListNode st = head;
		ListNode n1 = null;
		ListNode n2 = null;

		for (int i = 0; i < n; i++) {
			if (i < m - 1) {
				st = st.next;
			} else if (i == m - 1) {
				n1 = st.next;
				n2 = n1.next;
			} else {
				n1.next = n2.next;
				n2.next = st.next;
				st.next = n2;
				n2 = n1.next;
			}
		}
		return dummy.next;
	}
}
