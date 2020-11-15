package ms;

public class DeleteStringWithout3IdenticalConsecutiveLetters {
	/**
	 * S="eedaaad", return "eedaad" S="xxxtxxx", return "xxtxx" S="uuuuxaaaaxuu",
	 * return "uuxaaxuu"
	 */
	public String delete(String s) {
		if (s == null || s.length() == 0)
			return null;

		StringBuilder sb = new StringBuilder();
		sb.append(s.charAt(0));
		int count = 1;
		char c = s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				count++;
				if (count >= 3) {
					continue;
				} else {
					sb.append(s.charAt(i));
				}
			} else {
				count = 1;
				c = s.charAt(i);
				sb.append(s.charAt(i));
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		DeleteStringWithout3IdenticalConsecutiveLetters m = new DeleteStringWithout3IdenticalConsecutiveLetters();

		String s1 = "eedaaad";
		String s2 = "xxxtxxx";
		String s3 = "uuuuxaaaaxuu";

		System.out.println(m.delete(s1));
		System.out.println(m.delete(s2));
		System.out.println(m.delete(s3));
	}
}
