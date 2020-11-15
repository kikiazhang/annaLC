package tags.stack;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. Note that k is guaranteed
 * to be a positive integer.
 * 
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc.
 * 
 * Furthermore, you may assume that the original data does not contain any
 * digits and that digits are only for those repeat numbers, k. For example,
 * there won't be input like 3a or 2[4].
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "3[a]2[bc]" Output: "aaabcbc" Example 2:
 * 
 * Input: s = "3[a2[c]]" Output: "accaccacc" Example 3:
 * 
 * Input: s = "2[abc]3[cd]ef" Output: "abcabccdcdcdef" Example 4:
 * 
 * Input: s = "abc3[cd]xyz" Output: "abccdcdcdxyz"
 */
public class DecodeString394 {
	// o(k_max*n)
	public String decodeString1(String s) {
		if (s.length() == 0)
			return "";
		Stack<String> st = new Stack<>();//2[a; not include ], and make sure 2 is always with [
		int num = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9')
				num = num * 10 + c - '0';
			else if (c == '[') {
				st.push(num + "");//repeat number
				st.push("["); // use [ as a marker so we don't have to check whether an item is a number or
								// not
				num = 0;
			} else if (c == ']') {//write the repeated string inside the []
				String str = "";
				// keep pop until meet '['
				while (st.peek() != "[") {
					str = st.pop() + str;
				}
				st.pop(); // pop '['
				int repeat = Integer.valueOf(st.pop());
				StringBuilder sb = new StringBuilder();
				for (int k = 0; k < repeat; k++) {
					sb.append(str);
				}
				st.push(sb.toString());
			} else
				st.push(c + "");//save char
		}

		String ans = "";
		while (!st.isEmpty())
			ans = st.pop() + ans;
		return ans;
	}

	public String decodeString(String s) {
		if (s == null || s.length() == 0)
			return s;

		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int count = 0;
		int i = 0;
		while (i < s.length()) {
			if (s.charAt(i) == '[') {
				stack.push(sb.toString());
				stack.push(count + "");
				stack.push("[");
				sb = new StringBuilder();
				count = 0;
				i++;
			} else if (s.charAt(i) == ']') {
				stack.pop();
				count = Integer.valueOf(stack.pop());

				StringBuilder tmp = new StringBuilder();
				for (int c = 0; c < count; c++) {
					tmp.append(sb.toString());
				}
				sb = tmp;
				while (!stack.isEmpty() && !stack.peek().equals("[")) {
					sb.insert(0, stack.pop());
				}
				i++;
			} else if (Character.isDigit(s.charAt(i))) {
				int st = i;
				while (i < s.length() && Character.isDigit(s.charAt(i))) {
					i++;
				}
				count = Integer.valueOf(s.substring(st, i));
			} else {
				int st = i;
				while (i < s.length() && Character.isLetter(s.charAt(i))) {
					i++;
				}
				sb.append(s.substring(st, i));
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		DecodeString394 d = new DecodeString394();
		String input = "3[a]2[bc]";
		System.out.print(d.decodeString(input));
	}
}
