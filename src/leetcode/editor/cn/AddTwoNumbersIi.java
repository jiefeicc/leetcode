package leetcode.editor.cn;

import java.util.*;

class AddTwoNumbersIi{
    public static void main(String[] args){
        Solution solution = new AddTwoNumbersIi().new Solution();
    }
    //两数相加 II
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
        解题思路：
            双栈解法
            将两个链表的 val全部 add 到对应栈
            出栈，将两个值相加，再加上进位，构造一个链表节点 node(sum % 10)。
            node.next = head，head = node;
            维护进位 carry = sum/10;
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            LinkedList<Integer> stack1 = new LinkedList<>();
            LinkedList<Integer> stack2 = new LinkedList<>();
            while (!Objects.isNull(l1)) {
                stack1.addFirst(l1.val);
                l1 = l1.next;
            }
            while (!Objects.isNull(l2)) {
                stack2.addFirst(l2.val);
                l2 = l2.next;
            }

            ListNode head = null;
            ListNode node;
            int carry = 0;

            while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
                int sum = carry;
                sum += stack1.isEmpty() ? 0 : stack1.removeFirst();
                sum += (stack2.isEmpty() ? 0 : stack2.removeFirst());
                carry = sum/10;

                // 将加出来的值放在链表头
                node = new ListNode(sum % 10);
                node.next = head;
                head = node;
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
