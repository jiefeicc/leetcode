package leetcode.editor.cn;

import java.util.*;

class RotateList{
    public static void main(String[] args){
        Solution solution = new RotateList().new Solution();
        ListNode head = new ListNode().getListNode(new int[]{1});
        solution.rotateRight(head, 1);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        /*
        推演：1,2,3,4,5,6,7
            移动7位，不变
            移动3位 5,6,7,1,2,3,4
            移动17位，前14位不影响，后三位影响 3,4,5,1,2
        结论：链表移动k位，就是把链表后面 k%len 个节点移动到前面
             就是把第 (length - k)%length 个链表后面的链表移动到前面
        解题步骤：
            1.找到第 (length - k)%length 个链表
            2.断开第 (length - k)%length 个链表后面的链表
            3.将后面链表拼接到前面
         */
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || k == 0 || head.next == null) {
                return head;
            }
            int length = 0;
            ListNode cur = head;
            while (cur != null) {
                cur = cur.next;
                length++;
            }
            if (k % length == 0){
                return head;
            }
            int n = length - k % length;
            cur = head;
            // 找到第 (length - k)%length 个链表
            while (n > 1) {
                cur = cur.next;
                n--;
            }
            ListNode pre = cur.next;
            // 断开第 (length - k)%length 个链表后面的链表
            cur.next = null;
            cur = pre;
            while (cur.next != null) {
                cur = cur.next;
            }
            // 将后面链表拼接到前面
            cur.next = head;
            return pre;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}