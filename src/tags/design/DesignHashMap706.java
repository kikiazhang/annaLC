package tags.design;

public class DesignHashMap706 {
	// hashMap实现是靠array + linkedList
	// 通过hashcode找bucket，再通过equals找key做替换和插入
	final ListNode[] nodes = new ListNode[10000];// 默认长度10000

	public void put(int key, int value) {
		int i = idx(key);// bucket index in array
		if (nodes[i] == null)// 如果为空，就初始
			nodes[i] = new ListNode(-1, -1);
		ListNode prev = find(nodes[i], key);// 找到应该插入的点（结束||找到相同key）
		if (prev.next == null)
			prev.next = new ListNode(key, value);
		else
			prev.next.val = value;
	}

	public int get(int key) {
		int i = idx(key);
		if (nodes[i] == null)
			return -1;
		ListNode node = find(nodes[i], key);
		return node.next == null ? -1 : node.next.val;
	}

	public void remove(int key) {
		int i = idx(key);
		if (nodes[i] == null)
			return;
		ListNode prev = find(nodes[i], key);
		if (prev.next == null)
			return;
		prev.next = prev.next.next;
	}

	// 把key通过hashcode放到array里
	private int idx(int key) {
		return Integer.hashCode(key) % nodes.length;
	}

	// 找到该插入的点的prev
	ListNode find(ListNode bucket, int key) {
		ListNode node = bucket, prev = null;
		while (node != null && node.key != key) {
			prev = node;
			node = node.next;
		}
		return prev;
	}

	class ListNode {
		int key, val;
		ListNode next;

		ListNode(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
}
