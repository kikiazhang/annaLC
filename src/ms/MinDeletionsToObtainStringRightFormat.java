package ms;

public class MinDeletionsToObtainStringRightFormat {

	/**
	 * obtain a string in the format "AAA....BBBB" by deleting some letters from s
	 * 
	 * S = "BAAABAB" return 2 "AAABB" S = "BBABAA" return 3 "AAA" / "BBB" S =
	 * "AAABBB" return 0
	 * 
	 */
	public int solution(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int cntA = 0, cntB = 0, cntRemove = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'A') {
				cntA++;
				// 出现B之后出现的A。可以记为删除
				if (cntB > cntRemove)
					cntRemove++;
			} else {
				cntB++;
				// 出现A之前的B。可以记为删除
				if (cntA == 0)
					cntRemove++;
			}
		}
		// 删掉A 删掉B 删掉不符合的AB
		return Math.min(cntA, Math.min(cntB, cntRemove));
	}

	public static void main(String[] args) {
		MinDeletionsToObtainStringRightFormat m = new MinDeletionsToObtainStringRightFormat();

		String s1 = "BAAABAB";
		String s2 = "BBABAA";
		String s3 = "AABBBB";
		System.out.println(m.solution(s1));
		System.out.println(m.solution(s2));
		System.out.println(m.solution(s3));
	}
}
