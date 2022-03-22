//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 1309 👎 0

package leetcode.editor.cn;

import java.util.*;

//java:回文链表
class PalindromeLinkedList{
    public static void main(String[] args){
        Solution solution = new PalindromeLinkedList().new Solution();
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
        public boolean isPalindrome(ListNode head) {
            ListNode dummy = new ListNode(-1);
            ListNode slow = dummy;
            ListNode fast = dummy;
            dummy.next = head;

            //找到链表中间节点
            while(fast!=null && fast.next!=null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // 断开链表
            ListNode tmp = slow.next;
            slow.next = null;

            // 反转后半部分链表
            ListNode lastListNode = reverseList(tmp);

            //将链表前半部分 preListNode 和 反转的后半部分 lastListNode 对比
            ListNode preListNode = dummy.next;
            while(lastListNode!=null) {
                if(preListNode.val!=lastListNode.val) {
                    return false;
                }
                preListNode = preListNode.next;
                lastListNode = lastListNode.next;
            }
            return true;
        }

        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            //用tmp记录cur的右边节点，防止反转cur之后找不到右边节点
            ListNode tmp;
            while (cur!=null) {
                tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            }
            return pre;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}