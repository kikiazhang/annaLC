package tags.graph.topologicalSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: numCourses = 2, prerequisites = [[1,0]] Output: true Explanation:
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So it is possible. Example 2:
 * 
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]] Output: false
 * Explanation: There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0, and to take course 0 you should also have
 * finished course 1. So it is impossible.
 */
public class CourseSchedule207 {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0)
			return true;

		Map<Integer, Set<Integer>> map = new HashMap<>();
		int[] degree = new int[numCourses];
		for (int[] pre : prerequisites) {
			if (!map.containsKey(pre[0])) {
				map.put(pre[0], new HashSet<>());
			}
			if (!map.containsKey(pre[1])) {
				map.put(pre[1], new HashSet<>());
			}
			if (map.get(pre[1]).add(pre[0])) {
				degree[pre[0]]++;
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (degree[i] == 0) {
				q.offer(i);
			}
		}
		int count = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			count++;
			Set<Integer> set = map.getOrDefault(cur, new HashSet<>());
			for (int next : set) {
				degree[next]--;
				if (degree[next] == 0)
					q.offer(next);
			}
		}
		return count == numCourses;
	}
}
