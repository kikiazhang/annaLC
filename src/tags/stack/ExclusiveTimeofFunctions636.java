package tags.stack;

import java.util.List;
import java.util.Stack;

/**
 * On a single threaded CPU, we execute some functions. Each function has a
 * unique id between 0 and N-1.
 * 
 * We store logs in timestamp order that describe when a function is entered or
 * exited.
 * 
 * Each log is a string with this format: "{function_id}:{"start" |
 * "end"}:{timestamp}". For example, "0:start:3" means the function with id 0
 * started at the beginning of timestamp 3. "1:end:2" means the function with id
 * 1 ended at the end of timestamp 2.
 * 
 * A function's exclusive time is the number of units of time spent in this
 * function. Note that this does not include any recursive calls to child
 * functions.
 * 
 * The CPU is single threaded which means that only one function is being
 * executed at a given time unit.
 * 
 * Return the exclusive time of each function, sorted by their function id.
 * Input: n = 2 logs = ["0:start:0","1:start:2","1:end:5","0:end:6"] Output: [3,
 * 4] Explanation: Function 0 starts at the beginning of time 0, then it
 * executes 2 units of time and reaches the end of time 1. Now function 1 starts
 * at the beginning of time 2, executes 4 units of time and ends at time 5.
 * Function 0 is running again at the beginning of time 6, and also ends at the
 * end of time 6, thus executing for 1 unit of time. So function 0 spends 2 + 1
 * = 3 units of total time executing, and function 1 spends 4 units of total
 * time executing.
 */
public class ExclusiveTimeofFunctions636 {
	public int[] exclusiveTime(int n, List<String> logs) {
		Stack<Integer> stack = new Stack<>();// save index [0, n-1]
		int[] res = new int[n];
		// 0:start:1
		String[] s = logs.get(0).split(":");
		stack.push(Integer.parseInt(s[0]));
		int i = 1, prev = Integer.parseInt(s[2]);// prev is time
		while (i < logs.size()) {
			s = logs.get(i).split(":");// next one
			if (s[1].equals("start")) {// next one is start time, means that prev one can run [next time - prev]
				if (!stack.isEmpty())
					res[stack.peek()] += Integer.parseInt(s[2]) - prev;// 2-0
				stack.push(Integer.parseInt(s[0]));// write next one index
				prev = Integer.parseInt(s[2]);// update prev
			} else {
				// next one is end time, means prev(same index) stopped here
				res[stack.peek()] += Integer.parseInt(s[2]) - prev + 1;// 5-2+1 = 4
				stack.pop();// this index is ended, need to pop
				prev = Integer.parseInt(s[2]) + 1;
			}
			i++;
		}
		return res;
	}
}
