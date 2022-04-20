package leetcode.editor.cn;

import java.util.*;

class BalancedBinaryTree{
    public static void main(String[] args){
        Solution solution = new BalancedBinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        /*
        定义递归函数 height(TreeNode root)，预置功能是返回 root 的高度，但当root 的左右子树高度差大于1时返回 -1
        解题步骤：
            主函数调用 height(root) 判断root高度是否为 -1
            height(root) {
                输入判断：当 root == null，return 0
                // 求得左右子树高度
                int left = height(root.left);
                int right = height(root.right);
                判断当左右子树有一个高度为 -1 时，不用递归下去了，子树的左右子树高度差大于1
                返回：1.左右子树高度差大于1时直接返回 -1。2.不满足1.时，返回左右子树最大高度 + 1
            }
         */
        public boolean isBalanced(TreeNode root) {
            // 求 root 的高度，当高度是 -1 时，说明 root 左右子树高度差大于 1
            return height(root) != -1;
        }

        private int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = height(root.left);
            int right = height(root.right);
            if (left == -1) {
                return -1;
            }
            if (right == -1) {
                return -1;
            }
            return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}