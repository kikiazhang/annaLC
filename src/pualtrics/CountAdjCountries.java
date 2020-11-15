package pualtrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 一个矩阵里面有不同的数字代表不同的国家。给出所有相邻国家的组合：这个例子对应output {1，2} {2，3} [1, 1, 2, 3] [1, 2,
 * 2, 2] [1, 2, 3, 3]
 */
public class CountAdjCountries {

	public List<List<Integer>> countCountries(int[][] matrix) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (!map.containsKey(matrix[i][j])) {
					map.put(matrix[i][j], new HashSet<>());
				}
				for (int[] d : dir) {
					int x = i + d[0];
					int y = j + d[1];
					if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length)
						continue;
					if (matrix[x][y] != matrix[i][j])
						map.get(matrix[i][j]).add(matrix[x][y]);
				}
			}
		}
		Set<String> set = new HashSet<>();
		for (int key : map.keySet()) {
			Set<Integer> nei = map.get(key);
			for (int n : nei) {
				String tmp = key > n ? n + "," + key : key + "," + n;
				if (!set.contains(tmp))
					set.add(tmp);
			}

		}
		List<List<Integer>> res = new ArrayList<>();
		for (String tmp : set) {
			String[] nums = tmp.split(",");
			List<Integer> layer = new ArrayList<>();
			layer.add(Integer.parseInt(nums[0]));
			layer.add(Integer.parseInt(nums[1]));
			res.add(layer);
		}
		return res;
	}

	public static void main(String[] args) {
		CountAdjCountries c = new CountAdjCountries();
		int[][] matrix = { { 1, 1, 2, 3 }, { 1, 2, 2, 2 }, { 1, 3, 3, 3 } };
		List<List<Integer>> res = c.countCountries(matrix);
		System.out.println(res);
	}
}
