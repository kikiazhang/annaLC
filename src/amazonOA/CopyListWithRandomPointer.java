package amazonOA;

public class CopyListWithRandomPointer {
	/**
	 * A linked list is given such that each node contains an additional random
	 * pointer which could point to any node in the list or null.
	 * 
	 * Return a deep copy of the list.
	 * 
	 * The Linked List is represented in the input/output as a list of n nodes. Each
	 * node is represented as a pair of [val, random_index] where:
	 * 
	 * val: an integer representing Node.val random_index: the index of the node
	 * (range from 0 to n-1) where random pointer points to, or null if it does not
	 * point to any node.
	 * 
	 * 
	 * Example 1:
	 * 
	 * 
	 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]] Output:
	 * [[7,null],[13,0],[11,4],[10,2],[1,0]]
	 */
	class Node {
		public int val;
		public Node next;
		public Node random;

		public Node() {
		}

		public Node(int _val, Node _next, Node _random) {
			val = _val;
			next = _next;
			random = _random;
		}
	}

	/*
	 * 第一遍扫的时候巧妙运用next指针， 开始数组是1->2->3->4 。 然后扫描过程中 先建立copy节点
	 * 1->1`->2->2`->3->3`->4->4`, 然后第二遍copy的时候去建立边的copy， 拆分节点,
	 * 一边扫描一边拆成两个链表，这里用到两个dummy node。第一个链表变回 1->2->3 , 然后第二变成 1`->2`->3`
	 */
	// No HashMap version
	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}
		copyNext(head);
		copyRandom(head);
		return splitList(head);
	}

	private void copyNext(Node head) {
		while (head != null) {
			Node newNode = new Node(head.val, null, null);
			newNode.random = head.random;
			newNode.next = head.next;
			head.next = newNode;
			head = head.next.next;
		}
	}

	private void copyRandom(Node head) {
		while (head != null) {
			if (head.next.random != null) {
				head.next.random = head.random.next;
			}
			head = head.next.next;
		}
	}

	private Node splitList(Node head) {
		Node newHead = head.next;
		while (head != null) {
			Node temp = head.next;
			head.next = temp.next;
			head = head.next;
			if (temp.next != null) {
				temp.next = temp.next.next;
			}
		}
		return newHead;
	}
}
