//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
//
// 返回同样按升序排列的结果链表。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
//
//
// 示例 2：
//
//
//输入：head = [1,1,1,2,3]
//输出：[2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围 [0, 300] 内
// -100 <= Node.val <= 100
// 题目数据保证链表已经按升序排列
//
// Related Topics 链表 双指针 👍 768 👎 0

package leetcode.editor.cn;
//java:删除排序链表中的重复元素 II
class RemoveDuplicatesFromSortedListIi{
    public static void main(String[] args){
        Solution solution = new RemoveDuplicatesFromSortedListIi().new Solution();
        ListNode test = new ListNode(1);
        ListNode test2 = new ListNode(2);
        ListNode test3 = new ListNode(2);
        ListNode test4 = new ListNode(2);
        ListNode test5 = new ListNode(3);
        ListNode test6 = new ListNode(3);
        test6.next = new ListNode(4);
        test5.next = test6;
        test4.next = test5;
        test3.next = test4;
        test2.next = test3;
        test.next = test2;

        solution.deleteDuplicates(test);

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
        双指针 pre,cur
        if(pre.next.val!=cur.next.val)
            pre和cur都后移
        else
            //当cur、pre指向的节点值相等，就不断后移cur，直到cur、pre指向的值不相等
            while(cur.next!=null && pre.next.val==cur.next.val)
                cur后移
            pre.next = cur.next，这一步直接把所有重复的节点卡掉了，1222334->14
            cur = cur.next
            注：pre不能后移，因为pre下一个节点可能也要去掉
         */
        public ListNode deleteDuplicates(ListNode head) {
            if(head==null || head.next==null) {
                return head;
            }
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode pre = dummy;
            ListNode cur = head;
            while(cur!=null && cur.next!=null) {
                //初始化的时cur指向的是哑结点，所以比较逻辑应该是cur的下一个节点和pre的下一个节点
                if(pre.next.val!=cur.next.val) {
                    pre = pre.next;
                    cur = cur.next;
                }
                else {
                    //当cur、pre指向的节点值相等，就不断后移cur，直到cur、pre指向的值不相等
                    while(cur.next!=null && pre.next.val==cur.next.val) {
                        cur = cur.next;
                    }
                    pre.next = cur.next;
                    cur = cur.next;
                }
            }
            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
