package tags.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of scores of different students, return the average score of
 * each student's top five scores in the order of each student's id.
 * 
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the
 * student's score. The average score is calculated using integer division.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input:
 * [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]] Explanation: The average of the student with id = 1
 * is 87. The average of the student with id = 2 is 88.6. But with integer
 * division their average converts to 88.
 */
public class HighFive1086 {
	public int[][] highFive(int[][] items) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int[] s : items) {
			if (!map.containsKey(s[0])) {
				map.put(s[0], new ArrayList<>());
			}
			map.get(s[0]).add(s[1]);
		}
		int[][] res = new int[map.keySet().size()][2];
		int idx = 0;
		for (Integer s : map.keySet()) {
			List<Integer> tmp = map.get(s);
			Collections.sort(tmp, Collections.reverseOrder());
			int sum = 0;
			for (int i = 0; i < 5; i++) {
				sum += tmp.get(i);
			}
			res[idx][0] = s.intValue();
			res[idx][1] = sum / 5;
			idx++;
		}
		return res;
	}
}
