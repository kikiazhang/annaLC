package tags.backtracking;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * A sudoku solution must satisfy all of the following rules:
 * 
 * Each of the digits 1-9 must occur exactly once in each row. Each of the
 * digits 1-9 must occur exactly once in each column. Each of the the digits 1-9
 * must occur exactly once in each of the 9 3x3 sub-boxes of the grid. Empty
 * cells are indicated by the character '.'.
 */
public class SudokuSolver37 {
	int N = 9;
	int n = 3;
	// 只看row 是否符合，大于0，说明有这个index已经存在
	int[][] rows = new int[N][N + 1];
	// 只看col是否符合
	int[][] columns = new int[N][N + 1];
	// 只看box是否符合
	int[][] boxes = new int[N][N + 1];// box_index = (row / 3) * 3 + column / 3

	char[][] board;

	boolean sudokuSolved = false;

	public void solveSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9)
			return;
		this.board = board;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				char num = board[i][j];
				if (num != '.') {
					int d = Character.getNumericValue(num);
					// 放入所有初始的num
					placeNumber(d, i, j);
				}
			}
		}
		backtrack(0, 0);// 找结果
	}

	private void placeNumber(int d, int r, int c) {
		int idx = (r / n) * n + c / n;
		rows[r][d]++;
		columns[c][d]++;
		boxes[idx][d]++;
		board[r][c] = (char) (d + '0');// 放入class的board里
	}

	private void backtrack(int r, int c) {
		if (board[r][c] == '.') {// 是空位，就1-9都试一遍，目前能放，就placeNumber，并且看下一位，如果最后不能solved，再remove
			for (int d = 1; d < 10; d++) {
				if (couldPlace(d, r, c)) {
					placeNumber(d, r, c);
					placeNextNumber(r, c);
					if (!sudokuSolved)
						removeNumber(d, r, c);
				}
			}
		} else {
			// 这个点不为空，继续下一个点
			placeNextNumber(r, c);
		}
	}

	private void placeNextNumber(int r, int c) {
		if ((c == N - 1) && (r == N - 1)) {// 走完了
			sudokuSolved = true;
		} else {
			// 先走c，再走r
			if (c == N - 1)
				backtrack(r + 1, 0);
			else
				backtrack(r, c + 1);
		}
	}

	private void removeNumber(int d, int r, int c) {

		int idx = (r / n) * n + c / n;
		rows[r][d]--;
		columns[c][d]--;
		boxes[idx][d]--;
		board[r][c] = '.';
	}

	private boolean couldPlace(int d, int r, int c) {
		int idx = (r / n) * n + c / n;
		return rows[r][d] + columns[c][d] + boxes[idx][d] == 0;// row col box里面都没有存在
	}
}
