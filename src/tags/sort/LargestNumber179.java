package tags.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * Example 1:
 * 
 * Input: [10,2] Output: "210" Example 2:
 * 
 * Input: [3,30,34,5,9] Output: "9534330"
 */
public class LargestNumber179 {
	// 变成string然后sort
	public String largestNumber(int[] nums) {
		String[] asStrs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			asStrs[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(asStrs, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				String s1 = a + b;
				String s2 = b + a;
				return s2.compareTo(s1);
			}
		});
		// 0开始，有误
		if (asStrs[0].equals("0")) {
			return "0";
		}
		StringBuilder sb = new StringBuilder();
		for (String str : asStrs) {
			sb.append(str);
		}
		return sb.toString();
	}
}
