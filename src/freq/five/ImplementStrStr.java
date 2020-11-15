package freq.five;

public class ImplementStrStr {

	/**
	 * Implement strStr().

		Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
		
		Example 1:
		
		Input: haystack = "hello", needle = "ll"
		Output: 2
		Example 2:
		
		Input: haystack = "aaaaa", needle = "bba"
		Output: -1
	 * */
	public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) return 0;
        if(needle.length() > haystack.length()) return -1;
        char[] hChar = haystack.toCharArray();
        
        for(int i=0;i<hChar.length;i++){
            if(hChar[i] == needle.charAt(0)){
                int t = i;
                for(int j=0;j<needle.length();j++){
                    if(t < hChar.length && hChar[t] == needle.charAt(j)){
                        if(j == needle.length() - 1) return i;
                        t++;
                    } else {
                        break;
                    }
                }
            }
        }
        return -1;
    }
	//边界条件：i（开始位）到h-n为止，r（长度）到n长度为止
	public int strStr1(String h, String n) {
		if(n == null || n.length() == 0) return 0;
		if(n.length() > h.length()) return -1;
		
		int r = 0;
		for(int i=0;i<=h.length()-n.length();i++) {
			while(r<n.length()&&r+i<h.length()&&h.charAt(i+r)==n.charAt(r)) {
				if(r == n.length() - 1) {
					return i;
				}
				r++;
			}
			r = 0;
		}
		return -1;
	}
}
