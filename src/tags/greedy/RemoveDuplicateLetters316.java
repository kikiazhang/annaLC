package tags.greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Given a string which contains only lowercase letters, remove duplicate
 * letters so that every letter appears once and only once. You must make sure
 * your result is the smallest in lexicographical order among all possible
 * results.
 * 
 * Example 1:
 * 
 * Input: "bcabc" Output: "abc" Example 2:
 * 
 * Input: "cbacdcbc" Output: "acdb"
 */
public class RemoveDuplicateLetters316 {
	// greed + stack o(n)
	public String removeDuplicateLetters(String s) {
		Stack<Character> stack = new Stack<>();// 里面顺序就是从小到大顺序
		HashSet<Character> seen = new HashSet<>();
		// 最后出现index
		HashMap<Character, Integer> last_occ = new HashMap<>();
		for (int i = 0; i < s.length(); i++)
			last_occ.put(s.charAt(i), i);

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!seen.contains(c)) {// 首次出现c
				// stack peek的比c大 && 这个数后面也有，说明可以去掉peek
				while (!stack.isEmpty() && c < stack.peek() && last_occ.get(stack.peek()) > i) {
					seen.remove(stack.pop());
				}
				// 加入c
				seen.add(c);
				stack.push(c);
			}

		}
		StringBuilder sb = new StringBuilder();
		for (Character c : stack) {
			sb.append(c.charValue());
		}
		return sb.toString();
	}

	// o(n)
	public String removeDuplicateLetters2(String s) {
		int[] cnt = new int[26];// 计数
		for (int i = 0; i < s.length(); i++) {
			cnt[s.charAt(i) - 'a']++;
		}
		int pos = 0;// 开始position
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < s.charAt(pos))// 能组成更小的开始，更新pos
				pos = i;
			if (--cnt[s.charAt(i) - 'a'] == 0)// 直到有i去干净了，结束寻找pos，说明到i这里必须是pos开始了
				break;
		}
		// pos开始 + 后面接recursive找pos+1开始并且去掉所有之后的pos位
		return s.length() == 0 ? ""
				: s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
	}
}
