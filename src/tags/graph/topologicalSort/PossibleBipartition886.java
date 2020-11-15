package tags.graph.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split
 * everyone into two groups of any size.
 * 
 * Each person may dislike some other people, and they should not go into the
 * same group.
 * 
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the
 * people numbered a and b into the same group.
 * 
 * Return true if and only if it is possible to split everyone into two groups
 * in this way.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]] Output: true Explanation: group1
 * [1,4], group2 [2,3] Example 2:
 * 
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]] Output: false Example 3:
 * 
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]] Output: false
 */
public class PossibleBipartition886 {
	public boolean possibleBipartition(int N, int[][] dislikes) {
		Map<Integer, Integer> map = new HashMap<>();// index, 0|1 value非黑即白
		List<Integer>[] graph = new ArrayList[N + 1];// index，他的dislikes
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] dis : dislikes) {
			graph[dis[0]].add(dis[1]);
			graph[dis[1]].add(dis[0]);
		}
		for (int i = 1; i <= N; i++) {
			if (!map.containsKey(i) && !dfs(i, 0, map, graph)) {// 试着放i到0|1
				return false;
			}
		}
		return true;
	}

	private boolean dfs(int i, int c, Map<Integer, Integer> map, List<Integer>[] graph) {
		if (map.containsKey(i)) {// 看是否与之前一致
			return map.get(i) == c;
		}
		map.put(i, c);
		for (int dis : graph[i]) {// 他的dislikes应该也都能顺利放入对面
			if (!dfs(dis, c ^ 1, map, graph)) {
				return false;
			}
		}
		return true;
	}
}
