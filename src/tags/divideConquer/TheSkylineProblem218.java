package tags.divideConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the
 * buildings in that city when viewed from a distance. Now suppose you are given
 * the locations and height of all the buildings as shown on a cityscape photo
 * (Figure A), write a program to output the skyline formed by these buildings
 * collectively (Figure B).
 */
public class TheSkylineProblem218 {
	/**
	 * Divide-and-conquer algorithm to solve skyline problem, which is similar with
	 * the merge sort algorithm. For instance, the dimensions of all buildings in
	 * Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24
	 * 8] ] . For instance, the skyline in Figure B should be represented as:[ [2
	 * 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
	 */
	public List<List<Integer>> getSkyline(int[][] buildings) {
		int n = buildings.length;
		List<List<Integer>> res = new ArrayList<>();

		if (n == 0)
			return res;
		if (n == 1) {// [2,9,10]
			int xSt = buildings[0][0];// 2
			int xEn = buildings[0][1];// 9
			int y = buildings[0][2];// 10 高度

			res.add(new ArrayList<Integer>() {// 2，10，开始的x和y
				{
					add(xSt);
					add(y);
				}
			});
			res.add(new ArrayList<Integer>() {// 9，0，y再次变化的x和新的y
				{
					add(xEn);
					add(0);
				}
			});

			return res;
		}

		List<List<Integer>> left, right;// divide到只剩一个或o个得到结果
		left = getSkyline(Arrays.copyOfRange(buildings, 0, n / 2));
		right = getSkyline(Arrays.copyOfRange(buildings, n / 2, n));

		return merge(left, right);
	}

	public List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
		int nL = left.size(), nR = right.size();
		int pL = 0, pR = 0;
		int curY = 0, leftY = 0, rightY = 0;
		int x, maxY;
		List<List<Integer>> output = new ArrayList<>();
		// 一一拿出
		while ((pL < nL) && (pR < nR)) {
			List<Integer> pointL = left.get(pL);
			List<Integer> pointR = right.get(pR);
			// x小的，先到，判断是否会改变output
			if (pointL.get(0) < pointR.get(0)) {
				x = pointL.get(0);
				leftY = pointL.get(1);
				pL++;
			} else {
				x = pointR.get(0);
				rightY = pointR.get(1);
				pR++;
			}
			// 此时最大Y
			maxY = Math.max(leftY, rightY);
			// curY是现在output里最后的Y，变化了，就要加入这个点
			if (curY != maxY) {
				updateOutput(output, x, maxY);
				curY = maxY;
			}
		}
		// 记录剩下的left，right到output
		appendSkyline(output, left, pL, nL, curY);
		appendSkyline(output, right, pR, nR, curY);

		return output;
	}

	public void updateOutput(List<List<Integer>> output, int x, int y) {
		// not vertical change，加入x，y点
		if (output.isEmpty() || output.get(output.size() - 1).get(0) != x) {
			output.add(new ArrayList<Integer>() {
				{
					add(x);
					add(y);
				}
			});
		} else {
			// is vertical change，只更改y， 1是index
			output.get(output.size() - 1).set(1, y);
		}
	}

	// 连接output和skyline
	public void appendSkyline(List<List<Integer>> output, List<List<Integer>> skyline, int p, int n, int curY) {
		// 遍历p之后的每一个点
		while (p < n) {
			List<Integer> point = skyline.get(p);
			int x = point.get(0);
			int y = point.get(1);
			p++;
			// 当y变化时，需要记录到output里
			if (curY != y) {
				updateOutput(output, x, y);
				curY = y;
			}
		}
	}
}
