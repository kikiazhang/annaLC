package tags.dfs.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There are a total of n courses you have to take labelled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example, if prerequisites[i] = [ai,
 * bi] this means you must take the course bi before the course ai.
 * 
 * Given the total number of courses numCourses and a list of the prerequisite
 * pairs, return the ordering of courses you should take to finish all courses.
 * 
 * If there are many valid answers, return any of them. If it is impossible to
 * finish all courses, return an empty array.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: numCourses = 2, prerequisites = [[1,0]] Output: [0,1] Explanation:
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So the correct course order is [0,1]. Example 2:
 * 
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]] Output:
 * [0,2,1,3] Explanation: There are a total of 4 courses to take. To take course
 * 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should
 * be taken after you finished course 0. So one correct course order is
 * [0,1,2,3]. Another correct ordering is [0,2,1,3]. Example 3:
 * 
 * Input: numCourses = 1, prerequisites = [] Output: [0]
 */
public class CourseScheduleII210 {
	// topological sort
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0)
			return null;

		int[] res = new int[numCourses];
		Map<Integer, Set<Integer>> map = new HashMap<>();// course, next course
		Map<Integer, Integer> preMap = new HashMap<>();// course, pre count
		for (int[] course : prerequisites) {
			if (!map.containsKey(course[0])) {
				map.put(course[0], new HashSet<>());
			}
			if (!map.containsKey(course[1])) {
				map.put(course[1], new HashSet<>());
			}
			map.get(course[1]).add(course[0]);
			preMap.put(course[0], preMap.getOrDefault(course[0], 0) + 1);
			if (!preMap.containsKey(course[1])) {
				preMap.put(course[1], 0);
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (int c = 0; c < numCourses; c++) {
			if (!preMap.containsKey(c) || preMap.get(c) == 0) {
				q.offer(c);
			}
		}
		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				res[count++] = cur;
				Set<Integer> nexts = map.getOrDefault(cur, new HashSet<>());
				for (int n : nexts) {
					preMap.put(n, preMap.get(n) - 1);
					if (preMap.get(n) == 0)
						q.offer(n);
				}
			}
		}
		if (count == numCourses) {
			return res;
		}
		return new int[0];
	}
}
