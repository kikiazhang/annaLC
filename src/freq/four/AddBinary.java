package freq.four;

public class AddBinary {

	/**
	 * Example 1:
	 * 
	 * Input: a = "11", b = "1" Output: "100" 
	 * 
	 * Example 2:
	 * 
	 * Input: a = "1010", b = "1011" Output: "10101"
	 */
	public String addBinary(String a, String b) {
		if (a == null || a.length() == 0)
			return b;
		if (b == null || b.length() == 0)
			return a;

		StringBuilder sb = new StringBuilder();
		int i = a.length() - 1, j = b.length() - 1;
		int carry = 0;
		while (i >= 0 || j >= 0 || carry != 0) {
			int num = (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0) + carry;
			sb.insert(0, num % 2);
			carry = num / 2;
			i--;
			j--;
		}
		return sb.toString();
	}
}
