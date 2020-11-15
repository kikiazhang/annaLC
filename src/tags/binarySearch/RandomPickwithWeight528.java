package tags.binarySearch;

/**
 * Given an array w of positive integers, where w[i] describes the weight of
 * index i(0-indexed), write a function pickIndex which randomly picks an index
 * in proportion to its weight.
 * 
 * For example, given an input list of values w = [2, 8], when we pick up a
 * number out of it, the chance is that 8 times out of 10 we should pick the
 * number 1 as the answer since it's the second element of the array (w[1] = 8).
 * 
 * 
 * 
 * Example 1:
 * 
 * Input ["Solution","pickIndex"] [[[1]],[]] Output [null,0]
 * 
 * Explanation Solution solution = new Solution([1]); solution.pickIndex(); //
 * return 0. Since there is only one single element on the array the only option
 * is to return the first element.
 */
public class RandomPickwithWeight528 {
	int sum;// 总重量
	int[] weights;// 到目前为止的重量和

	public RandomPickwithWeight528(int[] w) {
		weights = new int[w.length];

		int prefixSum = 0;
		for (int i = 0; i < w.length; i++) {
			prefixSum += w[i];
			weights[i] = prefixSum;
		}
		sum = prefixSum;
	}

	public int pickIndex() {
		// random 出来的数 小于 cur，就返回 cur
		double target = sum * Math.random();
		int i = 0;
		for (; i < weights.length; i++) {
			if (target < weights[i]) {
				return i;
			}
		}
		return i - 1;
	}
}
