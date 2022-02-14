//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//
//
// 示例 2：
//
//
//输入：head = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 100] 内
// 0 <= Node.val <= 100
//
// Related Topics 递归 链表 👍 1231 👎 0

package leetcode.editor.cn;
//java:两两交换链表中的节点
class SwapNodesInPairs{
    public static void main(String[] args){
        Solution solution = new SwapNodesInPairs().new Solution();
        int[] nums = new int[]{1,2,3,4};
        ListNode head = new ListNode().getListNode(nums);
        solution.swapPairs(head);
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
        迭代解法
        1->2->3->4
        把1，2换位，然后3，4换位 start.next = end.next,，end.next = start
        cur 作用：cur 指向待处理的end，end，start处理完成后（换位），temp再指向start
         */
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode cur = dummy;
            while(cur.next != null && cur.next.next != null) {
                ListNode start = cur.next;
                ListNode end = cur.next.next;
                // temp 指向在上一轮处理过后的的链表尾，这一步操作把处理好的链表尾指向待处理的链表头
                // temp 指向的是地址，后面 end 处理好了，temp指的就对了
                cur.next = end;
                // start,end 交换位置
                start.next = end.next;
                end.next = start;
                // temp 指向处理过后的的链表尾
                cur = start;
            }
            return dummy.next;
        }

        public ListNode swapPairs1(ListNode head) {
            //递归的终止条件
            if(head==null || head.next==null) {
                return head;
            }
            //假设链表是 1->2->3->4
            //这句就先保存节点2
            ListNode temp = head.next;
            //继续递归，处理节点3->4
            //当递归结束返回后，就变成了4->3
            //于是head节点就指向了4，变成1->4->3
            head.next = swapPairs1(temp.next);
            //将2节点指向1
            temp.next = head;
            return temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
