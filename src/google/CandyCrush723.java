package google;

public class CandyCrush723 {
	public int[][] candyCrush(int[][] board) {
		// Time Complexity: O((R*C)^2)
		if (board == null || board.length == 0 || board[0].length == 0)
			return board;

		int R = board.length, C = board[0].length;
		boolean todo = false;
		// 横向合并
		for (int r = 0; r < R; r++) {
			for (int c = 0; c + 2 < C; c++) {
				int v = Math.abs(board[r][c]);
				if (v != 0 && v == Math.abs(board[r][c + 1]) && v == Math.abs(board[r][c + 2])) {
					board[r][c] = board[r][c + 1] = board[r][c + 2] = -v;
					todo = true;
				}
			}
		}
		// 竖向合并
		for (int r = 0; r + 2 < R; r++) {
			for (int c = 0; c < C; c++) {
				int v = Math.abs(board[r][c]);
				if (v != 0 && v == Math.abs(board[r + 1][c]) && v == Math.abs(board[r + 2][c])) {
					board[r][c] = board[r + 1][c] = board[r + 2][c] = -v;
					todo = true;
				}
			}
		}
		// 从最后一行，每一个column开始下落
		for (int c = 0; c < C; c++) {
			int wr = R - 1;// 下落到行数wr
			for (int r = R - 1; r >= 0; r--) {
				if (board[r][c] > 0) {// >0说明要保留，进行下落
					board[wr--][c] = board[r][c];// wr向上移动
				}
			}
			while (wr >= 0) {// 补0
				board[wr--][c] = 0;
			}
		}
		return todo ? candyCrush(board) : board;
	}
}
