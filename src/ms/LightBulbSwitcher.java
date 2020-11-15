package ms;

public class LightBulbSwitcher {

	/**
	 * There are N bulbs, numbered from 1 to N, arranged in a row. Initially, all
	 * the bulbs are turned off. At moment K (for K from 0 to N - 1), we turn on the
	 * A[K]-th bulb. A bulb shines if it is on and all the previous bulbs are turned
	 * on too.
	 * 
	 * Return the number of moments for which every turned on bulb shines.
	 * 
	 * Example 1:
	 * 
	 * Input: A = [2, 1, 3, 5, 4] Output: 3 Example 2:
	 * 
	 * Input: A = [2, 3, 4, 1, 5] Output: 2 Example 3:
	 * 
	 * Input: A = [5, 4, 3, 2, 1] Output: 1 N is an integer within the range
	 * [1..100,000] the elements of A are all distinct each element of array A is an
	 * integer within the range [1..N]
	 * 
	 */
	public int getShiningCounts(int[] arr) {
		int ans = 0, sum = 0, target = 0;

		for (int i = 1; i <= arr.length; i++) {
			sum += arr[i - 1];
			target += i; // Sum from 1 to i
			if (sum == target)
				ans++;
		}

		return ans;
	}
}
