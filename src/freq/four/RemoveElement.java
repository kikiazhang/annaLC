package freq.four;

public class RemoveElement {

	/**
	 * Example 1:
	 * 
	 * Given nums = [3,2,2,3], val = 3,
	 * 
	 * Your function should return length = 2, with the first two elements of nums
	 * being 2.
	 * 
	 * It doesn't matter what you leave beyond the returned length. Example 2:
	 * 
	 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
	 * 
	 * Your function should return length = 5, with the first five elements of nums
	 * containing 0, 1, 3, 0, and 4.
	 * 
	 * Note that the order of those five elements can be arbitrary.
	 * 
	 * It doesn't matter what values are set beyond the returned length.
	 */
	// in place O(n)
	public int removeElement(int[] nums, int val) {
		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != val) {
				nums[i] = nums[j];
				i++;
			}
		}
		return i;
	}
	
	//solution 2
	public int removeElement2(int[] nums, int val) {
	    int i = 0;
	    int n = nums.length;
	    while (i < n) {
	        if (nums[i] == val) {
	            nums[i] = nums[n - 1];
	            // reduce array size by one
	            n--;
	        } else {
	            i++;
	        }
	    }
	    return n;
	}
}
