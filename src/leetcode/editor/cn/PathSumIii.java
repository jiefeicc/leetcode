//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
//
//
// 示例 2：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [0,1000]
// -10⁹ <= Node.val <= 10⁹
// -1000 <= targetSum <= 1000
//
// Related Topics 树 深度优先搜索 二叉树 👍 1178 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//java:路径总和 III
class PathSumIii{
    public static void main(String[] args){
        Solution solution = new PathSumIii().new Solution();
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
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> preSumMap = new HashMap<>();
        int target;
        public int pathSum(TreeNode root, int sum) {
            target = sum;
            // 初始化前缀和为0的一条路径
            preSumMap.put(0, 1);
            // 前缀和的递归回溯思路
            return recursionPathSum(root, 0);
        }
        /*
         前缀和的递归回溯
         当前路径的前缀和 curSum = curSum + node.val
         如果之前路径存在前缀和 curSum - target
         说明存在路径的和为 target
         注：在回溯结束，回到上层时去除当前层，保证其不影响其他分支的结果
         */
        private int recursionPathSum(TreeNode node, int curSum) {
            if (node == null) {
                return 0;
            }

            int res = 0;
            // 当前路径上的前缀和
            curSum += node.val;

            // currSum-target相当于找路径的起点，当前点到起点的距离就是target
            res += preSumMap.getOrDefault(curSum - target, 0);
            // 更新路径上当前节点前缀和的个数
            preSumMap.put(curSum, preSumMap.getOrDefault(curSum, 0) + 1);

            // 进入下一层
            int left = recursionPathSum(node.left, curSum);
            int right = recursionPathSum(node.right, curSum);

            // 当我们把一个节点的前缀和信息更新到map里时，它应当只对其子节点们有效。
            preSumMap.put(curSum, preSumMap.get(curSum) - 1);

            // 结果是当前节点前缀树的个数加上左边满足的个数加右边满足的个数
            return res + left + right;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
