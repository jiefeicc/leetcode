//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
//
// 不允许修改 链表。
//
//
//
//
//
//
// 示例 1：
//
//
//
//
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
// 示例 2：
//
//
//
//
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
// 示例 3：
//
//
//
//
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围在范围 [0, 10⁴] 内
// -10⁵ <= Node.val <= 10⁵
// pos 的值为 -1 或者链表中的一个有效索引
//
//
//
//
// 进阶：你是否可以使用 O(1) 空间解决此题？
// Related Topics 哈希表 链表 双指针 👍 1297 👎 0

package leetcode.editor.cn;
//java:环形链表 II
class LinkedListCycleIi{
    public static void main(String[] args){
        Solution solution = new LinkedListCycleIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public class Solution {
        /*
        设链表共 a+b 个节点，链表头到链表入口有 a 个节点（不包含链表入口），链表环有 b 个节点
        当 fast 追上 slow 时：
            设 fast 走 f 步，slow 走 s 步
            fast 走的步数是 slow 步数的 2 倍，即 f = 2s;
            fast 追上了 slow，f = a + jb, s = a + kb ==> f = s + nb;
            f = 2s, f = s + nb ==> s = nb, f = 2nb;
        节点从 head 走到链表入口节点时的步数: a + xb
        slow 已经走了 nb，那么 slow 再走 a 步就是入环点了

        重新构建一个从头开始的指针 temp，往前走 a 步到入口，slow 也同时往前走了 a 步，最终两节点在入口相遇。
         */
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (slow.next != null && fast.next!=null && fast.next.next!=null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    ListNode temp = head;
                    while (temp != slow) {
                        temp = temp.next;
                        slow = slow.next;
                    }
                    return temp;
                }
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
