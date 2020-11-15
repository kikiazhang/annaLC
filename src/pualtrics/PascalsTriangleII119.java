package pualtrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer rowIndex, return the rowIndexth row of the Pascal's
 * triangle.
 * 
 * Notice that the row index starts from 0.
 */
public class PascalsTriangleII119 {
	public List<Integer> getRow(int k) {
		// dp
		Integer[] arr = new Integer[k + 1];
		Arrays.fill(arr, 0);
		arr[0] = 1;
		// write k times
		for (int i = 1; i <= k; i++)
			for (int j = i; j > 0; j--) // j = 1, 2, 3, ... k开始累加（不动0，0永远是1）
				arr[j] = arr[j] + arr[j - 1];

		return Arrays.asList(arr);
	}

	// 118 PascalsTriangleI
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();// cur layer
		for (int i = 0; i < numRows; i++) {
			row.add(0, 1);// first is 1
			for (int j = 1; j < row.size() - 1; j++)// cause first add 1
				row.set(j, row.get(j) + row.get(j + 1));// add the prev layer 2 index
			res.add(new ArrayList<Integer>(row));// add to res
		}
		return res;
	}
}
