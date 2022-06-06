package leetcode.editor.cn;

class ErChaSouSuoShuDeDiKdaJieDianLcof{
    public static void main(String[] args){
        Solution solution = new ErChaSouSuoShuDeDiKdaJieDianLcof().new Solution();
    }
    // 二叉搜索树的第k大节点
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        /*
        思路：
            二叉搜索树的特征是中序遍历时，val是递增的。题目要求第K大，那么倒着中序遍历到第K个就行。
         */
        int k = 0;
        int res = 0;
        public int kthLargest(TreeNode root, int k) {
            this.k = k;
            dfs(root);
            return res;
        }
        // 需要倒着中序遍历，先右再左。
        public void dfs(TreeNode root){
            // 当 root 遍历完了或者 res 已经得出结果了，直接返回。
            if (root == null || res != 0) {
                return;
            }
            dfs(root.right);
            k--;
            if (k == 0) {
                res = root.val;
                return;
            }
            dfs(root.left);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}