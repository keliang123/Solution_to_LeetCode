/*
	给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
并且它们的每个节点只能存储 一位 数字。如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：
	输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
	输出：7 -> 0 -> 8
	原因：342 + 465 = 807

我的解答：
	要考虑进位和两个链表长度不一样的情况。用一个flag表示进位情况，使用一个三目运算符来计算长度不一样情况。
*/

//代码：
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode head = new ListNode(0);
        head.val = (l1.val + l2.val)%10;
        ListNode dummy = head;
        int flag = (l1.val + l2.val)/10;
        l1 = l1.next; l2 = l2.next;
        
        while (l1 != null || l2 != null) {
            int temp = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            int data = temp + flag;
            ListNode tempNode = new ListNode(data%10);
            head.next = tempNode;
            head = tempNode;
            flag = data/10;
            l1 = l1 == null ? null : l1.next; 
            l2 = l2 == null ? null : l2.next;
        }
        if (flag == 1) {
            ListNode tempNode = new ListNode(1);
            head.next = tempNode;
        }
        return dummy;
    }
}

/*
	时间复杂度：log(max(m,n)) 要把最长链表遍历完。
	空间复杂度：log(max(m,n)) 与最长链表长度有关。
*/


