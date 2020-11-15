package ms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class JumpGame {

	/**
	 * https://leetcode.com/problems/jump-game-iii/
	 * 
	 * You are given an array of non-negative integers arr and a start index. When
	 * you are at an index i, you can move left or right by arr[i]. Your task is to
	 * figure out if you can reach value 0.
	 * 
	 * Example 1:
	 * 
	 * Input: arr = [3, 4, 2, 3, 0, 3, 1, 2, 1], start = 7 Output: true Explanation:
	 * left -> left -> right Related problems:
	 * 
	 * https://leetcode.com/problems/jump-game/
	 */
	// bfs O(n)
	public static boolean jump(int[] arr, int k) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(k);
		Set<Integer> visited = new HashSet<>();
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (arr[cur] == 0)
				return true;
			if (!visited.contains(cur)) {
				visited.add(cur);
				if (cur - arr[cur] >= 0) {
					q.offer(cur - arr[cur]);
				}
				if (cur + arr[cur] < arr.length) {
					q.offer(cur + arr[cur]);
				}
			}
		}
		return false;
	}

	// dfs
	public boolean jumpDFS(int[] arr, int k) {
		Set<Integer> visited = new HashSet<>();
		return helper(arr, visited, k);
	}

	private boolean helper(int[] arr, Set<Integer> visited, int cur) {
		if (cur < 0 || cur >= arr.length || visited.contains(cur))
			return false;
		if (arr[cur] == 0)
			return true;
		visited.add(cur);
		boolean left = helper(arr, visited, cur - arr[cur]);
		if (left)
			return true;
		boolean right = helper(arr, visited, cur + arr[cur]);
		if (right)
			return true;
		return false;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 4, 2, 3, 0, 3, 1, 2, 1 };
		int start = 7;
		System.out.println(jump(nums, start));
	}
}
