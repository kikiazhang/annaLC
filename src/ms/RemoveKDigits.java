package ms;

public class RemoveKDigits {

	/**
	 * Example 1:
	 * 
	 * Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove the three
	 * digits 4, 3, and 2 to form the new number 1219 which is the smallest.
	 * 
	 * 
	 * Example 2:
	 * 
	 * Input: num = "10200", k = 1 Output: "200" Explanation: Remove the leading 1
	 * and the number is 200. Note that the output must not contain leading zeroes.
	 */
	public String solution(String n, int k) {
		StringBuilder sb = new StringBuilder();
		int len = n.length();
		for (char c : n.toCharArray()) {
			while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) > c) {
				k--;
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append(c);
		}

		while (sb.length() > 0 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}

		return sb.length() == 0 ? "0" : sb.toString();
	}

	public static void main(String[] args) {
		RemoveKDigits m = new RemoveKDigits();

		String n1 = "1432219";
		System.out.println(m.solution(n1, 3));

		String n2 = "10200";
		System.out.println(m.solution(n2, 2));
	}
}
