package ms;

import java.util.HashSet;
import java.util.Set;

public class MinSwapsMakePalindrome {

	/**
	 * Given a string, what is the minimum number of adjacent swaps required to
	 * convert a string into a palindrome. If not possible, return -1.
	 * 
	 * Practice online: https://www.codechef.com/problems/ENCD12
	 * 
	 * 
	 * Input: "mamad" Output: 3 Example 2:
	 * 
	 * Input: "asflkj" Output: -1 Example 3:
	 * 
	 * Input: "aabb" Output: 2
	 * 
	 * Input: "ntiin" Output: 1 Explanation: swap 't' with 'i' => "nitin"
	 */

	// seems to be O(n^2)
	public int minSwapPalindrome(String s) {
		if (s == null || s.length() == 0)
			return -1;

		if (!isPalindrome(s))
			return -1;

		int min = 0;
		char[] arr = s.toCharArray();
		int i = 0;
		int j = arr.length - 1;
		int k = j;
		// two point
		while (i < j) {
			k = j;
			while (arr[i] != arr[k] && k > i) {// find the different index
				k--;
			}
			if (arr[i] == arr[k] && i != k) {// if k moved to reach here, count the steps
				while (k < j) {
					swap(arr, k, k + 1);
					k++;
					min++;
				}
				i++;
				j--;
			} else {// abbcadd, not reach the middle, but can't find duplicated i, move i to the
					// right
				swap(arr, i, i + 1);
				min++;
			}
		}

		return min;
	}

	private void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private boolean isPalindrome(String s) {
		Set<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			if (set.contains(c)) {
				set.remove(c);
			} else {
				set.add(c);
			}
		}
		return set.size() < 2;
	}
}
