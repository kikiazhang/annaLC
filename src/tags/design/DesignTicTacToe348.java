package tags.design;

/**
 * Assume the following rules are for the tic-tac-toe game on an n x n board
 * between two players:
 * 
 * A move is guaranteed to be valid and is placed on an empty block. Once a
 * winning condition is reached, no more moves are allowed. A player who
 * succeeds in placing n of their marks in a horizontal, vertical, or diagonal
 * row wins the game. Implement the TicTacToe class:
 * 
 * TicTacToe(int n) Initializes the object the size of the board n. int move(int
 * row, int col, int player) Indicates that player with id player plays at the
 * cell (row, col) of the board. The move is guaranteed to be a valid move.
 * Follow up: Could you do better than O(n2) per move() operation?
 * 
 * 
 * 
 * Example 1:
 * 
 * Input ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2,
 * 1, 1]] Output [null, 0, 0, 0, 0, 0, 0, 1]
 */
public class DesignTicTacToe348 {
	private int[] rows;// one index means one row sum
	private int[] cols;// one index means one col sum
	private int diagonal;// \
	private int antiDiagonal;// /
	// play1 each step is 1, player2 each step is -1
	// so play1 win means one line(row | col | diagonal | antiDiagonal) is 3,
	// player2 win means one line is -3

	/** Initialize your data structure here. */
	public DesignTicTacToe348(int n) {
		rows = new int[n];
		cols = new int[n];
	}

	/**
	 * Player {player} makes a move at ({row}, {col}).
	 * 
	 * @param row    The row of the board.
	 * @param col    The column of the board.
	 * @param player The player, can be either 1 or 2.
	 * @return The current winning condition, can be either: 0: No one wins. 1:
	 *         Player 1 wins. 2: Player 2 wins.
	 */
	public int move(int row, int col, int player) {
		int toAdd = player == 1 ? 1 : -1;

		rows[row] += toAdd;
		cols[col] += toAdd;
		if (row == col) {// \
			diagonal += toAdd;
		}

		if (col == (cols.length - row - 1)) {// / => col + row == n - 1
			antiDiagonal += toAdd;
		}

		int size = rows.length;
		// find someone win
		if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size
				|| Math.abs(antiDiagonal) == size) {
			return player;
		}

		return 0;
	}
}
