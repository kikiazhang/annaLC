package amazonOA;

public class Search2DMatrixII {
	/**
	 * Write an efficient algorithm that searches for a value in an m x n matrix.
	 * This matrix has the following properties:
	 * 
	 * Integers in each row are sorted in ascending from left to right. Integers in
	 * each column are sorted in ascending from top to bottom. Example:
	 * 
	 * Consider the following matrix:
	 * 
	 * [ [1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17,
	 * 24], [18, 21, 23, 26, 30] ] Given target = 5, return true.
	 * 
	 * Given target = 20, return false.
	 */
	/**
	 * Time complexity : \mathcal{O}(n+m)O(n+m)
	 * 
	 * The key to the time complexity analysis is noticing that, on every iteration
	 * (during which we do not return true) either row or col is is
	 * decremented/incremented exactly once. Because row can only be decremented mm
	 * times and col can only be incremented nn times before causing the while loop
	 * to terminate, the loop cannot run for more than n+mn+m iterations. Because
	 * all other work is constant, the overall time complexity is linear in the sum
	 * of the dimensions of the matrix.
	 * 
	 * Space complexity : \mathcal{O}(1)O(1)
	 * 
	 * 
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		// start our "pointer" in the bottom-left
		int row = matrix.length - 1;
		int col = 0;

		while (row >= 0 && col < matrix[0].length) {
			if (matrix[row][col] > target) {
				row--;
			} else if (matrix[row][col] < target) {
				col++;
			} else { // found it
				return true;
			}
		}
		return false;
	}

	/**
	 * Time complexity : \mathcal{O}(nlgn)O(nlgn) 
	 * Space complexity :
	 * \mathcal{O}(lgn)O(lgn)
	 */
	private int[][] matrix;
	private int target;

	private boolean searchRec(int left, int up, int right, int down) {
		// this submatrix has no height or no width.
		if (left > right || up > down) {
			return false;
			// `target` is already larger than the largest element or smaller
			// than the smallest element in this submatrix.
		} else if (target < matrix[up][left] || target > matrix[down][right]) {
			return false;
		}

		int mid = left + (right - left) / 2;

		// Locate `row` such that matrix[row-1][mid] < target < matrix[row][mid]
		int row = up;
		while (row <= down && matrix[row][mid] <= target) {
			if (matrix[row][mid] == target) {
				return true;
			}
			row++;
		}

		return searchRec(left, row, mid - 1, down) || searchRec(mid + 1, up, right, row - 1);
	}

	public boolean searchMatrix2(int[][] mat, int targ) {
		// cache input values in object to avoid passing them unnecessarily
		// to `searchRec`
		matrix = mat;
		target = targ;

		// an empty matrix obviously does not contain `target`
		if (matrix == null || matrix.length == 0) {
			return false;
		}

		return searchRec(0, 0, matrix[0].length - 1, matrix.length - 1);
	}
}
