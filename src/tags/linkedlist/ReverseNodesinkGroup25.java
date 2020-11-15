package tags.linkedlist;

import tags.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the linked
 * list. If the number of nodes is not a multiple of k then left-out nodes in
 * the end should remain as it is.
 * 
 * Example:
 * 
 * Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesinkGroup25 {
	// recursive
	public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }//不是完整的k不用反转
        if(count == k){
            //后面整理好的的list
           curr = reverseKGroup(curr, k);
            //反转这一步部分
           while(count > 0){ // 反转k次
               ListNode next = head.next;
               head.next = curr;//连好head->curr
               //next->head->curr
               curr = head;
               head = next;
               count--;
           } 
           //回到整理好的开始
           head = curr;
        }
        return head;
    }
}
