package tags.dfs.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and
 * ).
 * 
 * Example 1:
 * 
 * Input: "()())()" Output: ["()()()", "(())()"] Example 2:
 * 
 * Input: "(a)())()" Output: ["(a)()()", "(a())()"] Example 3:
 * 
 * Input: ")(" Output: [""]
 */
public class RemoveInvalidParentheses301 {
	// bfs
	public List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<>();

		// sanity check
		if (s == null)
			return res;

		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add(s);
		visited.add(s);
		boolean found = false;

		while (!queue.isEmpty()) {
			s = queue.poll();
			if (isValid(s)) {
				// found an answer, add to the result
				res.add(s);
				found = true;
			}

			if (found)
				continue;// already use the min remove to find the res, so no need to continue for this s

			// generate all possible states, remove one char
			for (int i = 0; i < s.length(); i++) {
				// we only try to remove left or right paren
				if (s.charAt(i) != '(' && s.charAt(i) != ')')
					continue;
				String t = s.substring(0, i) + s.substring(i + 1);// remove i
				if (!visited.contains(t)) {
					// for each state, if it's not visited, add it to the queue
					queue.add(t);
					visited.add(t);
				}
			}
		}
		return res;
	}

	// helper function checks if string s contains valid parantheses
	boolean isValid(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(')
				count++;
			if (c == ')' && count-- == 0)
				return false;
		}
		return count == 0;
	}

	// dfs
	public List<String> removeInvalidParentheses2(String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
		for (int stack = 0, i = last_i; i < s.length(); ++i) {
			if (s.charAt(i) == par[0])
				stack++;
			if (s.charAt(i) == par[1])
				stack--;
			if (stack >= 0)
				continue;
			for (int j = last_j; j <= i; ++j)
				if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
					remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
			return;
		}
		String reversed = new StringBuilder(s).reverse().toString();
		if (par[0] == '(') // finished left to right
			remove(reversed, ans, 0, 0, new char[] { ')', '(' });
		else // finished right to left
			ans.add(reversed);
	}
}
