package ms;

public class LongestPrefixContainsSameNumberXY {

	/**
	 * Given two positive integers X and Y, and an array arr[] of positive integers.
	 * We need to find the longest prefix index which contains an equal number of X
	 * and Y. Print the maximum index of largest prefix if exist otherwise print -1.
	 * 
	 * Examples:
	 * 
	 * Input : array[] = [7, 42, 5, 6, 42, 8, 7, 5, 3, 6, 7] X = 7 Y = 42 Output : 9
	 * The longest prefix with same number of occurrences of 7 and 42 is: 7, 42, 5,
	 * 6, 42, 8, 7, 5, 3, 6 42
	 */
	public static int findIndex(int[] arr, int X, int Y) {
		if (arr == null || arr.length == 0)
			return 0;
		int max = 0;
		int cx = 0, cy = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == X) {
				cx++;
			} else if (arr[i] == Y) {
				cy++;
			}
			if ((cx != 0) && cx == cy)
				max = i;
		}
		return max;
	}

	static public void main(String[] args) {
		int[] arr = { 7, 42, 5, 6, 42, 8, 7, 5, 3, 6, 7 };
		int X = 7, Y = 42;
		System.out.println("Ending index of prefix is " + findIndex(arr, X, Y));
	}
}
