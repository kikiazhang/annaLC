package pualtrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an m x n board of characters and a list of strings words, return all
 * words on the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same
 * letter cell may not be used more than once in a word.
 */
public class WordSearchII212 {
	// 简化版，只能在一条线上找
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0)
			return res;
		// 记录start char，word list
		Map<Character, List<String>> map = new HashMap<>();
		for (String word : words) {
			if (!map.containsKey(word.charAt(0))) {
				map.put(word.charAt(0), new ArrayList<>());
			}
			map.get(word.charAt(0)).add(word);
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				// 有char开始
				if (map.containsKey(board[i][j])) {
					// 遍历所有没找过的word
					for (String word : map.get(board[i][j])) {
						if (!res.contains(word)) {
							// 每次只看一个方向
							helper(board, i, j, res, word, 1, 0, "");
							helper(board, i, j, res, word, -1, 0, "");
							helper(board, i, j, res, word, 0, 1, "");
							helper(board, i, j, res, word, 0, -1, "");
						}
					}
				}
			}
		}
		return res;
	}

	private void helper(char[][] board, int i, int j, List<String> res, String word, int x, int y, String curWord) {
		// 保存条件
		if (curWord.equals(word) && !res.contains(curWord)) {
			res.add(word);
		}
		// 结束条件
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
			return;
		// 没结束，继续往下找
		helper(board, i + x, j + y, res, word, x, y, curWord + board[i][j]);
	}
}
