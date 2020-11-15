package ms;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MinDeletionsMakeFrequencyEachLetterUnique {

	/**
	 * Given a string s consisting of n lowercase letters, you have to delete the
	 * minimum number of characters from s so that every letter in s appears a
	 * unique number of times. We only care about the occurrences of letters that
	 * appear at least once in result.
	 * 
	 * Example 1:
	 * 
	 * Input: "eeeeffff" Output: 1 Explanation: We can delete one occurence of 'e'
	 * or one occurence of 'f'. Then one letter will occur four times and the other
	 * three times. Example 2:
	 * 
	 * Input: "aabbffddeaee" Output: 6 Explanation: For example, we can delete all
	 * occurences of 'e' and 'f' and one occurence of 'd' to obtain the word
	 * "aabbda". Note that both 'e' and 'f' will occur zero times in the new word,
	 * but that's fine, since we only care about the letter that appear at least
	 * once. Example 3:
	 * 
	 * Input: "llll" Output: 0 Explanation: There is no need to delete any
	 * character. Example 4:
	 * 
	 * Input: "example" Output: 4
	 * 
	 */

	public int minDeletion(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int min = 0;
		Map<Character, Integer> map = new HashMap<>();

		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int[] arr = new int[s.length() + 1];
		for (Entry<Character, Integer> e : map.entrySet()) {
			if (arr[e.getValue()] != 0) {
				int i = e.getValue();
				while (i > 0) {
					if (arr[i] != 0) {
						min++;
						i--;
					} else {
						arr[i] = 1;
						break;
					}
				}

			} else {
				arr[e.getValue()] = 1;
			}
		}

		/**
		 * 
		 * List<Integer> freq = new ArrayList<>(countMap.values());
		 * Collections.sort(freq, Collections.reverseOrder());
		 * 
		 * int deleted = 0; 
		 * Set<Integer> countSet = new HashSet<>();
		 * 
		 * for(int n : freq) { 
		 * if(!countSet.contains(n)) { countSet.add(n); continue; }
		 * while(countSet.contains(n)) { n--; deleted++; } 
		 * if(n != 0) { countSet.add(n); } 
		 * }
		 * 
		 */
		return min;
	}

	public static void main(String[] args) {
		MinDeletionsMakeFrequencyEachLetterUnique m = new MinDeletionsMakeFrequencyEachLetterUnique();

		String s1 = "eeeeffff";
		String s2 = "aabbffddeaee";
		String s3 = "llll";
		String s4 = "example";

		System.out.println(m.minDeletion(s1));
		System.out.println(m.minDeletion(s2));
		System.out.println(m.minDeletion(s3));
		System.out.println(m.minDeletion(s4));
	}
}
