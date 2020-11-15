package tags.graph.topologicalSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Example 1:
 * 
 * Input: org = [1,2,3], seqs = [[1,2],[1,3]] Output: false Explanation: [1,2,3]
 * is not the only one sequence that can be reconstructed, because [1,3,2] is
 * also a valid sequence that can be reconstructed. Example 2:
 * 
 * Input: org = [1,2,3], seqs = [[1,2]] Output: false Explanation: The
 * reconstructed sequence can only be [1,2]. Example 3:
 * 
 * Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]] Output: true Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original
 * sequence [1,2,3]. Example 4:
 * 
 * Input: org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]] Output: true
 */
public class SequenceReconstruction444 {
	public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

		Map<Integer, Set<Integer>> map = new HashMap<>();// int, next int set
		Map<Integer, Integer> indegree = new HashMap<>();
		for (List<Integer> list : seqs) {
			for (int i = 0; i < list.size(); i++) {
				indegree.putIfAbsent(list.get(i), 0);
				map.putIfAbsent(list.get(i), new HashSet<>());
				if (i > 0) {// has prev index
					if (map.get(list.get(i - 1)).add(list.get(i))) {// can add into set, degree++
						indegree.put(list.get(i), indegree.get(list.get(i)) + 1);
					}
				}
			}
		}
		if (org.length != indegree.size())
			return false;
		Queue<Integer> queue = new LinkedList<>();
		for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
			if (entry.getValue() == 0)
				queue.offer(entry.getKey());
		}

		int index = 0;
		while (!queue.isEmpty()) {
			if (queue.size() > 1)// should only have one pass at each time, and match the org order
				return false;
			int cur = queue.poll();
			if (index == org.length || cur != org[index++])
				return false;
			for (int next : map.get(cur)) {
				indegree.put(next, indegree.get(next) - 1);
				if (indegree.get(next) == 0)
					queue.offer(next);
			}
		}

		return org.length == index;
	}
}
