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
        // solution.swapPairs(head);
        new ListNode().printListNode(head);
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
        public ListNode swapPairs(ListNode head) {
            //增加一个特殊节点方便处理
            ListNode p = new ListNode(-1);
            p.next = head;
            //创建a，b两个指针，这里还需要一个tmp指针
            ListNode a = p;
            ListNode b = p;
            ListNode tmp = p;
            while(b.next!=null && b.next.next!=null) {
                //a前进一位，b前进两位
                a = a.next;
                b = b.next.next;
                //这步很关键，tmp指针用来处理边界条件的
                //假设链表是1->2->3->4，a指向1，b指向2
                //改变a和b的指向，于是就变成2->1，但是1指向谁呢？
                //1是不能指向2的next，1应该指向4，而循环迭代的时候一次处理2个节点
                //1和2的关系弄清楚了，3和4的关系也能弄清楚，但需要一个指针来处理
                //2->1，4->3的关系，tmp指针就是干这个用的
                tmp.next = b;
                a.next = b.next;
                b.next = a;
                //现在链表就变成2->1->3->4
                //tmp和b都指向1节点，等下次迭代的时候
                //a就变成3，b就变成4，然后tmp就指向b，也就是1指向4
                tmp = a;
                b = a;
            }
            return p.next;
        }

        public ListNode swapPairs1(ListNode head) {
            //递归的终止条件
            if(head==null || head.next==null) {
                return head;
            }
            //假设链表是 1->2->3->4
            //这句就先保存节点2
            ListNode tmp = head.next;
            //继续递归，处理节点3->4
            //当递归结束返回后，就变成了4->3
            //于是head节点就指向了4，变成1->4->3
            head.next = swapPairs1(tmp.next);
            //将2节点指向1
            tmp.next = head;
            return tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
