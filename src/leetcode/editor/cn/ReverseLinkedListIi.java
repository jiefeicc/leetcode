//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
//
//
// 示例 2：
//
//
//输入：head = [5], left = 1, right = 1
//输出：[5]
//
//
//
//
// 提示：
//
//
// 链表中节点数目为 n
// 1 <= n <= 500
// -500 <= Node.val <= 500
// 1 <= left <= right <= n
//
//
//
//
// 进阶： 你可以使用一趟扫描完成反转吗？
// Related Topics 链表 👍 1103 👎 0

package leetcode.editor.cn;
//java:反转链表 II
class ReverseLinkedListIi{
    public static void main(String[] args){
        Solution solution = new ReverseLinkedListIi().new Solution();
        ListNode test = new ListNode(1);
        ListNode test2 = new ListNode(2);
        ListNode test3 = new ListNode(3);
        ListNode test4 = new ListNode(4);
        ListNode test5 = new ListNode(5);
        test4.next = test5;
        test3.next = test4;
        test2.next = test3;
        test.next = test2;
        solution.reverseBetween(test, 2, 4);
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
         双指针(guard + point) + 头插法
         将guard移动到待翻转节点前一个，point移动到待翻转节点
         将point后面一个节点插入到guard后面
         重复上一步操作m-n次
         */
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;
            ListNode g = dummyHead;
            ListNode p = dummyHead.next;
            // 将guard移动到待翻转节点前一个，point移动到待翻转节点
            for (int i=0; i<m-1; i++) {
                g = g.next;
                p = p.next;
            }
            for (int i=0; i<n-m; i++) {
                insertHead(g, p);
            }
            return dummyHead.next;
        }
        // 将point后面一个节点插入到guard后面
        public void insertHead(ListNode guard, ListNode point) {
            // 记住point后面的节点
            ListNode removed = point.next;
            //删除point后面的节点
            point.next = point.next.next;

            //将point后面的节点插到guard后面
            removed.next = guard.next;
            guard.next = removed;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
