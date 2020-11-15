package pualtrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus
 * repeats forever. For example if routes[0] = [1, 5, 7], this means that the
 * first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->...
 * forever.
 * 
 * We start at bus stop S (initially not on a bus), and we want to go to bus
 * stop T. Travelling by buses only, what is the least number of buses we must
 * take to reach our destination? Return -1 if it is not possible.
 * 
 * Example: Input: routes = [[1, 2, 7], [3, 6, 7]] S = 1 T = 6 Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then
 * take the second bus to the bus stop 6.
 */
public class BusRoutes815 {
	public int numBusesToDestination(int[][] routes, int S, int T) {
		if (S == T)
			return 0;
		int n = routes.length;

		Map<Integer, List<Integer>> busesInStop = new HashMap<>();// stop, bus list
		for (int i = 0; i < n; i++) {
			for (int stop : routes[i]) {
				busesInStop.computeIfAbsent(stop, z -> new ArrayList<>()).add(i);
			}
		}
		Set<Integer> visitedBus = new HashSet<>();
		Set<Integer> visitedStop = new HashSet<>();
		visitedStop.add(S);
		int count = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(S);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int stop = q.poll();
				if (stop == T) {// find T
					return count;
				}
				for (int bus : busesInStop.get(stop)) {// each bus can reach the stop
					if (visitedBus.contains(bus)) {// skip visited bus
						continue;
					}
					visitedBus.add(bus);// add cur bus to visited
					for (int busStop : routes[bus]) {// each next stop this bus can reach
						if (visitedStop.contains(busStop)) {// skip visited stop
							continue;
						}
						visitedStop.add(busStop);// add stop
						q.offer(busStop);// add to queue
					}
				}
			}
			count++;
		}
		return -1;
	}
}
