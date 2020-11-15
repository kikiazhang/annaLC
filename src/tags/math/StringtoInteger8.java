package tags.math;

/**
 * "42" = 42, " -42 " = -42, "+42" = 42, "4193 with words" = 4193, "words and
 * 987" = 0, "-91283472332" = -2147483648, " -0012a42" = -12
 */
public class StringtoInteger8 {
	public int myAtoi(String str) {
		if (str == null || str.length() == 0)
			return 0;

		str = str.trim();
		int i = 0;
		int sign = 1;
		int res = 0;
		while (i < str.length()) {
			if (i == 0) { // fist char must be -/+/digit
				if (!Character.isDigit(str.charAt(i))) {
					if (str.charAt(i) == '-') {
						sign = -1;
					} else if (str.charAt(i) == '+') {
						sign = 1;
					} else {
						return 0;
					}
				} else {
					res = res * 10 + (str.charAt(i) - '0');
				}
			} else {
				// only count fist connect digit: 238476 until ' '/letter/special char
				if (Character.isDigit(str.charAt(i))) {
					int cur = (str.charAt(i) - '0');
					if ((sign == 1 && res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && cur > 7))
							|| (sign == -1 && res > Integer.MAX_VALUE / 10
									|| (res == Integer.MAX_VALUE / 10 && cur > 8)))
						return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
					res = res * 10 + cur;
				} else {
					return res * sign;
				}
			}
			i++;
		}
		return res * sign;
	}
}
