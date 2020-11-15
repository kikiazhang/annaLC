package freq.five;

public class StringToInteger {
	
	/**
	 * 
	 * 	"4 2"
		"  -42"
		"  - 42"
		"-91283472332"
		"2147483646"
		" -1010023630o4"
		
		output:
		
		4
		-42
		0
		-2147483648
		2147483646
		-1010023630
	 * */

	public int myAtoi(String str) {
        if(str == null || str.length() == 0) return 0;
        
        str = str.trim();
        int sign = 1;
        int res = 0;
        for(int i=0;i<str.length();i++){
            if(Character.isDigit(str.charAt(i))){
                if(Integer.MAX_VALUE/10 < res || (Integer.MAX_VALUE/10 == res && Integer.MAX_VALUE%10 < (str.charAt(i) - '0')))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                
                res = res*10 + (str.charAt(i) - '0');
            } else {
                if(i == 0 && (str.charAt(i) == '-' || str.charAt(i) == '+')){
                    sign = str.charAt(i) == '-' ? -1 : 1;
                } else {
                    break;
                }
            }
        }
        res = res * sign;
        return res;
    }
}
