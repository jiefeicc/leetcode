//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1402 👎 0

package leetcode.editor.cn;
//java:排序链表
class SortList{
    public static void main(String[] args){
        Solution solution = new SortList().new Solution();
        ListNode a1=new ListNode();
        ListNode a2=new ListNode();
        ListNode a3=new ListNode();
        ListNode a4=new ListNode();
        ListNode a5=new ListNode();
        ListNode a6=new ListNode();
        ListNode a7=new ListNode();
        ListNode a8=new ListNode();
        a1.val=1; a1.next=a2;
        a2.val=3; a2.next=a3;
        a3.val=94;a3.next=a4;
        a4.val=2; a4.next=a5;
        a5.val=5; a5.next=a6;
        a6.val=764;a6.next=a7;
        a7.val=23;a7.next=a8;
        a8.val=9;a8.next=null;
        solution.sortList(a1);
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
         归并（递归）
         */
        public ListNode _sortList(ListNode head) {
            // 1、递归结束条件
            if (head == null || head.next == null) {
                return head;
            }
            // 2、找到链表中间节点并断开链表 & 递归下探
            ListNode midNode = middleNode(head);
            ListNode rightHead = midNode.next;
            midNode.next = null;

            ListNode left = _sortList(head);
            ListNode right = _sortList(rightHead);

            // 3、当前层业务操作（合并有序链表）
            return mergeTwoLists(left, right);
        }
        //  找到链表中间节点（876. 链表的中间结点）
        private ListNode middleNode(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next.next;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
        // 合并两个有序链表（21. 合并两个有序链表）
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
        /*
        快排（递归）
         */
        public ListNode __sortList(ListNode head) {
            //边界
            if(head==null || head.next==null)  {
                return head;
            }
            //伪头结点
            ListNode pre=new ListNode(0,head);
            //快排
            quickSort(pre,null);
            //返回头结点
            return pre.next;
        }
        //输入时伪头结点和尾节点null
        void quickSort(ListNode pre,ListNode end){
            //如果节点数小于1就返回
            if(pre==end||pre.next==end||pre.next.next==end) {
                return;
            }
            //选第一个节点为基准
            ListNode b=pre.next;
            //建立临时链表
            ListNode cur=new ListNode(0);
            //临时左右两指针
            ListNode r=b;
            ListNode l=cur;
            //遍历，右指针下一节点为end，说明当前是最后一个元素，结束
            while(r.next!=end){
                //如果当前元素小于基准，就加入临时链表，并在原链表中删除
                if(r.next.val<b.val){
                    l.next=r.next;
                    l=l.next;
                    r.next=r.next.next;
                } else{
                    //不小于基准，右指针后移
                    r=r.next;
                }
            }
            //临时链表接在原链表前面，并把伪头结点指向临时节点头结点
            l.next=pre.next;
            pre.next=cur.next;
            //对基准的左右两边递归，注意输入都是伪头结点和两链表的尾节点的下一节点
            quickSort(pre,b);
            quickSort(b,end);
        }
        /*
        迭代法
         */
        public ListNode sortList(ListNode head) {
            int length = getLength(head);
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            for(int step = 1; step < length; step*=2){ //依次将链表分成1块，2块，4块...
                //每次变换步长，pre指针和cur指针都初始化在链表头
                ListNode pre = dummy;
                ListNode cur = dummy.next;
                while(cur!=null){
                    ListNode h1 = cur; //第一部分头 （第二次循环之后，cur为剩余部分头，不断往后把链表按照步长step分成一块一块...）
                    ListNode h2 = split(h1,step);  //第二部分头
                    cur = split(h2,step); //剩余部分的头
                    ListNode temp = merge(h1,h2); //将一二部分排序合并
                    pre.next = temp; //将前面的部分与排序好的部分连接
                    while(pre.next!=null){
                        pre = pre.next; //把pre指针移动到排序好的部分的末尾
                    }
                }
            }
            return dummy.next;
        }
        public int getLength(ListNode head){
            //获取链表长度
            int count = 0;
            while(head!=null){
                count++;
                head=head.next;
            }
            return count;
        }
        public ListNode split(ListNode head,int step){
            //断链操作 返回第二部分链表头
            if(head==null)  return null;
            ListNode cur = head;
            for(int i=1; i<step && cur.next!=null; i++){
                cur = cur.next;
            }
            ListNode right = cur.next;
            cur.next = null; //切断连接
            return right;
        }
        public ListNode merge(ListNode h1, ListNode h2){
            //合并两个有序链表
            ListNode head = new ListNode(-1);
            ListNode p = head;
            while(h1!=null && h2!=null){
                if(h1.val < h2.val){
                    p.next = h1;
                    h1 = h1.next;
                }
                else{
                    p.next = h2;
                    h2 = h2.next;
                }
                p = p.next;
            }
            if(h1!=null)    p.next = h1;
            if(h2!=null)    p.next = h2;

            return head.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
