package ms;

import java.util.ArrayList;
import java.util.List;

public class MinSwapsGroupRedBalls {

	/**
	 * N balls positioned in a row. either white or red. In one move we can swap two
	 * adjacent balls. we want to arrange all the red balls into a consistent
	 * segment.
	 * 
	 * S = "WRRWWR" return 2 "WRRRWW" S = "WWRWWWRWR" return 4 "WWWWWRRRW" S = "WWW"
	 * return 0
	 * 
	 */
	public int solution(String s) {
		if (s == null || s.length() == 0)
			return -1;

		List<Integer> reds = getRedIndex(s);
		int mid = reds.size() / 2;
		int min = 0;
		for (int i = 0; i < reds.size(); i++) {
			// number of swaps = dist to mid - (number of R's between them)
			min += Math.abs(reds.get(mid) - reds.get(i)) - Math.abs(mid - i);
		}

		return min;

	}

	private List<Integer> getRedIndex(String s) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'R') {
				res.add(i);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		MinSwapsGroupRedBalls m = new MinSwapsGroupRedBalls();

		String s1 = "WRRWWR";
		String s2 = "WWRWWWRWR";
		String s3 = "WWW";

		System.out.println(m.solution(s1));
		System.out.println(m.solution(s2));
		System.out.println(m.solution(s3));
	}
}
