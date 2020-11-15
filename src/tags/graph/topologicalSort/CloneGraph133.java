package tags.graph.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph133 {
	public Node cloneGraph(Node node) {
		// bfs
		if (node == null)
			return null;
		Map<Node, Node> visited = new HashMap<>();// old node, clone node
		Queue<Node> q = new LinkedList<>();
		q.offer(node);
		visited.put(node, new Node(node.val, new ArrayList<>()));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (Node next : n.neighbors) {
				if (!visited.containsKey(next)) {
					visited.put(next, new Node(next.val, new ArrayList<>()));
					q.offer(next);
				}
				visited.get(n).neighbors.add(visited.get(next));// connect clone node with clone next node
			}
		}
		return visited.get(node);
	}
}

class Node {
	public int val;
	public List<Node> neighbors;

	public Node() {
		val = 0;
		neighbors = new ArrayList<Node>();
	}

	public Node(int _val) {
		val = _val;
		neighbors = new ArrayList<Node>();
	}

	public Node(int _val, ArrayList<Node> _neighbors) {
		val = _val;
		neighbors = _neighbors;
	}
}
