//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//
// 叶子节点 是指没有子节点的节点。
//
//
//
//
//
// 示例 1：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
//
//
// 示例 2：
//
//
//输入：root = [1,2,3], targetSum = 5
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1,2], targetSum = 0
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点总数在范围 [0, 5000] 内
// -1000 <= Node.val <= 1000
// -1000 <= targetSum <= 1000
//
//
//
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 643 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//java:路径总和 II
class PathSumIi {
    public static void main(String[] args) {
        Solution solution = new PathSumIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
public class Solution {
    /*
    回溯解法，类似于组合求和
    dfs下去，往下层传 sum - node.val
    当到达叶子节点时，node.val == sum，满足条件
    list.add(node.val),res.add(new ArrayList<>(list)),list.removeLast(),return;
    同样套模板
     */
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> list = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        dfs(root, sum);
        return res;
    }
    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && node.val == sum) {
            list.add(node.val);
            res.add(new ArrayList<>(list));
            list.removeLast();
            return;
        }
        list.add(node.val);
        dfs(node.left, sum - node.val);
        dfs(node.right, sum - node.val);
        list.removeLast();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
