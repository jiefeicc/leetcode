package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }

    public ListNode getListNode(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode temp = head;
        for (int i=1; i< nums.length; i++) {
            temp.next = new ListNode(nums[i]);
            temp = temp.next;
        }
        return head;
    }

    public String getListNodeStr(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        String s = Arrays.toString(list.toArray());
        String nodeStr = s.replace(",", "->").replace("[", "").replace("]", "");
        return nodeStr;
    }

    public String getListNodeStr(String str, ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        String s = Arrays.toString(list.toArray());
        String nodeStr = s.replace(",", "->").replace("[", str + ": ").replace("]", "");
        return nodeStr;
    }
}
