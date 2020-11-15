package ms;

public class MaxInsertsObtainStringWithout3ConsecutiveA {

	/**
	 * how many 'a' can be inserted into str without 3 consecutive 'a' S="aabab",
	 * return 3 "aabaabaa" S="aa" return 0 S="dog" return 8
	 * 
	 */
	public int solution(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int max = 0;
		int count = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != 'a') {
				max += 2 - count;
				count = 0;
			} else {
				count++;
				if (count > 2)
					return -1;
			}

		}
		max += 2 - count;
		return max;
	}

	public static void main(String[] args) {
		MaxInsertsObtainStringWithout3ConsecutiveA m = new MaxInsertsObtainStringWithout3ConsecutiveA();

		String s1 = "aabab";
		String s2 = "aa";
		String s3 = "dog";
		String s4 = "baaa";
		String s5 = "aaba";
		System.out.println(m.solution(s1));
		System.out.println(m.solution(s2));
		System.out.println(m.solution(s3));
		System.out.println(m.solution(s4));
		System.out.println(m.solution(s5));
	}
}
