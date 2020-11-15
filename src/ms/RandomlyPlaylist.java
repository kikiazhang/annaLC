package ms;

import java.util.Arrays;
import java.util.Random;

public class RandomlyPlaylist {

	Random rand = new Random();
    int[] originalNums;
        
    public RandomlyPlaylist(int[] nums) {
        originalNums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return originalNums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] array = Arrays.copyOf(originalNums, originalNums.length);
        for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			int temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		
		return array;
    }
}
