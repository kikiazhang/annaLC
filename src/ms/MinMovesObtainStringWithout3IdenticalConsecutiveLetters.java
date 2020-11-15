package ms;

public class MinMovesObtainStringWithout3IdenticalConsecutiveLetters {

	/**
	 * String only concludes a and b, you can swap one letter for the other (a -> b,
	 * b -> a) S="baaaaa", return 1, "baabaa" S="baaabbaabbba", return 2,
	 * "bbaabbaabbaa" S="baabab", return 0
	 */

	// time: o(n) space:o(1)
	public int solution(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int moves = 0;
		for (int i = 0; i < s.length(); i++) {
			int runlength = 1;
			for (; i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1); i++) {
				runlength++;
			}
			moves += runlength / 3;// one move in each 3 consecutive letters
		}
		return moves;
	}

	public static void main(String[] args) {
		MinMovesObtainStringWithout3IdenticalConsecutiveLetters m = new MinMovesObtainStringWithout3IdenticalConsecutiveLetters();

		String s1 = "baaaaa";
		String s2 = "baaabbaabbba";
		String s3 = "baabab";
		String s4 = "abbbaaabbbaaabbaa";

		System.out.println(m.solution(s1));
		System.out.println(m.solution(s2));
		System.out.println(m.solution(s3));
		System.out.println(m.solution(s4));
	}
}
