package tags.stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces .
 * 
 * Example 1:
 * 
 * Input: "1 + 1" Output: 2 Example 2:
 * 
 * Input: " 2-1 + 2 " Output: 3 Example 3:
 * 
 * Input: "(1+(4+5+2)-3)+(6+8)" Output: 23
 */
public class BasicCalculator224 {
	// o(n) o(n)
	public int calculate(String s) {
		if (s == null)
			return 0;
		char[] chars = s.toCharArray();
		Stack<Integer> stack = new Stack<>();// 放的是sum，sign
		int sign = 1;// 符号
		int count = 0;
		int i = 0;
		while (i < chars.length) {
			if (chars[i] == '+' || chars[i] == '-') {
				sign = chars[i] == '+' ? 1 : -1;
			} else if (Character.isDigit(chars[i])) {
				int num = 0;
				while (i < chars.length && Character.isDigit(chars[i])) {
					num = num * 10 + chars[i] - '0';
					i++;
				}
				count += sign * num;
				i--;
			} else if (chars[i] == '(') {
				stack.push(count);
				stack.push(sign);
				count = 0;
				sign = 1;
			} else if (chars[i] == ')') {
				sign = stack.pop();
				count = count * sign + stack.pop();
			}
			i++;
		}
		while (!stack.isEmpty()) {
			count += stack.pop();
		}
		return count;
	}
}
