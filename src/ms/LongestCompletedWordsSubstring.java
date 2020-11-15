package ms;

public class LongestCompletedWordsSubstring {

	/**
	 * 给1个句子string s，和一个int k， 返回s中从0开始的longest substring满足以下条件：
	 * A。长度不能超过k，如果k>s.length，返回整个s B。返回的string中不能有拆分的词 举例如下： String s = "Codility
	 * we test coders", int k = 14, return "Codility we",
	 * 因为不可以有一个被拆分的词，截图在附件中。这个题主要就是考虑各种corner case。
	 */
	public String solution(String s, int k) {
		if (k >= s.length())
			return s;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			if (Character.isAlphabetic(s.charAt(i))) {
				StringBuilder tmp = new StringBuilder();
				while (i < s.length() && Character.isAlphabetic(s.charAt(i))) {
					tmp.append(s.charAt(i));
					i++;
				}
				if (i > k) {
					return sb.toString().trim();
				} else if (i == k) {
					return sb.append(tmp).toString();
				} else {
					sb.append(tmp.toString() + " ");
				}
			} else {
				if (i < k) {
					sb.append(" ");
				} else {
					return sb.toString();
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		LongestCompletedWordsSubstring m = new LongestCompletedWordsSubstring();
		String s = "Codility we test coders";
		System.out.println(m.solution(s, 16));
	}
}
