package freq.five;

import java.util.Stack;

public class ValidParentheses {
	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

		An input string is valid if:
		
		Open brackets must be closed by the same type of brackets.
		Open brackets must be closed in the correct order.
		Note that an empty string is also considered valid.
	 * */

	public boolean isValid(String s) {
        if(s == null || s.length() == 0) return true;
        
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while(i < s.length()){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            } else if(s.charAt(i) == ')'){
                if(stack.isEmpty() || stack.pop() != '(') return false;
            } else if(s.charAt(i) == ']'){
                if(stack.isEmpty() || stack.pop() != '[') return false;
            } else if(s.charAt(i) == '}'){
                if(stack.isEmpty() || stack.pop() != '{') return false;
            } else {
                return false;
            }
            i++;
        }
        return stack.isEmpty();
    }
}
