package tags.math;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
 * and M.
 * 
 * Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000 Given an integer, convert
 * it to a roman numeral. Input is guaranteed to be within the range from 1 to
 * 3999. 3 "III" 3999 "MMMCMXCIX"
 */
public class IntegertoRoman12 {
	public String intToRoman(int num) {
		String rst = "";
		if (num <= 0 || num > 3999) {
			return rst;
		}
		int[] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuilder sb = new StringBuilder();
		int digit = 0;
		while (num > 0) {
			int times = num / nums[digit];
			num -= nums[digit] * times;
			while (times > 0) {
				sb.append(symbols[digit]);
				times--;
			}
			digit++;
		}
		return sb.toString();
	}
}
