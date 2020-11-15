package google;

import java.util.ArrayList;
import java.util.List;

/**
 * // This is the Master's API interface. // You should not implement it, or
 * speculate about its implementation interface Master { public int guess(String
 * word) {} }
 */
public class GuessTheWord843 {
	int[][] h;

	public void findSecretWord(String[] wordlist// , Master master
	) {
		if (wordlist == null || wordlist.length == 0)
			return;

		int n = wordlist.length;
		h = new int[n][n];// 两个单词的match个数
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int match = 0;
				for (int k = 0; k < 6; k++) {
					if (wordlist[i].charAt(k) == wordlist[j].charAt(k)) {
						match++;
					}
				}
				h[i][j] = h[j][i] = match;
			}
		}

		List<Integer> poss = new ArrayList<>();// 可以选的index
		List<Integer> path = new ArrayList<>();// 找过了的
		for (int i = 0; i < n; i++)
			poss.add(i);

		while (!poss.isEmpty()) {
			int guess = solve(poss, path);// 最多的match数的word
			int match = 0;// master.guess(wordlist[guess]);
			if (match == wordlist[0].length())
				return;// 找到了
			List<Integer> poss2 = new ArrayList<>();
			for (Integer j : poss) {// 所有没找过的words中match这么多的
				if (h[guess][j] == match) {
					poss2.add(j);
				}
			}
			poss = poss2;
			path.add(guess);
		}
	}

	private int solve(List<Integer> poss, List<Integer> path) {
		if (poss.size() <= 2)
			return poss.get(0);// 就2个 不判断了 随便一个就行
		List<Integer> ansgroup = poss;
		int ansguess = -1;

		for (int guess = 0; guess < h.length; guess++) {// 0-6 可能的word index
			if (!path.contains(guess)) {
				ArrayList<Integer>[] groups = new ArrayList[7];// 跟此时guess相match的word
				for (int i = 0; i < 7; i++) {
					groups[i] = new ArrayList<>();
				}
				for (Integer j : poss) {
					if (j != guess) {
						groups[h[guess][j]].add(j);
					}
				}
				ArrayList<Integer> maxgroup = groups[0];// match 0个
				for (int i = 0; i < 7; i++) {
					if (groups[i].size() > maxgroup.size()) {// 找到groups里面最长的list
						maxgroup = groups[i];
					}
				}
				if (maxgroup.size() < ansgroup.size()) {// 最长的list是ans
					ansgroup = maxgroup;
					ansguess = guess;
				}
			}
		}
		return ansguess;
	}
}
