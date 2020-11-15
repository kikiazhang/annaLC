package tags.dfs.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected
 * server-to-server connections forming a network where connections[i] = [a, b]
 * represents a connection between servers a and b. Any server can reach any
 * other server directly or indirectly through the network.
 * 
 * A critical connection is a connection that, if removed, will make some server
 * unable to reach some other server.
 * 
 * Return all critical connections in the network in any order. Input: n = 4,
 * connections = [[0,1],[1,2],[2,0],[1,3]] Output: [[1,3]] Explanation: [[3,1]]
 * is also accepted.
 */
public class CriticalConnectionsinaNetwork1192 {
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<Integer>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (List<Integer> l : connections) {// 写入两点关系
			graph[l.get(0)].add(l.get(1));
			graph[l.get(1)].add(l.get(0));
		}
		List<List<Integer>> res = new ArrayList<>();
		search(graph, 0, -1, new int[n], 1, res); // int[]为每一层能到的level，初始值0
		return res;
	}

	// cur点能到的min level
	private int search(List<Integer>[] graph, int cur, int parent, int[] levels, int level, List<List<Integer>> res) {
		levels[cur] = level;// cur所在level
		int min = level;
		for (int child : graph[cur]) {
			if (child == parent)// 跳过parent
				continue;
			if (levels[child] > 0) {// 说明计算过child
				min = Math.min(min, levels[child]);// 更新min
				continue;
			}
			int childLevel = search(graph, child, cur, levels, level + 1, res);// child level 从level+1开始
			if (childLevel > level) {// 没有cur，child level就变大了，说明cur。child这段是唯一
				res.add(Arrays.asList(cur, child));
			} else {
				min = Math.min(min, childLevel);// 可以通过child到更低level
			}
		}
		return min;
	}
}
