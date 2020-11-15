package google;

import java.util.Map;
import java.util.TreeMap;

/**
 * Alice has a hand of cards, given as an array of integers.
 * 
 * Now she wants to rearrange the cards into groups so that each group is size
 * W, and consists of W consecutive cards.
 * 
 * Return true if and only if she can.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3 Output: true Explanation: Alice's
 * hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]. Example 2:
 * 
 * Input: hand = [1,2,3,4,5], W = 4 Output: false Explanation: Alice's hand
 * can't be rearranged into groups of 4.
 * 
 */
public class HandofStraights846 {
	public boolean isNStraightHand(int[] hand, int W) {
		Map<Integer, Integer> map = new TreeMap<>();// freq map
		for (int i : hand) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		for (int i : map.keySet()) {
			if (map.get(i) > 0) {//  没用完
				for (int j = W - 1; j >= 0; j--) {// 凑成W长度的n段
					if (map.getOrDefault(j + i, 0) < map.get(i))
						return false;// 如果后面缺了位，false
					map.put(i + j, map.get(i + j) - map.get(i));// get i有几个，说明要凑几段
				}
			}
		}
		return true;
	}
}
