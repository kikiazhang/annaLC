package pualtrics;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 * 
 * Return the smallest sorted list of ranges that cover all the numbers in the
 * array exactly. That is, each element of nums is covered by exactly one of the
 * ranges, and there is no integer x such that x is in one of the ranges but not
 * in nums.
 * 
 * Each range [a,b] in the list should be output as:
 * 
 * "a->b" if a != b "a" if a == b
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [0,1,2,4,5,7] Output: ["0->2","4->5","7"] Explanation: The
 * ranges are: [0,2] --> "0->2" [4,5] --> "4->5" [7,7] --> "7"
 * 
 */
public class SummaryRanges228 {
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return res;
		String cur = "";
		int curNum = nums[0];
		int stNum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (curNum + 1 != nums[i]) {
				if (stNum != curNum) {
					cur = stNum + "->" + curNum;
				} else {
					cur = stNum + "";
				}
				res.add(new String(cur));
				cur = "";
				stNum = curNum = nums[i];
			} else {
				curNum = nums[i];
			}
		}
		if (stNum != curNum) {
			cur = stNum + "->" + curNum;
		} else {
			cur = stNum + "";
		}
		res.add(new String(cur));
		return res;
	}
}
