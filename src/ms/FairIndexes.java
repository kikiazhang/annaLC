package ms;

public class FairIndexes {

	/**
	 * A=[4,-1,0,3] B=[-2,5,0,3], return 2 since [4-1]=[0+3] [-2+5]=[0+3]
	 * 
	 */

	public int solution(int[] A, int[] B) {
		int res = 0;
		int sa = 0, sb = 0;
		for (int i = 0; i < A.length; i++) {
			sa += A[i];
			sb += B[i];
		}
		int ca = 0, cb = 0;
		for (int i = 0; i < A.length - 1; i++) {
			ca += A[i];
			cb += B[i];
			if (ca == sa - ca && cb == sb - cb && ca == cb) {
				res++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		FairIndexes m = new FairIndexes();
		int[] A = { 4, -1, 0, 3 };
		int[] B = { -2, 5, 0, 3 };

		System.out.println(m.solution(A, B));
	}
}
