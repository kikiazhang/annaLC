package amazonOA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CriticalConnections {
	/**
	 * https://leetcode.com/problems/critical-connections-in-a-network/
	 * 
	 * Given an underected connected graph with n nodes labeled 1..n. A bridge (cut
	 * edge) is defined as an edge which, when removed, makes the graph disconnected
	 * (or more precisely, increases the number of connected components in the
	 * graph). Equivalently, an edge is a bridge if and only if it is not contained
	 * in any cycle. The task is to find all bridges in the given graph. Output an
	 * empty list if there are no bridges.
	 * 
	 * Input:
	 * 
	 * n, an int representing the total number of nodes. 
	 * edges, a list of pairs of integers representing the nodes connected by an edge. 
	 * 
	 * Example 1:
	 * 
	 * Input: n = 5, edges = [[1, 2], [1, 3], [3, 4], [1, 4], [4, 5]] Output: [[1,
	 * 2], [4, 5]] Explanation: There are 2 bridges: 1. Between node 1 and 2 2.
	 * Between node 4 and 5 If we remove these edges, then the graph will be
	 * disconnected. If we remove any of the remaining edges, the graph will remain
	 * connected.
	 */
	
	//1-[2,3,4]
	//2-[1]
	//3-[1,4]
	//4-[1,3,5]
	//5-[4]
	class PairInt{
		int first;
		int second;
		public PairInt(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
		
	}
	List<PairInt> list;
	Map<Integer, Boolean> visited;

	List<PairInt> criticalConnections(int numOfServers, int numOfConnections, List<PairInt> connections) {
		Map<Integer, HashSet<Integer>> adj = new HashMap<>();
		for (PairInt connection : connections) {
			int u = connection.first;
			int v = connection.second;
			if (adj.get(u) == null) {
				adj.put(u, new HashSet<Integer>());
			}
			adj.get(u).add(v);
			if (adj.get(v) == null) {
				adj.put(v, new HashSet<Integer>());
			}
			adj.get(v).add(u);
		}

		list = new ArrayList<>();
		for (int i = 0; i < numOfConnections; i++) {
			visited = new HashMap<>();
			PairInt p = connections.get(i);
			int x = p.first;
			int y = p.second;
			adj.get(x).remove(y);
			adj.get(y).remove(x);
			DFS(adj, 1);
			if (visited.size() != numOfServers) {
				if (p.first > p.second)
					list.add(new PairInt(p.second, p.first));
				else
					list.add(p);
			}
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		return list;
	}

	public void DFS(Map<Integer, HashSet<Integer>> adj, int u) {
		visited.put(u, true);
		if (adj.get(u).size() != 0) {
			for (int v : adj.get(u)) {
				if (visited.getOrDefault(v, false) == false) {
					DFS(adj, v);
				}
			}
		}
	}
}
