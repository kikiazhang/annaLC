package lintcodeFreq;

public class MedianOfTwoSortedArrays {

	/**
	 *  nums1 = [1, 2]
		nums2 = [3, 4]
		The median is (2 + 3)/2 = 2.5
	 * */
	//need O(log(n+m))
	public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        //make sure n > m, A len > B
        if(m > n){
            int[] tmp = A;
            A = B;
            B = tmp;
            int temp = m;
            m = n;
            n = temp;
        }
        //len(left_part) = len(right_part)
        //max(left_part) <= min(right_part)
        //A,B都分成left right等长部分
        //median = (maxLeft + minRight)/2
        //i + j = m - i + n - j => i = 0~m j = half - i，保证B[j-1] <= A[i], A[i-1]<=B[j]
        //i - 1 && j - 1 为 left部分最大，i j 为right部分最小
        //遍历A
        int iMin = 0, iMax = m, halfLen = (m + n + 1)/2;
        while(iMin <= iMax){
            //mid A
            int i = (iMin + iMax) / 2;
            //B中应该取的
            int j = halfLen - i;
            if(i < iMax && B[j-1] > A[i]){
                //A部分都小，不用看了
                iMin = i + 1;
            } else if(i > iMin && A[i-1] > B[j]){
                //B部分都小，不用看了
                iMax = i - 1;
            } else {
                //相同||越界
                int maxLeft = 0;
                
                if(i == 0) maxLeft = B[j-1];//全取B
                else if(j== 0) maxLeft = A[i-1];//全取A
                else maxLeft = Math.max(A[i-1], B[j-1]);
                //中间就一个值
                if((m + n) % 2 == 1) return maxLeft;
                //两个值
                int minRight = 0;
                if(i == m) minRight = B[j];//全B
                else if(j == n) minRight = A[i];//全A
                else minRight = Math.min(B[j], A[i]);
                
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
