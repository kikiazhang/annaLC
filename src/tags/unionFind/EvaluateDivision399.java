package tags.unionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Equations are given in the format A / B = k, where A and B are variables
 * represented as strings, and k is a real number (floating point number). Given
 * some queries, return the answers. If the answer does not exist, return -1.0.
 * 
 * Example: Given a / b = 2.0, b / c = 3.0. queries are: a / c = ?, b / a = ?, a
 * / e = ?, a / a = ?, x / x = ? . return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * 
 * The input is: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries , where equations.size() ==
 * values.size(), and the values are positive. This represents the equations.
 * Return vector<double>.
 * 
 * According to the example above:
 * 
 * equations = [ ["a", "b"], ["b", "c"] ], values = [2.0, 3.0], queries = [
 * ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 */
public class EvaluateDivision399 {
	Map<String, Map<String, Double>> graph = new HashMap<>();// 被除数，除数+result

	public double[] calcEquation(List<List<String>> equs, double[] values, List<List<String>> queries) {
		// build graph
		buildGraph(equs, values);// 正除反除都写入
		double[] res = new double[queries.size()];
		for (int i = 0; i < queries.size(); i++) {
			res[i] = getPathWeight(queries.get(i).get(0), queries.get(i).get(1), new HashSet<String>());// 被除数，除数，visited
																										// set
		}
		return res;
	}

	private void buildGraph(List<List<String>> equs, double[] values) {
		String u, v;
		for (int i = 0; i < equs.size(); i++) {
			u = equs.get(i).get(0);
			v = equs.get(i).get(1);
			graph.putIfAbsent(u, new HashMap<>());// 被除数
			graph.get(u).put(v, values[i]);
			graph.putIfAbsent(v, new HashMap<>());// 除数
			graph.get(v).put(u, 1 / values[i]);
		}
	}

	private double getPathWeight(String start, String end, Set<String> visited) {
		// rejection 如果没见过除数，被除数
		if (!graph.containsKey(start) || !graph.containsKey(end)) {
			return -1.0;
		}

		// accept，直接找到
		if (graph.get(start).containsKey(end)) {
			return graph.get(start).get(end);
		}

		visited.add(start);// 写入被除数
		for (Map.Entry<String, Double> neighbour : graph.get(start).entrySet()) {// 所有除数变成下一轮被除数
			if (!visited.contains(neighbour.getKey())) {// 没用过这个除数，都可以试试，不用revert
														// visited只要能最后到达，如果最后是-1，那么这一流是不行了，也不用revert visited
				double productWeight = getPathWeight(neighbour.getKey(), end, visited);
				if (productWeight != -1.0) {// 找到了
					return productWeight * neighbour.getValue();
				}
			}
		}
		return -1.0;

	}
}
