package tags.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This problem is an interactive problem new to the LeetCode platform.
 * 
 * We are given a word list of unique words, each word is 6 letters long, and
 * one word in this list is chosen as secret.
 * 
 * You may call master.guess(word) to guess a word. The guessed word should have
 * type string and must be from the original list with 6 lowercase letters.
 * 
 * This function returns an integer type, representing the number of exact
 * matches (value and position) of your guess to the secret word. Also, if your
 * guess is not in the given wordlist, it will return -1 instead.
 * 
 * For each test case, you have 10 guesses to guess the word. At the end of any
 * number of calls, if you have made 10 or less calls to master.guess and at
 * least one of these guesses was the secret, you pass the testcase.
 * 
 * Besides the example test case below, there will be 5 additional test cases,
 * each with 100 words in the word list. The letters of each word in those
 * testcases were chosen independently at random from 'a' to 'z', such that
 * every word in the given word lists is unique.
 * 
 * Example 1: Input: secret = "acckzz", wordlist =
 * ["acckzz","ccbazz","eiowzz","abcczz"]
 * 
 * Explanation:
 * 
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6
 * matches. master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 * 
 * We made 5 calls to master.guess and one of them was the secret, so we pass
 * the test case.
 */
public class GuesstheWord843 {
	class Master {
		public int guess(String word) {
			return 0;
		}
	}

	public void findSecretWord(String[] wordlist, Master master) {
		// 每个词6位数， 10次机会
		for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
			HashMap<String, Integer> count = new HashMap<>();// guess word， match 0 的词的个数
			for (String w1 : wordlist)
				for (String w2 : wordlist)
					if (match(w1, w2) == 0)// 两个词完全不相同， map保存
						count.put(w1, count.getOrDefault(w1, 0) + 1);
			String guess = "";
			int min0 = 100;
			// match 0 次数越小，越早成为guess，因为跟他有关的word多
			for (String w : wordlist)
				// 找到word的值小于min0
				if (count.getOrDefault(w, 0) < min0) {
					guess = w;
					min0 = count.getOrDefault(w, 0);
				}
			x = master.guess(guess);
			List<String> wordlist2 = new ArrayList<String>();
			for (String w : wordlist)
				// secret只能是跟guess match x的word，把它们找出来，变成新的list
				if (match(guess, w) == x)
					wordlist2.add(w);
			wordlist = wordlist2.toArray(new String[0]);
		}
	}

	public int match(String a, String b) {
		int matches = 0;
		for (int i = 0; i < a.length(); ++i)
			if (a.charAt(i) == b.charAt(i))
				matches++;
		return matches;
	}
}
