//给你一个链表数组，每个链表都已经按升序排列。
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。
//
//
//
// 示例 1：
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
//
//
// 示例 2：
//
// 输入：lists = []
//输出：[]
//
//
// 示例 3：
//
// 输入：lists = [[]]
//输出：[]
//
//
//
//
// 提示：
//
//
// k == lists.length
// 0 <= k <= 10^4
// 0 <= lists[i].length <= 500
// -10^4 <= lists[i][j] <= 10^4
// lists[i] 按 升序 排列
// lists[i].length 的总和不超过 10^4
//
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 1651 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

//java:合并K个升序链表
class MergeKSortedLists{
    public static void main(String[] args){
        Solution solution = new MergeKSortedLists().new Solution();
        ListNode test11 = new ListNode(1);
        ListNode test14 = new ListNode(4);
        test14.next = new ListNode(5);
        test11.next = test14;

        ListNode test21 = new ListNode(1);
        ListNode test23 = new ListNode(3);
        test23.next = new ListNode(4);
        test21.next = test23;

        ListNode test32 = new ListNode(2);
        test32.next = new ListNode(6);

        ListNode[] lists = new ListNode[]{test21, test11, test32};

        solution.mergeKLists(lists);
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
        优先队列，小顶堆

        全部链表入队
        取出头节点最小的那个链表
        建立新链表
        取出得链表还有next节点，那就把next节点再入队

        最终效果就是pre节点依次连接从优先队列里面从小到大取出的节点
         */
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<ListNode> pq = new PriorityQueue<>((v1,v2)->v1.val-v2.val);
            for (ListNode node : lists) {
                if (node != null){
                    pq.add(node);
                }
            }
            ListNode dummyHead = new ListNode(0);
            ListNode pre = dummyHead;
            while (!pq.isEmpty()) {
                ListNode minNode = pq.poll();
                pre.next = minNode;
                pre = pre.next;
                if (minNode.next != null) {
                    pq.add(minNode.next);
                }
            }
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
