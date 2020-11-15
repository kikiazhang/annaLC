package tags.math;

/**
 * Given two binary strings, return their sum (also a binary string).
 * 
 * The input strings are both non-empty and contains only characters 1 or 0.
 * 
 * Example 1:
 * 
 * Input: a = "11", b = "1" Output: "100" Example 2:
 * 
 * Input: a = "1010", b = "1011" Output: "10101"
 */
public class AddBinary67 {
	public String addBinary(String a, String b) {
		if (a == null || a.length() == 0)
			return b;
		if (b == null || b.length() == 0)
			return a;

		int carry = 0;
		int ia = a.length() - 1, ib = b.length() - 1;
		StringBuilder sb = new StringBuilder();
		while (ia >= 0 || ib >= 0 || carry != 0) {
			int cur = (ia < 0 ? 0 : (a.charAt(ia) - '0')) + (ib < 0 ? 0 : (b.charAt(ib) - '0')) + carry;
			sb.insert(0, cur % 2);
			carry = cur / 2;
			ia--;
			ib--;
		}
		return sb.toString();
	}
}
