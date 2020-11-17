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

	// trie
	char[][] _board = null;

	public List<String> findWords2(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0)
			return res;

		// 建立trieNode
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode node = root;// 从root开始

			for (Character letter : word.toCharArray()) {// 有child加入；没有写newNode；并且node向下到child
				if (node.children.containsKey(letter)) {
					node = node.children.get(letter);
				} else {
					TrieNode newNode = new TrieNode();
					node.children.put(letter, newNode);
					node = newNode;
				}
			}
			node.word = word; // store words in Trie（最终完整的word）
		}
		this._board = board;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (root.children.containsKey(Character.valueOf(board[i][j]))) {// 有这个开始char
					helper(i, j, root, res);
				}
			}
		}
		return res;
	}

	private void helper(int i, int j, TrieNode root, List<String> res) {
		Character letter = this._board[i][j];
		TrieNode currNode = root.children.get(letter);// child node

		if (currNode.word != null) {// 说明是完整的word，加入res，去除curNode word（避免重复）
			res.add(currNode.word);
			currNode.word = null;
		}
		this._board[i][j] = '#';// visited

		int[] rowOffset = { -1, 0, 1, 0 };
		int[] colOffset = { 0, 1, 0, -1 };
		for (int k = 0; k < 4; ++k) {// 四个方向走下去看新child
			int newRow = i + rowOffset[k];
			int newCol = j + colOffset[k];
			if (newRow < 0 || newRow >= this._board.length || newCol < 0 || newCol >= this._board[0].length) {// 越界
				continue;
			}
			if (currNode.children.containsKey(this._board[newRow][newCol])) {// 有新的，再backtrack
				helper(newRow, newCol, currNode, res);
			}
		}
		this._board[i][j] = letter;// 去掉visited
		// Optimization: incrementally remove the leaf
		// nodes，已经走完所有letter开始的了，可以去掉letter所有children
		if (currNode.children.isEmpty()) {
			root.children.remove(letter);
		}
	}

	class TrieNode {
		HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();// child char，child trieNode
		String word = null;

		public TrieNode() {
		}
	}
}
