//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// 
//L0 → L1 → … → Ln - 1 → Ln
// 
//
// 请将其重新排列后变为： 
//
// 
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [1,2,3,4]
//输出：[1,4,2,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000 
// 
// Related Topics 栈 递归 链表 双指针 👍 726 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//java:重排链表
class ReorderList{
    public static void main(String[] args){
        Solution solution = new ReorderList().new Solution();
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
        双向队列解法
        除头节点外所有节点全部入队列
        节点出队列（先后再前）重建链表
         */
        public void reorderList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return;
            }
            Deque<ListNode> deque = new LinkedList<>();
            // 入队列
            ListNode next= head.next;
            while (next != null) {
                deque.add(next);
                next = next.next;
            }
            // 出队列
            while (!deque.isEmpty()) {
                // 后出
                head.next = deque.removeLast();
                // 节点指针往后移位
                head = head.next;
                // 前出
                if (!deque.isEmpty()) {
                    head.next = deque.removeFirst();
                    // 节点指针往后移位
                    head = head.next;
                }
            }
            // 断开尾结点的next，防止环形链表
            head.next = null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
