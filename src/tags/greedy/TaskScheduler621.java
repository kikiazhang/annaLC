package tags.greedy;

import java.util.Arrays;

/**
 * You are given a char array representing tasks CPU need to do. It contains
 * capital letters A to Z where each letter represents a different task. Tasks
 * could be done without the original order of the array. Each task is done in
 * one unit of time. For each unit of time, the CPU could complete either one
 * task or just be idle.
 * 
 * However, there is a non-negative integer n that represents the cooldown
 * period between two same tasks (the same letter in the array), that is that
 * there must be at least n units of time between any two same tasks.
 * 
 * You need to return the least number of units of times that the CPU will take
 * to finish all the given tasks.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: tasks = ["A","A","A","B","B","B"], n = 2 Output: 8 Explanation: A -> B
 * -> idle -> A -> B -> idle -> A -> B There is at least 2 units of time between
 * any two same tasks. Example 2:
 * 
 * Input: tasks = ["A","A","A","B","B","B"], n = 0 Output: 6 Explanation: On
 * this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"] ["A","B","A","B","A","B"] ["B","B","B","A","A","A"]
 * ... And so on.
 */
public class TaskScheduler621 {
	// o(n)
	public int leastInterval(char[] tasks, int n) {
		int[] cnt = new int[26];
		for (char c : tasks) {
			cnt[c - 'A']++;
		}
		Arrays.sort(cnt);// freq desc list

		int max = cnt[25];// max freq
		int idle = (max - 1) * n;// max idle
		for (int i = cnt.length - 2; i >= 0 && idle > 0; i--) {// 直到idle小于等于0||到头了
			idle -= Math.min(max - 1, cnt[i]);// max-1 个空隙，能放的都放入一个cnt【i】，放入了的就能减一个idle
		}
		idle = Math.max(idle, 0);
		return idle + tasks.length;// 本长度+idle个数
	}
}
