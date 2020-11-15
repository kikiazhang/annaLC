package freq.four;

public class WordSearch {
	/**
	 * board = [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ]
	 * 
	 * Given word = "ABCCED", return true. Given word = "SEE", return true. Given
	 * word = "ABCB", return false.
	 */
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 || board[0].length == 0)
			return false;
		if (word == null || word.length() == 0)
			return true;

		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (canFind(board, i, j, word, 0, visited))
					return true;
			}
		}
		return false;
	}

	public boolean canFind(char[][] board, int i, int j, String word, int idx, boolean[][] visited) {
		if (idx == word.length())
			return true;
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]
				|| board[i][j] != word.charAt(idx))
			return false;

		boolean can = false;
		visited[i][j] = true;
		can = can || canFind(board, i + 1, j, word, idx + 1, visited);
		can = can || canFind(board, i - 1, j, word, idx + 1, visited);
		can = can || canFind(board, i, j + 1, word, idx + 1, visited);
		can = can || canFind(board, i, j - 1, word, idx + 1, visited);
		visited[i][j] = false;

		return can;
	}
}
