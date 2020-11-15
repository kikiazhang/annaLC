package tags.unionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * Example:
 * 
 * Input: [100, 4, 200, 1, 3, 2] Output: 4 Explanation: The longest consecutive
 * elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence128 {
	// unionFind
	public int longestConsecutive(int[] nums) {
		UF uf = new UF(nums.length);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // <value,index>
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				continue;
			}
			map.put(nums[i], i);
			if (map.containsKey(nums[i] + 1)) {
				uf.union(i, map.get(nums[i] + 1));
			}
			if (map.containsKey(nums[i] - 1)) {
				uf.union(i, map.get(nums[i] - 1));
			}
		}
		return uf.maxUnion();
	}

	// sorting o(nlogn)
	public int longestConsecutive2(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		Arrays.sort(nums);

		int longestStreak = 1;
		int currentStreak = 1;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1]) {
				if (nums[i] == nums[i - 1] + 1) {// 递增
					currentStreak += 1;
				} else {
					// 递增结束，更新max len
					longestStreak = Math.max(longestStreak, currentStreak);
					currentStreak = 1;
				}
			}
		}

		return Math.max(longestStreak, currentStreak);
	}

	// hasSet o(n)； n+n == n
	public int longestConsecutive3(int[] nums) {
		Set<Integer> num_set = new HashSet<Integer>();
		for (int num : nums) {
			num_set.add(num);
		}

		int longestStreak = 0;

		for (int num : num_set) {
			if (!num_set.contains(num - 1)) {
				int currentNum = num;
				int currentStreak = 1;
				// 找递增，更新cur num，cur len
				while (num_set.contains(currentNum + 1)) {
					currentNum += 1;
					currentStreak += 1;
				}
				// 更新max len
				longestStreak = Math.max(longestStreak, currentStreak);
			}
		}

		return longestStreak;
	}
}

class UF {
	private int[] list;// parent list

	public UF(int n) {
		list = new int[n];
		for (int i = 0; i < n; i++) {
			list[i] = i;// 初始值root是自己
		}
	}

	private int root(int i) {
		while (i != list[i]) {
			list[i] = list[list[i]];
			i = list[i];
		}
		return i;
	}

	public boolean connected(int i, int j) {// 是否相连
		return root(i) == root(j);
	}

	public void union(int p, int q) {// 不在乎谁rank大，直接p连上q，直到保证他们root现在相同就行
		int i = root(p);
		int j = root(q);
		list[i] = j;
	}

	// returns the maxium size of union
	public int maxUnion() { // O(n)
		int[] count = new int[list.length];// 计算root i出现的次数
		int max = 0;
		for (int i = 0; i < list.length; i++) {
			count[root(i)]++;
			max = Math.max(max, count[root(i)]);
		}
		return max;
	}
}
