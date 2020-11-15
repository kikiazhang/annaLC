package ms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlaneSeatReservation {

	/**
	 * ABC DEFG HJK
	 * 
	 * some of the seats are already reserved. You need to allocate seats for as
	 * many four-person families as possible. They can only be seated as "DEFG" or
	 * "BC DE" or "FG HJ"
	 * 
	 * N=2, means: 2 numbers of rows S = "1A 2F 1C" means: seated position
	 */
	public static int solution(int n, String s) {
		if (n <= 0)
			return 0;

		int count = 0;
		String[] seated = s.split(" ");
		Map<Integer, Set<Character>> map = new HashMap<>();
		for (String str : seated) {
			int i = str.charAt(0) - '0';
			if (!map.containsKey(i)) {
				map.put(i, new HashSet<Character>());
			}
			map.get(i).add(str.charAt(1));
		}
		for (int i = 0; i < n; i++) {
			if (!map.containsKey(i)) {
				count += 2;
				continue;
			}
			Set<Character> set = map.get(i);
			if (set.contains('E') && set.contains('F'))
				continue;
			if (!set.contains('B') && !set.contains('C') && !set.contains('D') && !set.contains('E')) {
				count++;
				set.addAll(Arrays.asList('B', 'C', 'D', 'E'));
			}
			if (!set.contains('D') && !set.contains('E') && !set.contains('F') && !set.contains('G')) {
				count++;
				set.addAll(Arrays.asList('D', 'E', 'F', 'G'));
			}
			if (!set.contains('F') && !set.contains('G') && !set.contains('H') && !set.contains('J')) {
				count++;
				set.addAll(Arrays.asList('F', 'G', 'H', 'J'));
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int n = 3;
		String s = "1A 2F 1C";
		System.out.println(solution(n, s));
	}
}
