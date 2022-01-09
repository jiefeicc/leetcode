//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
//
// 示例 1:
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \
//     3   1
//
//输出: 7
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
//
// 示例 2:
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1095 👎 0

package leetcode.editor.cn;
//java:打家劫舍 III
class HouseRobberIii{
    public static void main(String[] args){
        Solution solution = new HouseRobberIii().new Solution();
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
        后序遍历 + 动态规划
        状态定义：dp[j]，当前节点作为根节点，j=0 偷node，j=1不偷
                当前状态时，获得的最大价值。
        初始状态：一个结点都没有，空节点，返回 0，对应后序遍历时候的递归终止条件
        状态转移方程：
            当前节点不偷：max(左节点偷，左节点不偷) + max(右节点偷，右节点不偷)
            当前节点偷：node.val + 左节点不偷 + 右节点不偷
         */
        public int rob(TreeNode root) {
            int[] res = dfs(root);
            return Math.max(res[0], res[1]);
        }
        // 后序遍历
        // 子结点陆续汇报信息给父结点，一层一层向上汇报，最后在根结点汇总值。
        private int[] dfs(TreeNode node) {
            // base case
            if (node == null) {
                return new int[]{0, 0};
            }
            int[] left = dfs(node.left);
            int[] right = dfs(node.right);
            // dp[0]：node 结点不偷
            // dp[1]：node 结点偷
            int[] dp = new int[2];
            dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            dp[1] = node.val + left[0] + right[0];
            return dp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
