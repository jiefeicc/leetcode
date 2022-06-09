package leetcode.editor.cn;

import java.util.*;

class ErChaShuZhongHeWeiMouYiZhiDeLuJingLcof{
    public static void main(String[] args){
        Solution solution = new ErChaShuZhongHeWeiMouYiZhiDeLuJingLcof().new Solution();
        TreeNode treeNode = TreeNode.arrayToBTree(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1});
        System.out.println(solution.pathSum(treeNode, 22));
    }
    // 二叉树中和为某一值的路径
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
        回溯解法：
            回溯解法，类似于组合求和
            dfs下去，往下层传 sum - node.val
            当到达叶子节点时，node.val == sum，满足条件
            list.add(node.val),res.add(new ArrayList<>(list)),list.removeLast(),return;
            同样套模板
         */
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<List<Integer>> res = new LinkedList<>();
        public List<List<Integer>> pathSum(TreeNode root, int target) {
            if (root == null) {
                return res;
            }
            dfs(root, target);
            return res;
        }
        private void dfs(TreeNode root, int target) {
            if (root == null) {
                return;
            }
            if (target == root.val && root.left == null && root.right == null) {
                list.add(root.val);
                res.add(new ArrayList<>(list));
                list.removeLast();
                return;
            }
            list.add(root.val);
            dfs(root.left, target - root.val);
            dfs(root.right, target - root.val);
            list.removeLast();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}