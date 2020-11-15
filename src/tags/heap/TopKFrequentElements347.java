package tags.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2] Example 2:
 * 
 * Input: nums = [1], k = 1 Output: [1]
 */
public class TopKFrequentElements347 {
	public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int[] res = new int[k];
        PriorityQueue<Map.Entry<Integer, Integer>> p = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2){
                return e1.getValue() - e2.getValue();
            }
        });
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            p.offer(e);
            if(p.size() > k){
                p.poll();
            }
        }
        for(int i=k-1;i>=0;i--){
            res[i] = p.poll().getKey();
        }
        
        return res;
    }
}
