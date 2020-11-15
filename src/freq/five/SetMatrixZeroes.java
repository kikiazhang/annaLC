package freq.five;

public class SetMatrixZeroes {

	/**
	 * Input: [ [1,1,1], [1,0,1], [1,1,1] ] Output: [ [1,0,1], [0,0,0], [1,0,1] ]
	 */
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;

		boolean[] rows = new boolean[matrix.length];
		boolean[] cols = new boolean[matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					rows[i] = true;
					cols[j] = true;
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			if (rows[i]) {
				for (int j = 0; j < matrix[0].length; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		for (int j = 0; j < matrix[0].length; j++) {
			if (cols[j]) {
				for (int i = 0; i < matrix.length; i++) {
					matrix[i][j] = 0;
				}
			}
		}
	}
}
