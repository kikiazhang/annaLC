package tags.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * 
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any
 * positions ) so that the resulting parentheses string is valid and return any
 * valid string.
 * 
 * Formally, a parentheses string is valid if and only if:
 * 
 * It is the empty string, contains only lowercase characters, or It can be
 * written as AB (A concatenated with B), where A and B are valid strings, or It
 * can be written as (A), where A is a valid string.
 * 
 * 
 * Example 1:
 * 
 * Input: s = "lee(t(c)o)de)" Output: "lee(t(c)o)de" Explanation: "lee(t(co)de)"
 * , "lee(t(c)ode)" would also be accepted. Example 2:
 * 
 * Input: s = "a)b(c)d" Output: "ab(c)d" Example 3:
 * 
 * Input: s = "))((" Output: "" Explanation: An empty string is also valid.
 * Example 4:
 * 
 * Input: s = "(a(b(c)d)" Output: "a(b(c)d)"
 */
public class MinimumRemoveMakeValidParentheses1249 {
	// o(n)
	public String minRemoveToMakeValid(String s) {
		if (s == null || s.length() == 0)
			return s;

		Set<Integer> indexes = new HashSet<>();// removed indexes
		Stack<Integer> stack = new Stack<>();// save (
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else if (s.charAt(i) == ')') {
				if (stack.isEmpty()) {
					indexes.add(i);
				} else {
					stack.pop();
				}
			}
		}
		// invalid (
		while (!stack.isEmpty()) {
			indexes.add(stack.pop());
		}
		// build res
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (!indexes.contains(i)) {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}

	// Two Parse String Builder o(n)
	public String minRemoveToMakeValid2(String s) {

		// Parse 1: Remove all invalid ")"
		StringBuilder sb = new StringBuilder();
		int openSeen = 0;
		int balance = 0;
		// remove all invalid ) and count ( and count ()
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				openSeen++;
				balance++;
			}
			if (c == ')') {
				if (balance == 0)
					continue;
				balance--;
			}
			sb.append(c);
		}

		// Parse 2: Remove the rightmost "("
		StringBuilder result = new StringBuilder();
		int openToKeep = openSeen - balance;// should be removed (
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c == '(') {
				openToKeep--;
				if (openToKeep < 0)
					continue;
			}
			result.append(c);
		}

		return result.toString();
	}
}
