package tags.linkedlist;

import tags.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Example 1:
 * 
 * Input: 1->2 Output: false Example 2:
 * 
 * Input: 1->2->2->1 Output: true
 */
public class PalindromeLinkedList234 {
	// o(n)
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null)
			return true;
		// find mid
		ListNode fast = head.next;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		// reverse half and compare
		ListNode newHead = reverse(slow.next);
		while (newHead != null) {
			if (head.val != newHead.val) {
				return false;
			} else {
				head = head.next;
				newHead = newHead.next;
			}
		}
		return true;
	}

	private ListNode reverse(ListNode head) {
		ListNode pre = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	// recursive
	private ListNode frontPointer;

	private boolean recursivelyCheck(ListNode currentNode) {
		if (currentNode != null) {
			if (!recursivelyCheck(currentNode.next))
				return false;
			if (currentNode.val != frontPointer.val)
				return false;
			frontPointer = frontPointer.next;
		}
		return true;
	}

	public boolean isPalindrome2(ListNode head) {
		frontPointer = head;
		return recursivelyCheck(head);
	}
}
