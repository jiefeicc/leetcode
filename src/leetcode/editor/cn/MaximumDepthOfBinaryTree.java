//给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例：
//给定二叉树 [3,9,20,null,null,15,7]，
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回它的最大深度 3 。
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1190 👎 0

package leetcode.editor.cn;

import java.util.*;

class MaximumDepthOfBinaryTree{
    public static void main(String[] args){
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();
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
        递归解法
        假定函数已经具备已有功能：maxDepth(root) 求出 root 的最大深度
        代码流程：
            1.递归退出条件(也可以理解输入条件的校验)
            2.求出左子树最大深度 maxDepth(root.left)
            3.求出左子树最大深度 maxDepth(root.left)
            4.返回 max(leftDepth, rightDepth) + 1
         */
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
