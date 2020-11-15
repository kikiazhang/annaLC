package tags.slidingWindow;

/**
 * There are several cards arranged in a row, and each card has an associated
 * number of points The points are given in the integer array cardPoints.
 * 
 * In one step, you can take one card from the beginning or from the end of the
 * row. You have to take exactly k cards.
 * 
 * Your score is the sum of the points of the cards you have taken.
 * 
 * Given the integer array cardPoints and the integer k, return the maximum
 * score you can obtain.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3 Output: 12 Explanation: After the
 * first step, your score will always be 1. However, choosing the rightmost card
 * first will maximize your total score. The optimal strategy is to take the
 * three cards on the right, giving a final score of 1 + 6 + 5 = 12. Example 2:
 * 
 * Input: cardPoints = [2,2,2], k = 2 Output: 4 Explanation: Regardless of which
 * two cards you take, your score will always be 4.
 */
public class MaximumPointsYouCanObtainfromCards1423 {
	// sliding window, connect array twice to make it sw problem
	public int maxScore(int[] cardPoints, int k) {
		// card：[1,2,3,4,5,6,1], k = 3
		// 合成：5,6,1，1,2,3,4,5,6,1
		int res = 0, len = cardPoints.length;
		// 从last k开始，到ed（i）走了len+k结束
		for (int start = len - k, i = start, win = 0; i < len + k; ++i) {
			win += cardPoints[i % len]; // accumulate the value of the sliding window.
			if (i - start >= k) { // Is the sliding window wider than k?
				win -= cardPoints[(i - k) % len]; // deduct the element from the left of the window.
			}
			res = Math.max(win, res); // update the maximum.
		}
		return res;
	}

	// dp
	public int maxScore2(int[] cardPoints, int k) {
		int[] dp = new int[k + 1];
		// this dp array stores the total points when taking i cards from left.
		// since we could take 0 - k cards from left ,the length of the dp array would
		// be k+1.

		for (int i = cardPoints.length - 1; i >= cardPoints.length - k; i--) {
			dp[0] += cardPoints[i];
		}
		int max_points = dp[0];

		for (int i = 1; i < k + 1; i++) {
			dp[i] = dp[i - 1] + cardPoints[i - 1] - cardPoints[cardPoints.length - k + i - 1];
			max_points = Math.max(max_points, dp[i]);
		}

		return max_points;
	}
}
