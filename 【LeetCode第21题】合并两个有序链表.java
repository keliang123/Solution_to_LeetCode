/*
	将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
	
	示例：

	输入：1->2->4, 1->3->4
	输出：1->1->2->3->4->4
	
	解答： 递归的思想，两个结点，当前选小的，再依次递归下去。
		   也可以用迭代，从头输出到尾。
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        
        if (l1.val > l2.val) { l2.next = mergeTwoLists(l1 , l2.next); } 
        if (l1.val <= l2.val) { l1.next = mergeTwoLists(l1.next , l2); }
        
        return l1.val > l2.val ? l2 : l1;
    }
}

//迭代
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode result = dummy;
        while (l1 != null || l2 != null) {
            if(l1 == null) {
                dummy.next = l2;
                return result.next;
            }
            if(l2 ==  null) {
                dummy.next = l1;
                return result.next;
            }

            if(l1.val < l2.val) {
                dummy.next = l1;
                dummy.next.val = l1.val;
                dummy = dummy.next;
                l1 = l1.next;
            }else {
                dummy.next = l2;
                dummy.next.val = l2.val;
                dummy = dummy.next;
                l2 = l2.next;
            }
        }
        return result.next;
    }


/*
	时间复杂度：O(m + n) ，与两个链表长度有关
	空间复杂度：O(1)
*/