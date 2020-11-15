package tags.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Example 1:
 * 
 * Input: [[1,1],2,[1,1]] Output: [1,1,2,1,1] Explanation: By calling next
 * repeatedly until hasNext returns false, the order of elements returned by
 * next should be: [1,1,2,1,1]. Example 2:
 * 
 * Input: [1,[4,[6]]] Output: [1,4,6] Explanation: By calling next repeatedly
 * until hasNext returns false, the order of elements returned by next should
 * be: [1,4,6].
 */
public class FlattenNestedListIterator341 implements Iterator<Integer> {
	Deque<NestedInteger> stack;

	public FlattenNestedListIterator341(List<NestedInteger> nestedList) {
		stack = new ArrayDeque<>(nestedList);
	}

	@Override
	public Integer next() {
		if(hasNext()){
            return stack.removeFirst().getInteger();
        }
        return null;
	}

	//只保证第一个是integer，不然一直拆开放入
	private void makeStackTopAnInteger() {
		while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
			List<NestedInteger> nestedList = stack.removeFirst().getList();
			for (int i = nestedList.size() - 1; i >= 0; i--) {
				stack.addFirst(nestedList.get(i));
			}
		}
	}

	@Override
	public boolean hasNext() {
		makeStackTopAnInteger();
		return !stack.isEmpty();
	}

}
