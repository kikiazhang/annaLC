package tags.graph.topologicalSort;

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
	// graph o(m*n)
	public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {

		HashMap<String, HashMap<String, Double>> graph = new HashMap<>();// dividend, <divisor, result>, 正反都写

		// Step 1). build the graph from the equations
		for (int i = 0; i < equations.size(); i++) {
			List<String> equation = equations.get(i);
			String dividend = equation.get(0), divisor = equation.get(1);
			double quotient = values[i];

			if (!graph.containsKey(dividend))
				graph.put(dividend, new HashMap<String, Double>());
			if (!graph.containsKey(divisor))
				graph.put(divisor, new HashMap<String, Double>());

			graph.get(dividend).put(divisor, quotient);
			graph.get(divisor).put(dividend, 1 / quotient);
		}

		// Step 2). Evaluate each query via bactracking (DFS)
		// by verifying if there exists a path from dividend to divisor
		double[] results = new double[queries.size()];
		for (int i = 0; i < queries.size(); i++) {
			List<String> query = queries.get(i);
			String dividend = query.get(0), divisor = query.get(1);

			if (!graph.containsKey(dividend) || !graph.containsKey(divisor))// 有一个不存在graph，就不行
				results[i] = -1.0;
			else if (dividend == divisor)
				results[i] = 1.0;
			else {
				// 需要记录visited， 和目前product
				HashSet<String> visited = new HashSet<>();
				results[i] = backtrackEvaluate(graph, dividend, divisor, 1, visited);
			}
		}

		return results;
	}

	private double backtrackEvaluate(HashMap<String, HashMap<String, Double>> graph, String currNode, String targetNode,
			double accProduct, Set<String> visited) {

		// mark the visit
		visited.add(currNode);
		double ret = -1.0;

		Map<String, Double> neighbors = graph.get(currNode);
		if (neighbors.containsKey(targetNode))
			ret = accProduct * neighbors.get(targetNode);
		else {
			// 一个一个试，没visited就可以，反正殊途同归
			for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
				String nextNode = pair.getKey();
				if (visited.contains(nextNode))
					continue;
				ret = backtrackEvaluate(graph, nextNode, targetNode, accProduct * pair.getValue(), visited);
				if (ret != -1.0)
					break;
			}
		}

		// unmark the visit, for the next backtracking
		visited.remove(currNode);
		return ret;
	}

	// union find with weight(o((m+n)logn))
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
