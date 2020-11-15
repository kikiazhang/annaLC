package freq.five;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	/**
	 *  Given array nums = [-1, 0, 1, 2, -1, -4],

		A solution set is:
		[
		  [-1, 0, 1],
		  [-1, -1, 2]
		]
	 * */
	public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i == 0 || nums[i] != nums[i-1]) {
            	if(nums[i] > 0) break;//improvement, if nums[i] > 0 and nums is sorted, then sum can't be 0.
                int l = i + 1;
                int r = nums.length - 1;
                while(l < r) {
                    if(nums[i] + nums[l] + nums[r] == 0){
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        //排除l r重复到下一个值
                        while(l < r && nums[l] == nums[l+1]) l++;
                        while(l < r && nums[r] == nums[r-1]) r--;
                        l++;
                        r--;
                    } else if(nums[i] + nums[l] + nums[r] < 0){
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }
	
}
