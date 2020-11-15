package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A company has n employees with a unique ID for each employee from 0 to n - 1.
 * The head of the company has is the one with headID.
 * 
 * Each employee has one direct manager given in the manager array where
 * manager[i] is the direct manager of the i-th employee, manager[headID] = -1.
 * Also it's guaranteed that the subordination relationships have a tree
 * structure.
 * 
 * The head of the company wants to inform all the employees of the company of
 * an urgent piece of news. He will inform his direct subordinates and they will
 * inform their subordinates and so on until all employees know about the urgent
 * news.
 * 
 * The i-th employee needs informTime[i] minutes to inform all of his direct
 * subordinates (i.e After informTime[i] minutes, all his direct subordinates
 * can start spreading the news).
 * 
 * Return the number of minutes needed to inform all the employees about the
 * urgent news.
 * 
 * Input: n = 4, headID = 2, manager = [3,3,-1,2], informTime = [0,0,162,914]
 * Output: 1076
 */
public class TimeNeededInformAllEmployees1376 {
	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
		if (n <= 0)
			return 0;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			List<Integer> directEmp = map.getOrDefault(manager[i], new ArrayList<>());
			directEmp.add(i);
			map.put(manager[i], directEmp);
		}

		return helper(headID, map, informTime);
	}

	private int helper(int manager, Map<Integer, List<Integer>> map, int[] informTime) {
		if (map.get(manager) == null || map.get(manager).isEmpty())
			return 0;

		List<Integer> empList = map.get(manager);
		int max = Integer.MIN_VALUE;
		for (int emp : empList) {
			max = Math.max(max, helper(emp, map, informTime));
		}

		return informTime[manager] + max;
	}
}
