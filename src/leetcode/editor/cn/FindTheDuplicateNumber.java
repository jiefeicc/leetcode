package leetcode.editor.cn;

import java.util.*;

class FindTheDuplicateNumber{
    public static void main(String[] args){
        Solution solution = new FindTheDuplicateNumber().new Solution();
    }
    //寻找重复数
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        思路：
            1,3,4,2,5
            0->1, 1->3, 2->4, 3->2, 4->5
            数组里没有重复的数，索引和对应位置的数的映射能组成一个链表，链表无环。
            1,3,4,2,2
            0->1, 1->3, 2->4, 3->2, 4->2
            数组里有重复的数，索引和对应位置的数的映射能组成一个链表，链表有环。
            综上，问题转换为类似 142 题，转到链表环入口即可。
        解题步骤：
            如何找到链表环入口
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
        public int findDuplicate(int[] nums) {
            int slow = 0;
            int fast = 0;
            // slow每次走一步，fast每次走两步。
            slow = nums[slow];
            fast = nums[nums[fast]];
            while(slow != fast){
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            // 当slow和fast相遇时,slow再走a（tmp从head走到链表环入口步）步，slow和temp在入口相遇。
            int temp = 0;
            while(temp != slow){
                temp = nums[temp];
                slow = nums[slow];
            }
            return temp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
