package ms;

public class ParticleVelocity {

	/**
	 * 找出3个连续片段是等差数列的个数
	 * 
	 * A = [1, 2, 3, 4]
	 * 
	 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3,
	 * 4] itself.
	 * 
	 */
	// o(n^2)
	public int solution(int[] A) {
		int count = 0;
		for (int s = 0; s < A.length - 2; s++) {
			int d = A[s + 1] - A[s];
			// 检查3个及以上等差数列，每次只用看最后两位，前面比好了
			for (int e = s + 2; e < A.length; e++) {
				if (A[e] - A[e - 1] == d)
					count++;
				else
					break;
			}
		}
		return count;
	}

	// o(n)
	public int numberOfArithmeticSlices(int[] A) {
		int[] dp = new int[A.length];
		int sum = 0;
		for (int i = 2; i < dp.length; i++) {
			if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
				dp[i] = 1 + dp[i - 1];
				sum += dp[i];
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		ParticleVelocity m = new ParticleVelocity();
		int[] input = { -1, 1, 3, 3, 3, 2, 3, 2, 1, 0 };
		int result = m.solution(input);
		System.out.println(result);
	}
}
