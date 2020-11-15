package tags.divideConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 * 
 * Example 1:
 * 
 * Input: num = "123", target = 6 Output: ["1+2+3", "1*2*3"] Example 2:
 * 
 * Input: num = "232", target = 8 Output: ["2*3+2", "2+3*2"]
 */
public class ExpressionAddOperators282 {
	// dfs o(n^4 * N)
	public List<String> addOperators(String num, int target) {
		List<String> rst = new ArrayList<String>();
		if (num == null || num.length() == 0)
			return rst;
		helper(rst, "", num, target, 0, 0, 0);
		return rst;
	}

	// path = current combination
	// num = original num
	// pos = start index
	// eval = sum of the current combination
	// multed = previous adding num，当*时，需要把之前加上地拿回来做乘法
	public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed) {
		if (pos == num.length()) {
			if (target == eval)
				rst.add(path);
			return;
		}
		for (int i = pos; i < num.length(); i++) {
			// pos不能从0开始
			if (i != pos && num.charAt(pos) == '0')
				break;
			// 当前的num（pos到i）
			long cur = Long.parseLong(num.substring(pos, i + 1));
			if (pos == 0) {// 第一个数只能是+
				helper(rst, path + cur, num, target, i + 1, cur, cur);
			} else {
				// +cur
				helper(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);
				// -cur
				helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
				// multed*cur
				helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
			}
		}
	}
}
