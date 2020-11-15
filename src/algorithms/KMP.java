package algorithms;

public class KMP {

	/**
	 * leetcode 28 implement strStr()
	 * 
	 * KMP:
	 * find the match char, until the non-match occur, then move back = (match part len) - (last match char repeat index in match part)
	 * eg: abcdab back = 6 - 4(b), if b has no repeated then - (-1)
	 * */
	public int strStr(String h, String n) {
		if(n == null || n.length() == 0) return 0;
		if(h == null || h.length() == 0) return -1;
		if(h.length() < n.length()) return -1;
		
		int[] table = getTable(n);
		int i = 0, j = 0;
		while(i < h.length() && j < n.length()) {
			//不相同了，就要跳转，继续比较，直到相同或者j为0
			while(j > 0 && h.charAt(i) != n.charAt(j)) j = table[j - 1];
			if(h.charAt(i) == n.charAt(j)) j++;
			i++;
		}
		//i是找到needle时的index，j是needle走的长度
		return j == n.length() ? i - j : -1; 
	}
	//跳转：跳过相同prefix，到第一位不同处
	//record every point if there are same prefix, then where to compare
	//[i, j] i : current index, j : should jump to (same prefix position)
	//eg :   abcdfabcg  -> if g is not the one we want, we need to jump to d to compare.
	public int[] getTable(String n) {
		int[] table = new int[n.length()];
		
		int index = 0;//prefix停止处
		for(int i = 1; i < n.length(); i++) {
			if(n.charAt(index) == n.charAt(i)) {
				//相同ij就一起后移，记录t[i] = t[i-1] + 1
				//i的位置来自于i-1的位置+1，因为现在相同
				//aaabc i=1,idx=0, t[1] = t[0]+1
				table[i] = table[i-1] + 1;
				index++;
			} else {
				//不相同，j跳转到t[i-1]，如果还不是，跳转到t[j-1]，直到相等，记录t[i] = j
				//跳回prefix之后的第一位，比较和i是否相同，不相同，继续跳回
				index = table[i-1];
				while(index > 0 && n.charAt(i) != n.charAt(index)) {
					index = table[index - 1];
				}
				//相同再后移
				if(n.charAt(i) == n.charAt(index)) {
					index++;
				}
				//prefix之后第一位||0
				table[i] = index;
			}
		}
		
		return table;
	}
}
