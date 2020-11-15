package tags.linkedlist;

import tags.ListNode;

/**
 * Reverse a singly linked list.
 * 
 * Example:
 * 
 * Input: 1->2->3->4->5->NULL Output: 5->4->3->2->1->NULL
 */
public class ReverseLinkedList206 {
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;// 每次连接一个 cur-pre
			pre = cur;// 更新cur pre到下一位
			cur = next;
		}

		return pre;
	}
}
