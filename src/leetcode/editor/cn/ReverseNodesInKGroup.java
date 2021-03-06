//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
//
// 进阶：
//
//
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
//
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
//
//
// 示例 3：
//
//
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
//
//
// 示例 4：
//
//
//输入：head = [1], k = 1
//输出：[1]
//
//
//
//
//
// 提示：
//
//
// 列表中节点的数量在范围 sz 内
// 1 <= sz <= 5000
// 0 <= Node.val <= 1000
// 1 <= k <= sz
//
// Related Topics 递归 链表 👍 1391 👎 0

package leetcode.editor.cn;
//java:K 个一组翻转链表
class ReverseNodesInKGroup{
    public static void main(String[] args){
        Solution solution = new ReverseNodesInKGroup().new Solution();
        ListNode test = new ListNode(1);
        ListNode test2 = new ListNode(2);
        ListNode test3 = new ListNode(3);
        ListNode test4 = new ListNode(4);
        ListNode test5 = new ListNode(5);
        test4.next = test5;
        test3.next = test4;
        test2.next = test3;
        test.next = test2;
        ListNode listNode = solution.reverseKGroup1(test, 2);
        System.out.println(listNode);
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
        定义两个 dummy 节点 pre 和 end
        end 往后移动 k 个节点，这 k 个节点单拿出来组成一个链表进行翻转，pre 设置到 end 的位置。
        重复上述操作，直到 end 节点往后移动不到 k 个节点。
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode pre = dummy;
            ListNode end = dummy;
            while (end.next != null) {
                for (int i=0; i<k&&end!=null; i++) {
                    end = end.next;
                }
                if (end == null) {
                    break;
                }
                // 记录断点并断开链表
                ListNode right = end.next;
                end.next = null;

                // 翻转这一段链表
                ListNode left = pre.next;
                pre.next = reverse(left);

                // left已经到了右边了，此时连接链表
                left.next = right;

                // pre 设置到 end 的位置
                pre = left;
                end = left;
            }
            return dummy.next;
        }
        // 不满 K 个也要翻转的处理方法
        public ListNode reverseKGroup1(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode pre = dummy;
            ListNode end = dummy;
            ListNode tmp = null;
            while (end.next != null) {
                for (int i=0; i<k&&end!=null; i++) {
                    // 原来逻辑不变，用一个tmp记录end
                    tmp = end;
                    end = end.next;
                }
                if (end == null) {
                    // 当end到最后空节点时，把end移回最后的尾结点，不做break，而是对最后这段进行翻转
                    end = tmp;
                }
                // 记录断点并断开链表
                ListNode right = end.next;
                end.next = null;

                // 翻转这一段链表
                ListNode left = pre.next;
                pre.next = reverse(left);

                // left已经到了右边了，此时连接链表
                left.next = right;

                // pre 设置到 end 的位置
                pre = left;
                end = left;
            }
            return dummy.next;
        }
        /*
        翻转链表
        cur = head，pre = null
        cur->pre，不停的往后迭代。
         */
        private ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode pre = null;
            ListNode cur = head;
            ListNode tmp;
            while (cur != null) {
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
