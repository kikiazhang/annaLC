package tags.math;

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
		int cur = 0;// 最后一个数
		int sum = 0;// 这个level的和
		for (int i = 0; i < chars.length; i++) {
			if (Character.isDigit(chars[i])) {// 处理大于10的数（123）
				cur = cur * 10 + (chars[i] - '0');
			} else if (chars[i] == '+' || chars[i] == '-') {
				// 要改变符号了，先把cur算入sum
				sum += sign * cur;
				cur = 0;
				sign = chars[i] == '+' ? 1 : -1;
			} else if (chars[i] == '(') {
				// 要改变level了，记录sum和sign （2-（。。。））
				stack.push(sum);
				stack.push(sign);
				sum = 0;
				sign = 1;
			} else if (chars[i] == ')') {
				// 这一个level结束，算出这个level的sum
				sum += sign * cur;
				sum *= stack.pop();// sign
				sum += stack.pop();// sum
				cur = 0;
			}
		}
		return sum + (sign * cur);
	}
}
