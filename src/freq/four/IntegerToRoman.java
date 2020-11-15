package freq.four;

public class IntegerToRoman {

	/**
	 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
	 * and M.
	 * 
	 * Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000
	 */
	public String intToRoman(int num) {
		String M[] = { "", "M", "MM", "MMM" };// 千位
		String C[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };// 百位
		String X[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };// 十位
		String I[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };// 个位
		return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
	}

}
