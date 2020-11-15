package pualtrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where
 * the first element accounts[i][0] is a name, and the rest of the elements are
 * emails representing emails of the account.
 * 
 * Now, we would like to merge these accounts. Two accounts definitely belong to
 * the same person if there is some email that is common to both accounts. Note
 * that even if two accounts have the same name, they may belong to different
 * people as people could have the same name. A person can have any number of
 * accounts initially, but all of their accounts definitely have the same name.
 * 
 * After merging the accounts, return the accounts in the following format: the
 * first element of each account is the name, and the rest of the elements are
 * emails in sorted order. The accounts themselves can be returned in any order.
 * Example 1: Input: accounts = [["John", "johnsmith@mail.com",
 * "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John",
 * "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com',
 * 'johnsmith@mail.com'], ["John", "johnnybravo@mail.com"], ["Mary",
 * "mary@mail.com"]] Explanation: The first and third John's are the same person
 * as they have the common email "johnsmith@mail.com". The second John and Mary
 * are different people as none of their email addresses are used by other
 * accounts. We could return these lists in any order, for example the answer
 * [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], ['John',
 * 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would
 * still be accepted.
 */
public class AccountMerge721 {
	// o(nlogn) union find
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		DSU dsu = new DSU();
		Map<String, String> emailToName = new HashMap<String, String>();// email, name
		Map<String, Integer> emailToID = new HashMap<String, Integer>();// email, email id用来union
		int id = 0;
		for (List<String> account : accounts) {
			String name = "";
			for (String email : account) {
				if (name == "") {// first string is name
					name = email;
					continue;
				}
				emailToName.put(email, name);
				if (!emailToID.containsKey(email)) {// email not exist, give it a new id
					emailToID.put(email, id++);
				}
				dsu.union(emailToID.get(account.get(1)), emailToID.get(email));// 把相同name里，第一个email和cur
																				// email衔接（用id做union）
			}
		}

		Map<Integer, List<String>> ans = new HashMap<>();// id, all email
		for (String email : emailToName.keySet()) {
			int index = dsu.find(emailToID.get(email));// find every parent id to save in ans
			ans.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
		}
		for (List<String> component : ans.values()) {// save name to every list of email
			Collections.sort(component);// list in sorted order
			component.add(0, emailToName.get(component.get(0)));
		}
		return new ArrayList<>(ans.values());
	}

	// union find
	class DSU {
		int[] parent;

		public DSU() {// 初始，设置parent都为自己
			parent = new int[10001];
			for (int i = 0; i <= 10000; ++i)
				parent[i] = i;
		}

		public int find(int x) {// 找祖先
			if (parent[x] != x)
				parent[x] = find(parent[x]);
			return parent[x];
		}

		public void union(int x, int y) {// x联入y祖先
			parent[find(x)] = find(y);
		}
	}

	// dfs
	public List<List<String>> accountsMerge2(List<List<String>> accounts) {
		Map<String, String> emailToName = new HashMap<>();// email, name
		Map<String, ArrayList<String>> graph = new HashMap<>();// email, list of email
		for (List<String> account : accounts) {
			String name = "";
			for (String email : account) {
				if (name == "") {
					name = email;
					continue;
				}
				// email, email1
				graph.computeIfAbsent(email, x -> new ArrayList<String>()).add(account.get(1));
				// email1, list of email with same name（这样可以使有可合并的email时，可以通过email1找到他的兄弟）
				graph.computeIfAbsent(account.get(1), x -> new ArrayList<String>()).add(email);
				emailToName.put(email, name);
			}
		}

		Set<String> seen = new HashSet<>();
		List<List<String>> ans = new ArrayList<>();
		for (String email : graph.keySet()) {
			if (!seen.contains(email)) {
				// bfs
				seen.add(email);
				Stack<String> stack = new Stack<>();// save email
				stack.push(email);
				List<String> component = new ArrayList<>();// list layer for one name
				// 遍历所有跟email相连的
				while (!stack.empty()) {
					String node = stack.pop();
					component.add(node);
					for (String nei : graph.get(node)) {
						if (!seen.contains(nei)) {
							seen.add(nei);
							stack.push(nei);
						}
					}
				}
				Collections.sort(component);// 结果需要sort
				component.add(0, emailToName.get(email));// 第一位放name
				ans.add(component);
			}
		}
		return ans;
	}
}
