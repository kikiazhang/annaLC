package ms;

import java.util.Arrays;

public class WidestPathWithoutTrees {

	/**
	 * build the widest road between trees (X[0], Y[0]) is the tree
	 * 
	 * X=[5,5,5,7,7,7] Y=[3,4,5,1,3,7] return 2 X=[6,10,1,4,3] Y=[2,5,3,1,6] return
	 * 4
	 */
	public int solution(int[] x, int[] y) {
		if (x == null || y == null || x.length != y.length)
			return 0;

		int count = 0;
		Arrays.sort(x);
		for (int i = 0; i < x.length - 1; i++) {
			count = Math.max(count, x[i + 1] - x[i]);
		}

		return count;
	}

	public static void main(String[] args) {
		WidestPathWithoutTrees m = new WidestPathWithoutTrees();

		int[] x = { 5, 5, 5, 7, 7, 7 };
		int[] y = { 3, 4, 5, 1, 3, 7 };

		System.out.println(m.solution(x, y));
	}
}
