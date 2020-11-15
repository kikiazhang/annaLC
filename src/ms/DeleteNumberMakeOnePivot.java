package ms;

public class DeleteNumberMakeOnePivot {

	/**
	 * 第三题是从一个数组中去掉最少数字变成先增后减序列，返回去掉数字的个数。 举例： [2,3,15,5,7,6,4,1]返回1：去掉15或者5
	 */

	// 双端 最长增长substring 问题， 然后多出的就是要删除的
	public int solution(int[] arr) {
		int ans = Integer.MAX_VALUE;
		int[] dp = new int[arr.length];
		int[] dp2 = new int[arr.length];
		dp[0] = 1;
		for (int i = 1; i < arr.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[j] > max) {
					max = dp[j];
				}
			}
			dp[i] = max + 1;
		}

		dp2[arr.length - 1] = 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			int max = 0;
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[i] > arr[j] && dp2[j] > max) {
					max = dp2[j];
				}
			}
			dp2[i] = max + 1;
		}
		for (int i = 0; i < arr.length; i++) {
			ans = Math.min(ans, arr.length - dp[i] - dp2[i] + 1);
		}
		return ans;
	}

	public static void main(String[] args) {
		DeleteNumberMakeOnePivot m = new DeleteNumberMakeOnePivot();

		int[] arr = { 2, 3, 15, 5, 7, 6, 4, 1 };
		System.out.println(m.solution(arr));

		int[] arr2 = { 9, 5, 6, 7, 5, 6, 5, 3, 1 };
		System.out.println(m.solution(arr2));
	}
}
