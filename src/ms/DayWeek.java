package ms;

import java.util.HashMap;
import java.util.Map;

public class DayWeek {

	/**
	 * ("Mon","Tue","Wed","Thu","Fri","Sat","Sun") S = day od the week K = after K
	 * days e.g: S="Wed" and K=2, return "Fri"
	 */
	public String solution(String s, int k) {
		String[] week = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
		Map<String, Integer> map = new HashMap<>();// Mon, 0; Tue, 1...
		for (int i = 0; i < 7; i++) {
			map.put(week[i], i);
		}
		return week[(map.get(s) + k) % 7];
	}

	public static void main(String[] args) {
		DayWeek m = new DayWeek();

		System.out.println(m.solution("Wed", 2));
		System.out.println(m.solution("Sat", 23));
	}
}
