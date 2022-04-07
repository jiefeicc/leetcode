//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,1,4,null,2], k = 1
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数为 n 。 
// 1 <= k <= n <= 10⁴ 
// 0 <= Node.val <= 10⁴ 
// 
//
// 
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？ 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 597 👎 0

package leetcode.editor.cn;

import java.util.*;

class KthSmallestElementInABst{
    public static void main(String[] args){
        Solution solution = new KthSmallestElementInABst().new Solution();
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        node1.right = node2;
        TreeNode node3 = new TreeNode(3, node1, node4);
        solution.kthSmallest(node3, 1);
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
        中序遍历递归解法
        二叉搜索树的中序遍历是递增的
         */
        int ans = -1;
        int K = 0;
        public int kthSmallest(TreeNode root, int k) {
            K = k;
            inorder(root);
            return ans;
        }
        public void inorder(TreeNode root) {
            if (root!=null && ans == -1){
                inorder(root.left);
                if (--K == 0) {
                     ans = root.val;
                }
                inorder(root.right);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}