package tags.divideConquer;

import tags.DoubleLinkedListNode;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in
 * place.
 * 
 * You can think of the left and right pointers as synonymous to the predecessor
 * and successor pointers in a doubly-linked list. For a circular doubly linked
 * list, the predecessor of the first element is the last element, and the
 * successor of the last element is the first element.
 * 
 * We want to do the transformation in place. After the transformation, the left
 * pointer of the tree node should point to its predecessor, and the right
 * pointer should point to its successor. You should return the pointer to the
 * smallest element of the linked list. Input: root = [4,2,5,1,3] Output:
 * [1,2,3,4,5] Example 2:
 * 
 * Input: root = [2,1,3] Output: [1,2,3] Example 3:
 * 
 * Input: root = [] Output: [] Explanation: Input is an empty tree. Output is
 * also an empty Linked List. Example 4:
 * 
 * Input: root = [1] Output: [1]
 */
public class ConvertBinarySearchTreetoSortedDoublyLinkedList426 {
	DoubleLinkedListNode last = null;
	DoubleLinkedListNode first = null;

	// o(n)
	public DoubleLinkedListNode treeToDoublyList(DoubleLinkedListNode root) {
		if (root == null)
			return root;

		helper(root);
		last.right = first;
		first.left = last;
		return first;
	}

	private void helper(DoubleLinkedListNode node) {
		if (node != null) {
			// first, adding the left deep nodes
			helper(node.left);

			if (last != null) {
				// there is some left nodes saved, so next should be cur node
				last.right = node;
				node.left = last;
			} else {
				// there is no left node, cur node is the most left one
				first = node;
			}
			// cur node always be the current most right node, so should be last
			last = node;
			// next sorted right half
			helper(node.right);
		}
	}
}
