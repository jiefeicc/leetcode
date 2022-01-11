package leetcode.editor.cn;

public class OddIncreaseEvenDecrease {
    /*
    奇数位升序偶数位降序链表排序
    题目描述：一个链表，奇数位升序偶数位降序，让链表变成升序的。
    比如：1 8 3 6 5 4 7 2 9，最后输出1 2 3 4 5 6 7 8 9。

    1.拆分为两个链表
    2.降序链表反转
    3.合并两个升序链表
     */
    public ListNode sort(ListNode head){
        if(head==null || head.next==null) {
            return head;
        }
        // 先把奇数位链表和偶数位链表拆开
        ListNode oddCur = head;
        ListNode evenCur = oddCur.next;
        ListNode oddHead = oddCur;
        ListNode evenHead = evenCur;
        while(evenCur != null){
            oddCur.next = evenCur.next;
            if(oddCur.next != null)
                evenCur.next = oddCur.next.next;
            oddCur = oddCur.next;
            evenCur = evenCur.next;
        }
        evenHead = reverseList(evenHead);
        return mergeTwoLists(oddHead,evenHead);
    }
    // 反转链表
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
    // 合并两个升序链表
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
    public static void main(String[] args) {
        ListNode ListNode1 = new ListNode(1);
        ListNode ListNode2 = new ListNode(8);
        ListNode ListNode3 = new ListNode(3);
        ListNode ListNode4 = new ListNode(6);
        ListNode ListNode5 = new ListNode(5);
        ListNode ListNode6 = new ListNode(4);
        ListNode ListNode7 = new ListNode(7);
        ListNode ListNode8 = new ListNode(2);
        ListNode ListNode9 = new ListNode(9);
        ListNode1.next = ListNode2;
        ListNode2.next = ListNode3;
        ListNode3.next = ListNode4;
        ListNode4.next = ListNode5;
        ListNode5.next = ListNode6;
        ListNode6.next = ListNode7;
        ListNode7.next = ListNode8;
        ListNode8.next = ListNode9;
        ListNode sort = new OddIncreaseEvenDecrease().sort(ListNode1);
        while (sort != null) {
            System.out.println(sort.val);
            sort = sort.next;
        }
    }
}
