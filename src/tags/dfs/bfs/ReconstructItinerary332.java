package tags.dfs.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival
 * airports [from, to], reconstruct the itinerary in order. All of the tickets
 * belong to a man who departs from JFK. Thus, the itinerary must begin with
 * JFK.
 * 
 * Note:
 * 
 * If there are multiple valid itineraries, you should return the itinerary that
 * has the smallest lexical order when read as a single string. For example, the
 * itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"]. All
 * airports are represented by three capital letters (IATA code). You may assume
 * all tickets form at least one valid itinerary. One must use all the tickets
 * once and only once. Example 1:
 * 
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"] Example 2:
 * 
 * Input:
 * [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"] Explanation: Another possible
 * reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in
 * lexical order.
 */
public class ReconstructItinerary332 {
	// dfs 写出最优，就一定是valid解法
	public List<String> findItinerary(List<List<String>> tickets) {
		List<String> res = new ArrayList<>();
		Map<String, PriorityQueue<String>> map = new HashMap<>();// location, next locations list(sorted)

		for (List<String> ticket : tickets) {
			if (!map.containsKey(ticket.get(0))) {
				map.put(ticket.get(0), new PriorityQueue<>());
			}
			map.get(ticket.get(0)).add(ticket.get(1));
		}
		dfs("JFK", res, map);// always start at JFK
		return res;
	}

	private void dfs(String s, List<String> res, Map<String, PriorityQueue<String>> map) {
		PriorityQueue<String> q = map.get(s);
		while (q != null && !q.isEmpty()) {
			dfs(q.poll(), res, map);
		}
		res.add(0, s);// 加入这段res的最前面
	}

	// stack
	public List<String> findItinerary(String[][] tickets) {
		List<String> ans = new ArrayList<String>();
		if (tickets == null || tickets.length == 0)
			return ans;
		Map<String, PriorityQueue<String>> ticketsMap = new HashMap<>();
		for (int i = 0; i < tickets.length; i++) {
			if (!ticketsMap.containsKey(tickets[i][0]))
				ticketsMap.put(tickets[i][0], new PriorityQueue<String>());
			ticketsMap.get(tickets[i][0]).add(tickets[i][1]);
		}

		String curr = "JFK";
		Stack<String> drawBack = new Stack<String>();
		for (int i = 0; i < tickets.length; i++) {
			while (!ticketsMap.containsKey(curr) || ticketsMap.get(curr).isEmpty()) {
				drawBack.push(curr);
				curr = ans.remove(ans.size() - 1);
			}
			ans.add(curr);
			curr = ticketsMap.get(curr).poll();
		}
		ans.add(curr);
		while (!drawBack.isEmpty())
			ans.add(drawBack.pop());
		return ans;
	}
}
