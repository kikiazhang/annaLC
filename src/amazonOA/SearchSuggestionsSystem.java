package amazonOA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class SearchSuggestionsSystem {
	/**
	 * Given an array of strings products and a string searchWord. We want to design
	 * a system that suggests at most three product names from products after each
	 * character of searchWord is typed. Suggested products should have common
	 * prefix with the searchWord. If there are more than three products with a
	 * common prefix return the three lexicographically minimums products.
	 * 
	 * Return list of lists of the suggested products after each character of
	 * searchWord is typed.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"],
	 * searchWord = "mouse" Output: [ ["mobile","moneypot","monitor"],
	 * ["mobile","moneypot","monitor"], ["mouse","mousepad"], ["mouse","mousepad"],
	 * ["mouse","mousepad"] ] Explanation: products sorted lexicographically =
	 * ["mobile","moneypot","monitor","mouse","mousepad"] After typing m and mo all
	 * products match and we show user ["mobile","moneypot","monitor"] After typing
	 * mou, mous and mouse the system suggests ["mouse","mousepad"] Example 2:
	 * 
	 * Input: products = ["havana"], searchWord = "havana" Output:
	 * [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]] Example
	 * 3:
	 * 
	 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord =
	 * "bags" Output:
	 * [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
	 */
	/**
	 * 
	 * Time O(NlogN) for sorting Space O(logN) for quick sort.
	 * 
	 * Time O(logN) for each query Space O(query) for each query where I take word
	 * operation as O(1)
	 */
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> res = new ArrayList<>();
		TreeMap<String, Integer> map = new TreeMap<>();// binary search
		Arrays.sort(products);
		List<String> productsList = Arrays.asList(products);

		for (int i = 0; i < products.length; i++) {
			map.put(products[i], i);
		}

		String key = "";
		for (char c : searchWord.toCharArray()) {
			key += c;
			String ceiling = map.ceilingKey(key);
			String floor = map.floorKey(key + "~");// 最后一个词
			if (ceiling == null || floor == null)
				break;
			res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));// 三个词之后，或者到最后一个词
		}
		// 到最后没有能找到的词了，就加空的
		while (res.size() < searchWord.length())
			res.add(new ArrayList<>());
		return res;
	}

	public static void main(String[] args) {
		String[] products = { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
		String searchWord = "mouse";

		SearchSuggestionsSystem s = new SearchSuggestionsSystem();
		List<List<String>> res = s.suggestedProducts(products, searchWord);
		for (List<String> list : res) {
			for (String str : list) {
				System.out.print(str + " ");
			}
			System.out.print("\n");
		}
	}
}
