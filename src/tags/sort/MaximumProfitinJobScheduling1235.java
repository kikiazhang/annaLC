package tags.sort;

import java.util.Arrays;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to
 * endTime[i], obtaining a profit of profit[i].
 * 
 * You're given the startTime , endTime and profit arrays, you need to output
 * the maximum profit you can take such that there are no 2 jobs in the subset
 * with overlapping time range.
 * 
 * If you choose a job that ends at time X you will be able to start another job
 * that starts at time X. example: Input: startTime = [1,2,3,3], endTime =
 * [3,4,5,6], profit = [50,10,40,70] Output: 120 Explanation: The subset chosen
 * is the first and fourth job. Time range [1-3]+[3-6] , we get profit of 120 =
 * 50 + 70.
 */
public class MaximumProfitinJobScheduling1235 {
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int n = profit.length;
		Job[] jobs = new Job[n];
		for (int i = 0; i < startTime.length; i++) {
			jobs[i] = (new Job(startTime[i], endTime[i], profit[i]));
		}
		// till now, the max profit sum
		int dp[] = new int[jobs.length];
		Arrays.sort(jobs, (a, b) -> (a.end - b.end));

		dp[0] = jobs[0].profit;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < jobs.length; i++) {
			dp[i] = Math.max(jobs[i].profit, dp[i - 1]);// 结束位：要i||不要i
			for (int j = i - 1; j >= 0; j--) {// 遍历前面
				if (jobs[j].end <= jobs[i].start) {// 如果j可以连接i
					dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);// max（现在算好的dp i，profit i + dp j）
					break;// 只用找到最近的j
				}
			}
			max = Math.max(dp[i], max);
		}
		return max;
	}

	class Job {
		int start, end, profit;

		public Job(int s, int e, int p) {
			this.start = s;
			this.end = e;
			this.profit = p;
		}
	}
}
