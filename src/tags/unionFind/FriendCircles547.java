package tags.unionFind;

import java.util.Arrays;

/**
 * There are N students in a class. Some of them are friends, while some are
 * not. Their friendship is transitive in nature. For example, if A is a direct
 * friend of B, and B is a direct friend of C, then A is an indirect friend of
 * C. And we defined a friend circle is a group of students who are direct or
 * indirect friends.
 * 
 * Given a N*N matrix M representing the friend relationship between students in
 * the class. If M[i][j] = 1, then the ith and jth students are direct friends
 * with each other, otherwise not. And you have to output the total number of
 * friend circles among all the students.
 * 
 * Example 1:
 * 
 * Input: [[1,1,0], [1,1,0], [0,0,1]] Output: 2 Explanation:The 0th and 1st
 * students are direct friends, so they are in a friend circle. The 2nd student
 * himself is in a friend circle. So return 2.
 * 
 * 
 * Example 2:
 * 
 * Input: [[1,1,0], [1,1,1], [0,1,1]] Output: 1 Explanation:The 0th and 1st
 * students are direct friends, the 1st and 2nd students are direct friends, so
 * the 0th and 2nd students are indirect friends. All of them are in the same
 * friend circle, so return 1.
 */
public class FriendCircles547 {
	public int findCircleNum(int[][] M) {
		if (M == null || M.length == 0) {
			return 0;
		}
		Union union = new Union(M.length);

		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				if (M[i][j] == 1 && i != j) {
					union.union(i, j);
				}
			}
		}

		int count = 0;
		for (int i = 0; i < M.length; i++) {
			if (union.parents[i] == -1) {
				count++;
			}
		}

		return count;
	}
}

class Union {
	int[] parents;

	public Union(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}

	public int find(int index) {
		if (parents[index] == -1) {// 如果是初始值。就返回自己
			return index;
		}
		return find(parents[index]);
	}

	public void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			parents[rootB] = rootA;
		}
	}
}
