package leetcode.editor.cn;

import java.util.*;

class ErChaSouSuoShuYuShuangXiangLianBiaoLcof{
    public static void main(String[] args){
        Solution solution = new ErChaSouSuoShuYuShuangXiangLianBiaoLcof().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /*
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
    */
    class Solution {
        /*
        中序遍历解法：
            中序遍历二叉搜索树，遍历顺序是递增的。
            解题思路：
                两个全局变量pre，head，pre保存中序遍历的前一个节点，head保存排序链表的头节点
                中序遍历 dfs ，递归到最底层，将 pre.right = cur,cur.left = pre。(当 pre 是 null，保存头节点 head = cur)
                然后 pre = cur，去往下一层递归处理
         */
        // 保存中序遍历的前一个节点
        Node pre;
        // 记录排序链表的头节点
        Node head;
        public Node treeToDoublyList(Node root) {
            if(root == null) {
                return null;
            }
            dfs(root);
            // 首尾连接
            head.left = pre;
            pre.right = head;
            return head;
        }
        void dfs(Node cur) {
            if(cur == null) {
                return;
            }
            dfs(cur.left);
            if(pre != null) {
                pre.right = cur;
            }
            else {
                head = cur;
            }
            cur.left = pre;
            pre = cur;
            dfs(cur.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}