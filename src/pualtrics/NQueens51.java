package pualtrics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 * 
 * Example:
 * 
 * Input: 4 Output: [ [".Q..", // Solution 1 "...Q", "Q...", "..Q."],
 * 
 * ["..Q.", // Solution 2 "Q...", "...Q", ".Q.."] ] Explanation: There exist two
 * distinct solutions to the 4-queens puzzle as shown above.
 */
public class NQueens51 {
	public List<List<String>> solveNQueens1(int n) {
		List<List<String>> rst = new ArrayList<>();
		int[] nQueens = new int[n];// index is cow, value is col
		helper(nQueens, 0, n, rst);
		return rst;
	}

	private void helper(int[] nQueens, int index, int len, List<List<String>> rst) {
		if (index == len) {// 结束了，写入res
			// construct result
			List<String> temp = new LinkedList<>();
			for (int num : nQueens) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < len; i++) {
					if (i == num) {
						sb.append("Q");
					} else {
						sb.append(".");
					}
				}
				temp.add(sb.toString());
			}
			rst.add(temp);
		} else {
			// num is col， index is row
			for (int num = 0; num < len; num++) {
				nQueens[index] = num;// 挨个放，能valid就backtrack下一个Q
				if (isValid(nQueens, index)) {
					helper(nQueens, index + 1, len, rst);
				}
			}
		}
	}

	private boolean isValid(int[] nQueens, int i) {
		for (int idx = 0; idx < i; idx++) {// 只用看row小于i的
			// col相同｜｜斜率相同
			if (nQueens[idx] == nQueens[i] || Math.abs(nQueens[idx] - nQueens[i]) == i - idx) {
				return false;
			}
		}
		return true;
	}

	int rows[];// index是col，大于0说明这个col有queen
	// "hill" diagonals
	int hills[];// /斜率, col + row = const
	// "dale" diagonals
	int dales[];// \斜率, rol - col = const = (2*n)
	int n;
	// output
	List<List<String>> output = new ArrayList<>();
	// queens positions
	int queens[];// value是col，index是row，因为是一行行放的，所以行不会冲突，不用考虑

	public boolean isNotUnderAttack(int row, int col) {
		int res = rows[col] + hills[row - col + 2 * n] + dales[row + col];// 都不存在queen
		return (res == 0) ? true : false;
	}

	public void placeQueen(int row, int col) {
		queens[row] = col;
		rows[col] = 1;
		hills[row - col + 2 * n] = 1; // "hill" diagonals
		dales[row + col] = 1; // "dale" diagonals
	}

	public void removeQueen(int row, int col) {
		queens[row] = 0;
		rows[col] = 0;
		hills[row - col + 2 * n] = 0;
		dales[row + col] = 0;
	}

	public void addSolution() {
		// 写一种可能到output
		List<String> solution = new ArrayList<String>();
		for (int i = 0; i < n; ++i) {
			int col = queens[i];// queens里面index是row，value是col
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < col; ++j)
				sb.append(".");
			sb.append("Q");
			for (int j = 0; j < n - col - 1; ++j)
				sb.append(".");
			solution.add(sb.toString());
		}
		output.add(solution);
	}

	public void backtrack(int row) {
		for (int col = 0; col < n; col++) {
			if (isNotUnderAttack(row, col)) {// 现在是可以放的，就放
				placeQueen(row, col);
				// if n queens are already placed，放完了，一种可能出现，加入output
				if (row + 1 == n)
					addSolution();
				// if not proceed to place the rest
				else
					backtrack(row + 1);// 下一行
				// backtrack
				removeQueen(row, col);// 这种place完了，remove加入的，好继续
			}
		}
	}

	// o（n！）n factorial
	public List<List<String>> solveNQueens(int n) {
		this.n = n;
		rows = new int[n];
		hills = new int[4 * n - 1];
		dales = new int[2 * n - 1];
		queens = new int[n];

		backtrack(0);
		return output;
	}
}
