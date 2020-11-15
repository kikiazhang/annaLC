package tags.hashTable;

/**
 * n an alien language, surprisingly they also use english lowercase letters,
 * but possibly in a different order. The order of the alphabet is some
 * permutation of lowercase letters.
 * 
 * Given a sequence of words written in the alien language, and the order of the
 * alphabet, return true if and only if the given words are sorted
 * lexicographicaly in this alien language.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true Explanation: As 'h' comes before 'l' in this language, then the
 * sequence is sorted. Example 2:
 * 
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false Explanation: As 'd' comes after 'l' in this language, then
 * words[0] > words[1], hence the sequence is unsorted.
 */
public class VerifyingAlienDictionary953 {
	// Time: O(mn), space: O(1).
	public boolean isAlienSorted(String[] words, String order) {
		if (words == null || words.length <= 1)
			return true;
		// char出现index
		int[] index = new int[26];
		for (int i = 0; i < order.length(); i++) {
			index[order.charAt(i) - 'a'] = i;
		}
		// else 返回 search
		// 只比较相邻的
		search: for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];
			// 比最小长度
			for (int k = 0; k < Math.min(word1.length(), word2.length()); k++) {
				if (word1.charAt(k) != word2.charAt(k)) {
					// 第一个不同，word1 char出现靠后就不对
					if (index[word1.charAt(k) - 'a'] > index[word2.charAt(k) - 'a']) {
						return false;
					}
					continue search;
				}
			}
			// word1长度长，不对
			if (word1.length() > word2.length())
				return false;
		}
		return true;
	}
}
