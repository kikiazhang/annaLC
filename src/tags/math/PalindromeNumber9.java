package tags.math;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when
 * it reads the same backward as forward.
 * 
 * Example 1:
 * 
 * Input: 121 Output: true Example 2:
 * 
 * Input: -121 Output: false Explanation: From left to right, it reads -121.
 * From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * 
 * Input: 10 Output: false Explanation: Reads 01 from right to left. Therefore
 * it is not a palindrome.
 */
public class PalindromeNumber9 {
	public boolean isPalindrome(int x) {
		String s = String.valueOf(x);
		int i = 0, j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	// not allow using to string
	public boolean isPalindrome2(int x) {
		if (x < 0)
			return false;
		int divider = 1;
		int xCopy = x;
		while (xCopy >= 10) {// 1021 find 1000
			xCopy = xCopy / 10;
			divider = divider * 10;
		}

		while (x != 0) {
			int head = x / divider;
			int tail = x % 10;
			if (head != tail) {
				return false;
			}
			x = (x % divider) / 10;// 2
			divider = divider / 100;// 100, next time, head = 0, tail = 2, false

		}
		return true;
	}
}
