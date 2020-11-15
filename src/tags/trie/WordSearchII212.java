package tags.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * 
 * 
 * Example:
 * 
 * Input: board = [ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'],
 * ['i','f','l','v'] ] words = ["oath","pea","eat","rain"]
 * 
 * Output: ["eat","oath"]
 */
public class WordSearchII212 {
	// Time Limit Exceeded
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0)
			return res;

		Map<Character, List<String>> map = new HashMap<>();
		for (String str : words) {
			if (!map.containsKey(str.charAt(0)))
				map.put(str.charAt(0), new ArrayList<>());
			map.get(str.charAt(0)).add(str);
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (map.containsKey(Character.valueOf(board[i][j]))) {
					for (String word : map.get(Character.valueOf(board[i][j]))) {
						if (!res.contains(word)) {
							boolean[][] visited = new boolean[board.length][board[0].length];
							helper(board, i, j, word, visited, "", res);
						}
					}
				}
			}
		}
		return res;
	}

	private void helper(char[][] board, int i, int j, String word, boolean[][] visited, String cur, List<String> res) {
		if (cur.equals(word) && !res.contains(word)) {
			res.add(cur);
			return;
		}
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j])
			return;

		visited[i][j] = true;
		helper(board, i + 1, j, word, visited, cur + board[i][j], res);
		helper(board, i - 1, j, word, visited, cur + board[i][j], res);
		helper(board, i, j + 1, word, visited, cur + board[i][j], res);
		helper(board, i, j - 1, word, visited, cur + board[i][j], res);
		visited[i][j] = false;
	}

	// trieNode
	// Time complexity: O(M(4*3^{L-1})), where M is the number of cells in the board
	// and L is the maximum length of words.
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
