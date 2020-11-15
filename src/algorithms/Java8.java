package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8 {
	
	public static void main(String[] args) {
//		List<String> list1 = Arrays.asList("a","b","c");
//		List<String> list2 = Arrays.asList("d","b","c");
//		
//		List<String> l = list1.stream()
//                .filter(e -> !list2.contains(e))
//                .collect (Collectors.toList());
		
		String[] s = solution(0,0);
		for(String str: s) {
			System.out.println(str);
		}
		
	}
	
	
	public static String[] solution(int N, int K) {
		if(N == 0) {
			return new String[] {""};
		}
		ArrayList<String> result = new ArrayList<String>();
		for(String p : solution(N-1, K)) {
			for(char l : new char[] {'a','b','c'}) {
				int pLen = p.length();
				if(pLen == 0 || p.charAt(pLen - 1) != l) {
					result.add(p + l);
				}
			}
		}
		int prefSize = Math.min(result.size(), K);
		return result.subList(0, prefSize).toArray(new String[prefSize]);
	}

}
