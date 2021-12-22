//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。
//
// 路径和 是路径中各节点值的总和。
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
//
// 示例 2：
//
//
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
//
//
//
//
// 提示：
//
//
// 树中节点数目范围是 [1, 3 * 10⁴]
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1340 👎 0

package leetcode.editor.cn;
//java:二叉树中的最大路径和
class BinaryTreeMaximumPathSum{
    public static void main(String[] args){
        Solution solution = new BinaryTreeMaximumPathSum().new Solution();
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
        后序遍历
        递归方法作用是返回单边子树最大路径和（为负就不走当0）
        return root.val + max(leftMax, rightMax)
        维护一个全局变量max
        max = 从左子树走到右子树最大路径和
        max = Math.max(max, root.val+leftMax+rightMax)
         */
        int max = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            if (root == null) {
                return 0;
            }
            dfs(root);
            return max;
        }
        // 返回经过root的单边分支最大和， 即Math.max(root, root+left, root+right)
        public int dfs(TreeNode root) {
            if (root == null) {
                return 0;
            }
            //计算左边分支最大值，左边分支如果为负数还不如不选择
            int leftMax = Math.max(0, dfs(root.left));
            //计算右边分支最大值，右边分支如果为负数还不如不选择
            int rightMax = Math.max(0, dfs(root.right));
            //left->root->right 作为路径与已经计算过历史最大值做比较
            max = Math.max(max, root.val + leftMax + rightMax);
            // 返回经过root的单边最大分支给当前root的父节点计算使用
            return root.val + Math.max(leftMax, rightMax);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
