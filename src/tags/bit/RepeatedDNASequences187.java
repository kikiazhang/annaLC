package tags.bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule.
 * 
 * Example:
 * 
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class RepeatedDNASequences187 {
	public List<String> findRepeatedDnaSequences(String s) {
		Set<String> res = new HashSet<>();
		Set<String> set = new HashSet<>();
		for (int i = 0; i < s.length() - 9; i++) {
			String sub = s.substring(i, i + 10);
			if (set.contains(sub)) {
				res.add(sub);
			}
			set.add(sub);
		}
		List<String> list = new ArrayList<>();
		for (String str : res) {
			list.add(str);
		}
		return list;
	}

	// bit o(n-l)
	public List<String> findRepeatedDnaSequences2(String s) {
		int L = 10, n = s.length();
		if (n <= L)
			return new ArrayList<>();

		// rolling hash parameters: base a
		int a = 4, aL = (int) Math.pow(a, L);

		// convert string to array of integers
		Map<Character, Integer> toInt = new HashMap<>() {
			{
				put('A', 0);
				put('C', 1);
				put('G', 2);
				put('T', 3);
			}
		};
		int[] nums = new int[n];
		for (int i = 0; i < n; ++i)
			nums[i] = toInt.get(s.charAt(i));

		int bitmask = 0;
		Set<Integer> seen = new HashSet<>();
		Set<String> output = new HashSet<>();
		// iterate over all sequences of length L
		for (int start = 0; start < n - L + 1; ++start) {
			// compute bitmask of the current sequence in O(1) time
			if (start != 0) {
				// left shift to free the last 2 bit
				bitmask <<= 2;
				// add a new 2-bits number in the last two bits
				bitmask |= nums[start + L - 1];
				// unset first two bits: 2L-bit and (2L + 1)-bit
				bitmask &= ~(3 << 2 * L);
			}
			// compute hash of the first sequence in O(L) time
			else {
				for (int i = 0; i < L; ++i) {
					bitmask <<= 2;
					bitmask |= nums[i];
				}
			}
			// update output and hashset of seen sequences
			if (seen.contains(bitmask))
				output.add(s.substring(start, start + L));
			seen.add(bitmask);
		}
		return new ArrayList<String>(output);
	}
}
