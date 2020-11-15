package ms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcatenatedStringLengthWithUniqueCharacters {

	/**
	 * Given an Array A consisting of N Strings, calculate the length of the longest
	 * string S such that:
	 * 
	 * S is a concatenation of some of the Strings from A. every letter in S is
	 * different. Example - A = ["co","dil","ity"] , function should return 5,
	 * resulting string S could be codil , dilco, coity,ityco A =
	 * ["abc","kkk","def","csv"] , returns 6 , resulting Strings S could be abcdef ,
	 * defabc, defcsv , csvdef A = ["abc","ade","akl"] , return 0 , impossible to
	 * concatenate as letters wont be unique.
	 * 
	 * N is [1..8] ; A consists of lowercase English letters ; sum of length of
	 * strings in A does not exceed 100.
	 * 
	 * Related problems:
	 * 
	 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
	 * 
	 */

	public int solution(String[] arr) {
		if (arr == null || arr.length == 0)
			return 0;

		int max = 0;
		int min = 0;
		List<String> res = new ArrayList<>();
		for (String s : arr) {
			if (!isUnique(s))
				continue;
			if(res.isEmpty()) {
				res.add(s);
			} else {
				List<String> resList = new ArrayList<>();
				for (String str : res) {
					String tmp = s + str;
					if (isUnique(tmp))
						resList.add(tmp);
				}
				res.addAll(resList);
			}
			
		}
		res.removeAll(Arrays.asList(arr));
		for (String s : res) {
			max = Math.max(max, s.length());
		}
		return max;
	}

	private boolean isUnique(String s) {
		if (s.length() > 26)
			return false;

		boolean[] used = new boolean[26];
		for (char c : s.toCharArray()) {
			if (used[c - 'a']) {
				return false;
			} else {
				used[c - 'a'] = true;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		ConcatenatedStringLengthWithUniqueCharacters m = new ConcatenatedStringLengthWithUniqueCharacters();

		String[] arr1 = { "co", "dil", "ity" };
		String[] arr2 = { "abc", "kkk", "def", "csv" };
		String[] arr3 = { "abc", "ade", "akl" };

		System.out.println(m.solution(arr1));
		System.out.println(m.solution(arr2));
		System.out.println(m.solution(arr3));
	}
}
