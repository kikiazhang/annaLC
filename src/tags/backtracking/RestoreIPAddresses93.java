package tags.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s containing only digits. Return all possible valid IP
 * addresses that can be obtained from s. You can return them in any order.
 * 
 * A valid IP address consists of exactly four integers, each integer is between
 * 0 and 255, separated by single points and cannot have leading zeros. For
 * example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and
 * "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "25525511135" Output: ["255.255.11.135","255.255.111.35"] Example
 * 2:
 * 
 * Input: s = "0000" Output: ["0.0.0.0"] Example 3:
 * 
 * Input: s = "1111" Output: ["1.1.1.1"] Example 4:
 * 
 * Input: s = "010010" Output: ["0.10.0.10","0.100.1.0"]
 */
public class RestoreIPAddresses93 {
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<>();
		StringBuilder valid = new StringBuilder();
		backtrack(0, 0, valid, res, s);// index，number of substring， cur str， res， s
		return res;
	}

	private static void backtrack(int index, int times, StringBuilder valid, List<String> res, String s) {
		if (times == 4) {// 4段
			if (index == s.length()) {
				res.add(valid.toString());
			}
			return;
		}
		for (int i = 0; i < 3 && i + index < s.length(); i++) {// 每一个string最长3位数&&总长度小于len
			String value = s.substring(index, index + i + 1);
			if ((value.length() > 1 && value.startsWith("0")) || Integer.parseInt(value) >= 256) {// 排除0开始的23位数||大于256
				break;
			}
			if (index + i != s.length() - 1) {
				value += '.';// 不是最后一位，加。
			}
			valid.append(value);
			backtrack(index + i + 1, times + 1, valid, res, s);// backtrack
			valid.delete(valid.length() - value.length(), valid.length());// 去掉之前加入的
		}
	}

	// backtrack
	public List<String> restoreIpAddresses2(String s) {
		List<String> res = new ArrayList<>();
		if (s == null || s.length() == 0)
			return res;
		if (s.length() < 4 || s.length() > 12)
			return res;

		helper(s, 0, res, 0, "");
		return res;
	}

	private void helper(String s, int st, List<String> res, int len, String cur) {
		if (len > 4)
			return;
		if (st == s.length() && cur.length() == s.length() + 3 && len == 4) {
			if (!res.contains(cur))
				res.add(cur);
			return;
		}

		for (int i = st; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				if (st == 0) {
					helper(s, i + 1, res, len + 1, "0");
				} else {
					helper(s, i + 1, res, len + 1, cur + "." + "0");
				}
			} else {
				for (int j = i + 1; j <= s.length(); j++) {
					if (Integer.valueOf(s.substring(i, j)) > 255) {
						break;
					} else {
						if (st == 0) {
							helper(s, j, res, len + 1, s.substring(i, j));
						} else {
							helper(s, j, res, len + 1, cur + "." + s.substring(i, j));
						}
					}
				}
			}
		}
	}
}
