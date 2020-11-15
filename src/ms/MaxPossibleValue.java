package ms;

public class MaxPossibleValue {

	/**
	 * add '5' to return the max possible value e.g: 268 -> 5268 0 -> 50 -999 ->
	 * -5999
	 */
	public int solution(int n) {
		if (n == 0)
			return 50;

		StringBuilder sb = new StringBuilder(String.valueOf(Math.abs(n)));
		int flag = n > 0 ? 1 : -1;
		if (n > 0) {
			int idx = 0;
			while (idx < sb.length() && (sb.charAt(idx) - '0') >= 5) {
				idx++;
			}
			sb.insert(idx, 5);
		} else {
			int idx = 0;
			while (idx < sb.length() && (sb.charAt(idx) - '0') <= 5) {
				idx++;
			}
			sb.insert(idx, 5);
		}
		int val = Integer.parseInt(sb.toString());
		return val * flag;
	}

	public static void main(String[] args) {
		MaxPossibleValue m = new MaxPossibleValue();

		System.out.println(m.solution(268));
		System.out.println(m.solution(0));
		System.out.println(m.solution(-999));
	}
}
