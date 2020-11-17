package doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountAllValidPickupandDeliveryOptions1359 {
	public boolean isValid(List<String> orders) {
		Set<Character> p_set = new HashSet<>();
		Set<Character> d_set = new HashSet<>();

		for (String order : orders) {
			char task_type = order.charAt(0);
			char task_num = order.charAt(1);
			if (task_type == 'P') {
				if (p_set.contains(task_num)) {
					return false;
				}
				p_set.add(task_num);

			} else if (task_type == 'D') {
				if (d_set.contains(task_num) || !p_set.contains(task_num)) {
					return false;
				}

				d_set.add(task_num);
			} else {
				return false;
			}
		}

		return p_set.size() == d_set.size();

	}

	// follow up, count all valid
	public static List<List<String>> permuteUnique(int num) {
		int[] nums = new int[2 * num];// [1,1,2,2,...]
		int identifier = 1;// PickupandDelivery index
		for (int i = 0; i < nums.length;) {
			nums[i] = identifier;
			nums[i + 1] = identifier;
			i += 2;
			++identifier;
		}

		List<List<String>> res = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return res;
		boolean[] used = new boolean[nums.length];
		List<Integer> list = new ArrayList<>();

		dfs(nums, used, list, res);
		System.out.println(res.size());
		return res;
	}

	public static void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<String>> res) {
		if (list.size() == nums.length) {
			Set<String> set = new HashSet<>();// check can insert P or D
			List<String> l = new ArrayList<>();// cur res layer
			for (int num : list) {
				if (set.add("P" + num)) {
					l.add("P" + num);
				} else {
					l.add("D" + num);
				}
			}
			res.add(l);
			return;
		}
		// duplicated value permutation
		for (int i = 0; i < nums.length; i++) {
			if (used[i])
				continue;
			if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1])// can't be the second same one
				continue;
			used[i] = true;

			list.add(nums[i]);
			dfs(nums, used, list, res);
			used[i] = false;
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {

		CountAllValidPickupandDeliveryOptions1359 obj = new CountAllValidPickupandDeliveryOptions1359();
		System.out.println(obj.isValid(Arrays.asList("P1", "P2", "D1", "D2")));
		System.out.println(obj.isValid(Arrays.asList("P1", "D1", "P2", "D2")));
		System.out.println(obj.isValid(Arrays.asList("P1", "D2", "D1", "P2")));
		System.out.println(obj.isValid(Arrays.asList("P1", "D2")));
		System.out.println(obj.isValid(Arrays.asList("P1", "P2")));
		System.out.println(obj.isValid(Arrays.asList("P1", "D1", "D1")));
		System.out.println(obj.isValid(Arrays.asList()));
		System.out.println(obj.isValid(Arrays.asList("P1", "P1", "D1")));
		System.out.println(obj.isValid(Arrays.asList("P1", "P1", "D1", "D1")));
		System.out.println(obj.isValid(Arrays.asList("P1", "D1", "P1")));
		System.out.println(obj.isValid(Arrays.asList("P1", "D1", "P1", "D1")));

		int input = 2;
		List<List<String>> res = permuteUnique(input);
		for (List<String> s : res) {
			System.out.println(Arrays.toString(s.toArray()));
		}

	}
}
