package ms;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
	/**
	 * FizzBuzz变种 输入N需要print出来1..N的所有fizzbuzz词语 最底下要求写着保证correctness为第一要务
	 * 
	 * 输出从1到N：如果数字可以被2整除，则输出Codility，如果数字可以被3整除，则输出Test，如果数字可以被5整除，则输出Coders。如果可以被多个整除，比如说可以同时被2和3整除，则输出CodilityTest，以此类推。都不能被整除的输出原来数字。
	 */
	public List<String> fizzBuzz(int n) {
		List<String> res = new ArrayList<>();
		String codility = "Codility";
		String test = "Test";
		String coder = "Coder";
		for (int i = 1; i <= n; i++) {
			StringBuilder sb = new StringBuilder();
			if (i % 2 == 0) {
				sb.append(codility);
			}
			if (i % 3 == 0) {
				sb.append(test);
			}
			if (i % 5 == 0) {
				sb.append(coder);
			}
			if (i % 2 != 0 && i % 3 != 0 && i % 5 != 0) {
				sb.append(i);
			}
			res.add(sb.toString());
		}
		return res;
	}

	public static void main(String[] args) {
		FizzBuzz m = new FizzBuzz();
		System.out.println(m.fizzBuzz(30));
	}
}
