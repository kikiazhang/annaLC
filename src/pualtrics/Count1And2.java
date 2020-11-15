package pualtrics;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 一个数据结构里有0,1,2 . 类似下面这个例子但不一定是矩阵, 因为要求定义的每个node带有up, down, left, right
 * neighbor的信息。input是一个点，如果是非零点，返回连续区间里1和2的个数。(0是用来分隔区域的) [0, 1, 2] [0, 0, 1]
 * [0, 1, 2]
 */
public class Count1And2 {

	public int[] count1And2(Node root) {
		if (root.val == 0)
			return new int[0];
		int[] res = new int[2];
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node cur = q.poll();
				if (cur.val == 1)
					res[0]++;
				if (cur.val == 2)
					res[1]++;
				if (cur.left != null && cur.left.val != 0)
					q.offer(cur.left);
				if (cur.right != null && cur.right.val != 0)
					q.offer(cur.right);
				if (cur.up != null && cur.up.val != 0)
					q.offer(cur.up);
				if (cur.down != null && cur.down.val != 0)
					q.offer(cur.down);
			}
		}
		return res;
	}
}

class Node {
	Node up, down, left, right;
	int val;
}
