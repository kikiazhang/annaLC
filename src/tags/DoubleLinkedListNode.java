package tags;

public class DoubleLinkedListNode {
	public int val;
	public DoubleLinkedListNode left;
	public DoubleLinkedListNode right;

	public DoubleLinkedListNode() {
	}

	public DoubleLinkedListNode(int _val) {
		val = _val;
	}

	public DoubleLinkedListNode(int _val, DoubleLinkedListNode _left, DoubleLinkedListNode _right) {
		val = _val;
		left = _left;
		right = _right;
	}
}
