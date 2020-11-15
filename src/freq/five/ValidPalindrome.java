package freq.five;

public class ValidPalindrome {

	/**
	 * Example 1:
	 * 
	 * Input: "A man, a plan, a canal: Panama" Output: true Example 2:
	 * 
	 * Input: "race a car" Output: false
	 * 
	 * ".," true "0P" false
	 */
	public boolean isPalindrome(String s) {
		if (s == null || s.length() == 0)
			return true;

		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			while (i < s.length() && !Character.isLetterOrDigit(s.charAt(i))) {
				i++;
			}
			while (j >= 0 && !Character.isLetterOrDigit(s.charAt(j))) {
				j--;
			}
			if (i < s.length() && j >= 0 && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
				return false;
			} else {
				i++;
				j--;
			}
		}
		return true;
	}
}
