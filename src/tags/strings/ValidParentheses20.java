package tags.strings;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * Open brackets must be closed by the same type of brackets. Open brackets must
 * be closed in the correct order. Note that an empty string is also considered
 * valid.
 */
public class ValidParentheses20 {
	public boolean isValid(String s) {
		if (s == null || s.length() == 0)
			return true;

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
				stack.push(s.charAt(i));
			} else if (s.charAt(i) == ')') {
				if (!stack.isEmpty() && stack.pop() == '(') {
					continue;
				} else
					return false;
			} else if (s.charAt(i) == ']') {
				if (!stack.isEmpty() && stack.pop() == '[') {
					continue;
				} else
					return false;
			} else if (s.charAt(i) == '}') {
				if (!stack.isEmpty() && stack.pop() == '{') {
					continue;
				} else
					return false;
			}
		}
		return stack.isEmpty();
	}
}
