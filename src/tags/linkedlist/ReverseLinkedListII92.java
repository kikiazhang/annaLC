package tags.linkedlist;

import tags.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * 
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * 
 * Example:
 * 
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4 Output: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListII92 {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null)
			return head;

		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		int count = 1;
		while (count < m) {
			count++;
			pre = pre.next;
			head = head.next;
		}
		// pre = 1, head = 2, count = 2
		pre.next = null;
		head = reverse(head, count, n);
		pre.next = head;// 重新连接

		return dummy.next;
	}

	private ListNode reverse(ListNode head, int count, int n) {
		ListNode pre = null;
		ListNode tail = head;
		ListNode next = null;
		while (count <= n) {// 这个reverse最大的问题就是tail是断开的，最后为null
			count++;
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		tail.next = next;// 所以需要reverse部分连接没有reverse部分
		return pre;
	}
}
